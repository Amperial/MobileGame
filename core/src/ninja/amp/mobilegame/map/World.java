package ninja.amp.mobilegame.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import ninja.amp.mobilegame.MobileGame;
import ninja.amp.mobilegame.engine.graphics.Atlas;
import ninja.amp.mobilegame.engine.graphics.RegionTexture;
import ninja.amp.mobilegame.engine.physics.collision.RectangleHitbox;
import ninja.amp.mobilegame.engine.physics.forces.SimpleForce;
import ninja.amp.mobilegame.engine.physics.mass.StaticMass;
import ninja.amp.mobilegame.engine.physics.vectors.LVector2;
import ninja.amp.mobilegame.engine.physics.vectors.limits.CubeLimit;
import ninja.amp.mobilegame.engine.physics.vectors.limits.LengthLimit;
import ninja.amp.mobilegame.engine.physics.vectors.limits.Limit;
import ninja.amp.mobilegame.engine.graphics.AnimatedTexture;
import ninja.amp.mobilegame.engine.physics.vectors.limits.RectangleLimit;
import ninja.amp.mobilegame.map.old.TileMap;
import ninja.amp.mobilegame.objects.Entity;
import ninja.amp.mobilegame.objects.body.Body;
import ninja.amp.mobilegame.objects.body.BodyPart;
import ninja.amp.mobilegame.objects.body.WeaponPart;
import ninja.amp.mobilegame.objects.body.pose.Pose;
import ninja.amp.mobilegame.objects.characters.Character;
import ninja.amp.mobilegame.engine.physics.forces.Force;
import ninja.amp.mobilegame.objects.characters.movement.poses.Idle;
import ninja.amp.mobilegame.objects.characters.movement.poses.Jumping;
import ninja.amp.mobilegame.objects.characters.movement.poses.Running;
import ninja.amp.mobilegame.objects.characters.npc.State;
import ninja.amp.mobilegame.objects.characters.npc.ai.actions.movement.WanderInRange;
import ninja.amp.mobilegame.objects.characters.npc.hostile.Bat;
import ninja.amp.mobilegame.objects.items.weapons.axes.Hammer;
import ninja.amp.mobilegame.objects.items.weapons.swords.Longsword;
import ninja.amp.mobilegame.screens.Screen;
import ninja.amp.mobilegame.screens.ScreenCamera;
import ninja.amp.mobilegame.screens.game.GameScreen;

import java.util.HashSet;
import java.util.Set;

public class World {

    private MobileGame game;
    private ScreenCamera camera;
    private float elasticity = 10;
    private float yOffset = 0;

    public TileMap map;
    private Character character;

    private Set<Entity> entities = new HashSet<Entity>();
    private Force gravity = new SimpleForce(new Vector2(0, -30f), false);

    private float scale;

    // TODO: remove
    public boolean left = false;
    public boolean right = false;
    public boolean lastleft = false;
    public boolean jump = false;
    public boolean air = false;
    private float totalDelta = 0;
    private Pose idle;
    private Pose running;
    private Pose jumping;
    private boolean pose_idle = false;
    private boolean pose_running = false;
    private boolean pose_jumping = false;
    private Bat bat;

    private AnimatedTexture tex;
    private AnimatedTexture tex_n;



