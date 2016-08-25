package ninja.amp.mobilegame.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
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
import ninja.amp.mobilegame.engine.physics.vectors.limits.Limit;
import ninja.amp.mobilegame.engine.graphics.AnimatedTexture;
import ninja.amp.mobilegame.engine.physics.vectors.limits.RectangleLimit;
import ninja.amp.mobilegame.map.tiles.TileMap;
import ninja.amp.mobilegame.objects.Entity;
import ninja.amp.mobilegame.objects.characters.Character;
import ninja.amp.mobilegame.engine.physics.forces.Force;
import ninja.amp.mobilegame.objects.characters.movement.Body;
import ninja.amp.mobilegame.objects.characters.movement.BodyPart;
import ninja.amp.mobilegame.objects.characters.movement.MultiPosition;
import ninja.amp.mobilegame.objects.characters.movement.Position;
import ninja.amp.mobilegame.objects.characters.movement.positions.Idle;
import ninja.amp.mobilegame.objects.characters.movement.positions.Jumping;
import ninja.amp.mobilegame.objects.characters.movement.positions.Running;
import ninja.amp.mobilegame.objects.characters.movement.positions.WeaponPart;
import ninja.amp.mobilegame.objects.characters.npc.State;
import ninja.amp.mobilegame.objects.characters.npc.ai.actions.MoveTo;
import ninja.amp.mobilegame.objects.characters.npc.hostile.Bat;
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
    private float totalDelta = 0;
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
            Vector2 temp = new Vector2();
            @Override
            public Vector2 position() {
                float bob = (float)Math.abs(Math.sin((totalDelta*2.5f + 0.25f)*Math.PI))*0.0625f;
                if (!left && !right) {
                    bob = 0;
                }
                return temp.set(character.getPosition()).add(4f/16f, 9f/16f + bob);
            }
            @Override
            public void draw(Batch batch, float delta, boolean flipped) {
                totalDelta += delta;
                super.draw(batch, delta, flipped);
            }
        };
        Idle.BODY = body;
        Running.BODY = body;
        Jumping.BODY = body;
        BodyPart torso = new BodyPart(body, null, new RegionTexture(bodyAtlas.findRegion("body"), screen), 0, 1, new MultiPosition() {
            @Override
            public Position current() {
                if (character.getVelocity().y > 0 || character.getVelocity().y < 0) {
                    return Jumping.TORSO;
                } else if (!left && !right) {
                    return Idle.TORSO;
                } else {
                    return Running.TORSO;
                }
            }
        }) {
            @Override
            public int getDepth() {
                if (lastleft) {
                    return 1;
                } else {
                    return 0;
                }
            }
        };
        BodyPart head = new BodyPart(body, torso, new RegionTexture(bodyAtlas.findRegion("head"), screen), 1, 2, new MultiPosition() {
            @Override
            public Position current() {
                if (character.getVelocity().y > 0 || character.getVelocity().y < 0) {
                    return Jumping.HEAD;
                } else if (!left && !right) {
                    return Idle.HEAD;
                } else {
                    return Running.HEAD;
                }
            }
        }) {
            @Override
            public int getDepth() {
                if (lastleft) {
                    return 0;
                } else {
                    return 1;
                }
            }
        };
        //arm_left_upper, arm_left_lower, leg left upper, leg left lower, torso, head, leg right lower, leg right upper, weapon, arm right lower, arm right upper
        //-4                -3              -2              -1              0       1   2                   3               4       5               6
        //arm right upper, arm right lower, weapon, leg right upper, leg right lower, torso, head, leg left lower, leg left upper, arm left lower, arm left upper
        //-4                -3              -2              -1              0            1   2                   3               4       5               6
        BodyPart arm_left_upper = new BodyPart(body, torso, new RegionTexture(bodyAtlas.findRegion("arm_upper"), screen), -4, 6, new MultiPosition() {
            @Override
            public Position current() {
                if (character.getVelocity().y > 0 || character.getVelocity().y < 0) {
                    return Jumping.ARM_LEFT_UPPER;
                } else if (!left && !right) {
                    return Idle.ARM_LEFT_UPPER;
                } else {
                    return Running.ARM_LEFT_UPPER;
                }
            }
        });
        BodyPart arm_left_lower = new BodyPart(body, arm_left_upper, new RegionTexture(bodyAtlas.findRegion("arm_lower"), screen), -3, 5, new MultiPosition() {
            @Override
            public Position current() {
                if (character.getVelocity().y > 0 || character.getVelocity().y < 0) {
                    return Jumping.ARM_LEFT_LOWER;
                } else if (!left && !right) {
                    return Idle.ARM_LEFT_LOWER;
                } else {
                    return Running.ARM_LEFT_LOWER;
                }
            }
        });
        BodyPart arm_right_upper = new BodyPart(body, torso, new RegionTexture(bodyAtlas.findRegion("arm_upper"), screen), 6, -4, new MultiPosition() {
            @Override
            public Position current() {
                if (character.getVelocity().y > 0 || character.getVelocity().y < 0) {
                    return Jumping.ARM_RIGHT_UPPER;
                } else if (!left && !right) {
                    return Idle.ARM_RIGHT_UPPER;
                } else {
                    return Running.ARM_RIGHT_UPPER;
                }
            }
        });
        BodyPart arm_right_lower = new BodyPart(body, arm_right_upper, new RegionTexture(bodyAtlas.findRegion("arm_lower"), screen), 5, -3, new MultiPosition() {
            @Override
            public Position current() {
                if (character.getVelocity().y > 0 || character.getVelocity().y < 0) {
                    return Jumping.ARM_RIGHT_LOWER;
                } else if (!left && !right) {
                    return Idle.ARM_RIGHT_LOWER;
                } else {
                    return Running.ARM_RIGHT_LOWER;
                }
            }
        });

        BodyPart leg_left_upper = new BodyPart(body, torso, new RegionTexture(bodyAtlas.findRegion("leg_upper"), screen), -2, 4, new MultiPosition() {
            @Override
            public Position current() {
                if (character.getVelocity().y > 0 || character.getVelocity().y < 0) {
                    return Jumping.LEG_LEFT_UPPER;
                } else if (!left && !right) {
                    return Idle.LEG_LEFT_UPPER;
                } else {
                    return Running.LEG_LEFT_UPPER;
                }
            }
        });
        BodyPart leg_left_lower = new BodyPart(body, leg_left_upper, new RegionTexture(bodyAtlas.findRegion("leg_lower"), screen), -1, 3, new MultiPosition() {
            @Override
            public Position current() {
                if (character.getVelocity().y > 0 || character.getVelocity().y < 0) {
                    return Jumping.LEG_LEFT_LOWER;
                } else if (!left && !right) {
                    return Idle.LEG_LEFT_LOWER;
                } else {
                    return Running.LEG_LEFT_LOWER;
                }
            }
        });
        BodyPart leg_right_upper = new BodyPart(body, torso, new RegionTexture(bodyAtlas.findRegion("leg_upper"), screen), 3, -1, new MultiPosition() {
            @Override
            public Position current() {
                if (character.getVelocity().y > 0 || character.getVelocity().y < 0) {
                    return Jumping.LEG_RIGHT_UPPER;
                } else if (!left && !right) {
                    return Idle.LEG_RIGHT_UPPER;
                } else {
                    return Running.LEG_RIGHT_UPPER;
                }
            }
        });
        BodyPart leg_right_lower = new BodyPart(body, leg_right_upper, new RegionTexture(bodyAtlas.findRegion("leg_lower"), screen), 2, 0, new MultiPosition() {
            @Override
            public Position current() {
                if (character.getVelocity().y > 0 || character.getVelocity().y < 0) {
                    return Jumping.LEG_RIGHT_LOWER;
                } else if (!left && !right) {
                    return Idle.LEG_RIGHT_LOWER;
                } else {
                    return Running.LEG_RIGHT_LOWER;
                }
            }
        });
        BodyPart weapon = new WeaponPart(body, arm_right_lower, new Longsword("Longsword", new StaticMass(1), screen), 4, -2, 0, 4f/16f, 0);

        character = new Character(this,
                new LVector2(1, 2, new RectangleLimit(new Rectangle(0, 0, map.width - 3, map.height - 3))),   // TODO: Position
                new LVector2(new RectangleLimit(new Rectangle(-6f, -20, 12, 40))),   // TODO: Velocity
                new LVector2(Limit.VEC2),   // TODO: Acceleration
                new StaticMass(1),          // TODO: Mass
                new RectangleHitbox(new Rectangle(0, 0, 1, 1)) {
                    @Override
                    public Vector2 getPosition() {
                        return character.getPosition();
                    }
                }, body);
        entities.add(character);

        bat = new Bat(screen, this, new LVector2(5, 5, Limit.VEC2), new LVector2(0, 0, Limit.VEC2), new LVector2(0, 0, Limit.VEC2), new StaticMass(1), State.HOSTILE, new RectangleHitbox(new Rectangle(0, 0, 1, 1)) {
            @Override
            public Vector2 getPosition() {
                return bat.getPosition();
            }
        });
        bat.setAction(new MoveTo(bat, 5) {
            Vector2 target = new Vector2();
            @Override
            public boolean isComplete() {
                //return bat.getVelocity().len2() < 0.1 && bat.getPosition().epsilonEquals(getTarget(), 0.001f);
                return false;
            }
            @Override
            public Vector2 getTarget() {
                return target.set(character.getPosition()).add(0.5f, 2.5f);
            }
        });
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
            return vec.set(0, -character.getVelocity().y + 15).scl(1 / delta);
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
                Idle.resetTotalTime();
                Running.resetTotalTime();
                totalDelta = 0;
            }
        } else if (left && !right) {
            if (character.getVelocity().x > 0) {
                character.getVelocity().x = 0;
            }
            character.applyForce(leftforce.calculate(character, delta));
            if (!lastleft) {
                lastleft = true;
                Idle.resetTotalTime();
                Running.resetTotalTime();
                totalDelta = 0;
            }
        } else if (!right) {
            if (character.getVelocity().y > 0 || character.getVelocity().y < 0) {
                character.applyForce(friction_air.calculate(character, delta));
            } else {
                character.applyForce(friction_ground.calculate(character, delta));
                Jumping.resetTotalTime();
            }
            Running.resetTotalTime();
            totalDelta = 0;
        }
        if (jump) {
            character.applyForce(jumpforce.calculate(character, delta));
            Idle.resetTotalTime();
            Running.resetTotalTime();
            Jumping.resetTotalTime();
            totalDelta = 0;
            jump = false;
        }
        //TEMP

        character.applyForce(gravity.calculate(character, delta));
        for (Entity entity : entities) {
        //    entity.applyForce(gravity.calculate(entity, delta));
            entity.update(delta);
        }

        camera.position.add(((character.getPosition().x+0.5f) * scale - camera.position.x) * delta * elasticity, ((character.getPosition().y + 0.5f + yOffset) * scale - camera.position.y) * delta * elasticity, 0);

    }

    public void render(float delta) {
        camera.apply(game.batch);
        game.batch.begin();

        // Draw Map
        map.draw(game.batch, 0, 100, 0, 50);

        // Draw Character
        //tex.update(delta);
        //game.batch.draw(tex.getRegion(), character.getPosition().x * scale, character.getPosition().y * scale, scale, scale);
        //game.batch.draw(CastleTile.SOLID_FLOOR.getTexture(), character.getPosition().x * scale, character.getPosition().y * scale, scale, scale);
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