    public World(MobileGame game, final GameScreen screen) {
        this.game = game;

        tex = AnimatedTexture.load(Gdx.files.internal("entities/coin_gold.png"), 8, 8, 0.125f, Animation.PlayMode.LOOP, screen);
        tex_n = AnimatedTexture.load(Gdx.files.internal("entities/coin_normal.png"), 8, 8, 0.125f, Animation.PlayMode.LOOP, screen);
        //tex = new SingleTexture(Gdx.files.internal("entities/rock.png"), screen);
        //tex_n = new SingleTexture(Gdx.files.internal("entities/rock_n.png"), screen);

        camera = new ScreenCamera(screen, Limit.VEC3); // TODO: set camera initial position

        map = new TileMap();

        Atlas bodyAtlas = new Atlas(Gdx.files.internal("entities/character.pack"), screen);
        Body body = new Body() {
            Vector2 position = new Vector2();
            float max_up_velocity = 12.5f;
            float max_down_velocity = -20;
            float min_down_velocity = -2;
            @Override
            public Vector2 position() {
                float bob = Math.abs(MathUtils.sin((character.getBody().getPoseTime()*2.5f + 0.25f)*MathUtils.PI))*0.0625f;
                if (!left && !right) {
                    bob = 0;
                }
                return position.set(character.getPosition()).add(4f/16f, 9f/16f + bob);
            }
            @Override
            public Pose getPose() {
                return idle;
            }
            @Override
            public float getPoseTime() {
                if (pose_jumping) {
                    float y_velocity = character.getVelocity().y;
                    if (y_velocity > max_up_velocity || y_velocity < max_down_velocity) {
                        return 1;
                    } else if (y_velocity > 0) {
                        return y_velocity / max_up_velocity;
                    } else {
                        if (y_velocity > min_down_velocity) {
                            return 0;
                        } else {
                            return (y_velocity - min_down_velocity) / (max_down_velocity - min_down_velocity);
                        }
                    }
                } else {
                    return super.getPoseTime();
                }
            }
        };
        idle = new Idle(body);
        running = new Running(body);
        jumping = new Jumping(body);
        BodyPart torso = new BodyPart(body, "torso", new RegionTexture(bodyAtlas.findRegion("body"), screen), 0, 1);
        BodyPart head = new BodyPart(body, "head", new RegionTexture(bodyAtlas.findRegion("head"), screen), 1, 2);
        BodyPart arm_left_upper = new BodyPart(body, "arm_left_upper", new RegionTexture(bodyAtlas.findRegion("arm_upper"), screen), -5, 7, 50);
        BodyPart arm_left_lower = new BodyPart(body, "arm_left_lower", new RegionTexture(bodyAtlas.findRegion("arm_lower"), screen), -4, 6, 20);
        BodyPart arm_right_upper = new BodyPart(body, "arm_right_upper", new RegionTexture(bodyAtlas.findRegion("arm_upper"), screen), 6, -4, 50);
        BodyPart arm_right_lower = new BodyPart(body, "arm_right_lower", new RegionTexture(bodyAtlas.findRegion("arm_lower"), screen), 5, -3, 20);
        BodyPart leg_left_upper = new BodyPart(body, "leg_left_upper", new RegionTexture(bodyAtlas.findRegion("leg_upper"), screen), -2, 4, 50);
        BodyPart leg_left_lower = new BodyPart(body, "leg_left_lower", new RegionTexture(bodyAtlas.findRegion("leg_lower"), screen), -1, 3, 40);
        BodyPart leg_right_upper = new BodyPart(body, "leg_right_upper", new RegionTexture(bodyAtlas.findRegion("leg_upper"), screen), 3, -1, 50);
        BodyPart leg_right_lower = new BodyPart(body, "leg_right_lower", new RegionTexture(bodyAtlas.findRegion("leg_lower"), screen), 2, 0, 40);
        BodyPart weapon_right = new WeaponPart(body, arm_right_lower, new Longsword("Longsword", new StaticMass(1), screen), "weapon_right", 4, -2, 0, 4f/16f, 0);
        //BodyPart weapon_left = new WeaponPart(body, arm_left_lower, new Hammer("Hammer", new StaticMass(1), screen), "weapon_left", -3, 5, 0, 4f/16f, 0);
        character = new Character(this,
                new LVector2(1, 2, new RectangleLimit(new Rectangle(0, 0, map.width - 3, map.height - 3))),   // TODO: Position
                new LVector2(new RectangleLimit(new Rectangle(-6, -20, 12, 40))),   // TODO: Velocity
                new LVector2(Limit.VEC2),   // TODO: Acceleration
                new StaticMass(1),          // TODO: Mass
                new RectangleHitbox(new Rectangle(0, 0, 1, 1)) {
                    @Override
                    public Vector2 getPosition() {
                        return character.getPosition();
                    }
                }, body);
        entities.add(character);


        bat = new Bat(screen, this, new LVector2(5, 5, Limit.VEC2), new LVector2(0, 0, new LengthLimit<Vector2>(1)), new LVector2(0, 0, Limit.VEC2), new StaticMass(1), State.HOSTILE, new RectangleHitbox(new Rectangle(0, 0, 1, 1)) {
            @Override
            public Vector2 getPosition() {
                return bat.getPosition();
            }
        });
        /*
        bat.setAction(new Follow(bat, 5) {
            Vector2 target = new Vector2();
            @Override
            public Vector2 getTarget() {
                return target.set(character.getPosition()).add(0.5f, 2.5f);
            }
        });
        */
        bat.setAction(new WanderInRange(bat, 5, bat.getPosition().cpy(), 2));
        //bat.setAction(new Patrol(bat, 5f, bat.getPosition().cpy(), bat.getPosition().cpy().add(0, 1), bat.getPosition().cpy().add(1, 1), bat.getPosition().cpy().add(1, 0)));
        entities.add(bat);
    }

    public InputProcessor getCharacterProcessor(final Screen screen) {
        return new InputProcessor() {
            @Override
            public boolean keyDown(int keycode) {
                return false;
            }

            @Override
            public boolean keyUp(int keycode) {
                return false;
            }

            @Override
            public boolean keyTyped(char character) {
                return false;
            }

            @Override
            public boolean touchDown(int screenX, int screenY, int pointer, int button) {
                float charX = character.getPosition().x * scale;
                float charY = character.getPosition().y * scale;

                Vector3 point = camera.unproject(new Vector3(screenX, screenY, 0));

                boolean containsx = point.x >= charX && point.x <= charX + scale;
                boolean containsy = point.y >= charY && point.y <= charY + scale;

                if (containsx && containsy) {
                    screen.openPopup(screen.getMenu("character"));
                    return true;
                }
                return false;
            }

            @Override
            public boolean touchUp(int screenX, int screenY, int pointer, int button) {
                return false;
            }

            @Override
            public boolean touchDragged(int screenX, int screenY, int pointer) {
                return false;
            }

            @Override
            public boolean mouseMoved(int screenX, int screenY) {
                return false;
            }

            @Override
            public boolean scrolled(int amount) {
                return false;
            }
        };
    }


    Force rightforce = new SimpleForce(new Vector2(100, 0), true);
    Force leftforce = new SimpleForce(new Vector2(-100, 0), true);
    Force jumpforce = new Force() {
        Vector2 vec = new Vector2();
        @Override
        public Vector2 calculate(Entity entity, float delta) {
            return vec.set(0, -character.getVelocity().y + 12.5f).scl(1 / delta);
        }
    };
    Force friction_air = new Force() {
        Vector2 vec = new Vector2();
        @Override
        public Vector2 calculate(Entity entity, float delta) {
            return vec.set(entity.getVelocity().x, 0).scl(-5);
        }
    };
    Force friction_ground = new Force() {
        Vector2 vec = new Vector2();
        @Override
        public Vector2 calculate(Entity entity, float delta) {
            return vec.set(entity.getVelocity().x, 0).scl(-10);
        }
    };


    public void update(float delta) {
        if (delta > 0.05f) {
            delta = 0.05f;
        }

        //TEMP
        if (right && !left) {
            if (character.getVelocity().x < 0) {
                character.getVelocity().x = 0;
            }
            character.applyForce(rightforce.calculate(character, delta));
            if (lastleft) {
                lastleft = false;
                totalDelta = 0;
            }
        } else if (left && !right) {
            if (character.getVelocity().x > 0) {
                character.getVelocity().x = 0;
            }
            character.applyForce(leftforce.calculate(character, delta));
            if (!lastleft) {
                lastleft = true;
                totalDelta = 0;
            }
        } else if (!right) {
            if (character.getVelocity().y > 0 || character.getVelocity().y < 0) {
                character.applyForce(friction_air.calculate(character, delta));
            } else {
                character.applyForce(friction_ground.calculate(character, delta));
            }
            totalDelta = 0;
        }
        if (jump) {
            character.applyForce(jumpforce.calculate(character, delta));
            totalDelta = 0;
            jump = false;
        }
        //TEMP

        character.applyForce(gravity.calculate(character, delta));
        for (Entity entity : entities) {
        //    entity.applyForce(gravity.calculate(entity, delta));
            entity.update(delta);
        }

        // TEMP
        if (character.getVelocity().y > 0 || character.getVelocity().y < 0) {
            if (!pose_jumping) {
                character.getBody().setPose(jumping);
                pose_idle = false;
                pose_jumping = true;
                pose_running = false;
            }
        } else if (!left && !right) {
            if (!pose_idle) {
                character.getBody().setPose(idle);
                pose_idle = true;
                pose_jumping = false;
                pose_running = false;
            }
        } else {
            if (!pose_running) {
                character.getBody().setPose(running);
                pose_idle = false;
                pose_jumping = false;
                pose_running = true;
            }
        }
        // TEMP

        camera.position.add(((character.getPosition().x+0.5f) * scale - camera.position.x) * delta * elasticity, ((character.getPosition().y + 0.5f + yOffset) * scale - camera.position.y) * delta * elasticity, 0);
    }

    public void render(float delta) {
        camera.apply(game.batch);
        game.batch.begin();

        // Draw Map
        map.draw(game.batch, 0, 100, 0, 50);

        // Draw Character
        //tex.update(delta);
        //game.batch.draw(tex.getRegion(), character.position().x * scale, character.position().y * scale, scale, scale);
        //game.batch.draw(CastleTile.SOLID_FLOOR.getTexture(), character.position().x * scale, character.position().y * scale, scale, scale);
        character.getBody().draw(game.batch, delta, lastleft);

        bat.getBody().draw(game.batch, delta, bat.getVelocity().x > 0);

        game.batch.end();
    }

    public float getbackgroundx() {
        return (map.getWidth() / 2) - camera.position.x;
    }

    public float getbackgroundy() {
        //return (map.getHeight() / 2) - camera.position.y;
        return (camera.viewportHeight / 2) - camera.position.y;
    }

    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
        camera.setLimit(new CubeLimit(new Vector3(camera.viewportWidth / 2, camera.viewportHeight / 2, 0), new Vector3(map.getWidth() - (camera.viewportWidth / 2), map.getHeight() - (camera.viewportHeight / 2), 0)));
        camera.position.set((character.getPosition().x+0.5f) * scale, (character.getPosition().y + 0.5f + yOffset) * scale, 0);

        scale = 16 * width / 300;
        map.setScale(scale);

        character.getBody().setScale(scale);

        bat.getBody().setScale(scale);
    }

    public void dispose() {
        map.dispose();
    }

    public Character getCharacter() {
        return character;
    }

}
