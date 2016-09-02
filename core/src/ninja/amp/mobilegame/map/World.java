package ninja.amp.mobilegame.map;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.math.*;
import ninja.amp.mobilegame.MobileGame;
import ninja.amp.mobilegame.engine.physics.collision.EntityHitbox;
import ninja.amp.mobilegame.engine.physics.collision.Hitbox;
import ninja.amp.mobilegame.engine.physics.collision.PolygonHitbox;
import ninja.amp.mobilegame.engine.physics.mass.StaticMass;
import ninja.amp.mobilegame.engine.physics.vectors.LVector2;
import ninja.amp.mobilegame.engine.physics.vectors.limits.CubeLimit;
import ninja.amp.mobilegame.engine.physics.vectors.limits.LengthLimit;
import ninja.amp.mobilegame.engine.physics.vectors.limits.Limit;
import ninja.amp.mobilegame.map.old.CastleTile;
import ninja.amp.mobilegame.map.old.TileMap;
import ninja.amp.mobilegame.objects.Entity;
import ninja.amp.mobilegame.objects.characters.Character;
import ninja.amp.mobilegame.objects.characters.npc.State;
import ninja.amp.mobilegame.objects.characters.npc.ai.actions.movement.Follow;
import ninja.amp.mobilegame.objects.characters.npc.hostile.Bat;
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
    private Bat bat; // TODO: remove
    private Set<Entity> entities = new HashSet<Entity>();

    private float scale;

    private Hitbox sword_hitbox; // TODO: remove
    private Hitbox sword_hitbox_flip; // TODO: remove

    public World(MobileGame game, final GameScreen screen) {
        this.game = game;

        //AnimatedTexture.load(Gdx.files.internal("entities/coin_gold.png"), 8, 8, 0.125f, Animation.PlayMode.LOOP, screen);

        camera = new ScreenCamera(screen, Limit.VEC3); // TODO: set camera initial position

        map = new TileMap();


        //sword is 5f/24f thick, 1 long starting at 8f/24f
        /*
         sword_hitbox = new RectangleHitbox(new Rectangle(0, 2f/24f, 7f/24f, 32f/24f)) {
            @Override
            public float getX() {
                return weapon_right.getPosition().getX() - weapon_right.getPosition().getOriginX();
            }
            @Override
            public float getY() {
                 return weapon_right.getPosition().getY() - weapon_right.getPosition().getOriginY();
            }
             @Override
             public float getOriginX() {
                 return weapon_right.getPosition().getOriginX();
             }
             @Override
             public float getOriginY() {
                 return weapon_right.getPosition().getOriginY();
             }
            @Override
            public float getRotation() {
                return weapon_right.getPosition().getRotation();
            }
        };
        sword_hitbox_flip = new RectangleHitbox(new Rectangle(0, 2f/24f, -7f/24f, 32f/24f)) {
            @Override
            public float getX() {
                return weapon_right.getPosition().getFlippedX() + weapon_right.getPosition().getOriginX();
            }
            @Override
            public float getY() {
                return weapon_right.getPosition().getY() - weapon_right.getPosition().getOriginY();
            }
            @Override
            public float getOriginX() {
                return -weapon_right.getPosition().getOriginX();
            }
            @Override
            public float getOriginY() {
                return weapon_right.getPosition().getOriginY();
            }
            @Override
            public float getRotation() {
                return -weapon_right.getPosition().getRotation();
            }
        };
        */

        bat = new Bat(
                screen,
                this,
                new LVector2(5, 2, Limit.VEC2),
                new LVector2(0, 0, new LengthLimit<Vector2>(20)),
                new LVector2(0, 0, Limit.VEC2),
                new StaticMass(1),
                State.HOSTILE,
                new EntityHitbox(bat, new Rectangle(-1f/4f, 1f/4f, 3f/4f, 1f/2f))
        );
        bat.setAction(new Follow(bat, 5) {
            Vector2 target = new Vector2();
            @Override
            public Vector2 getTarget() {
                //return target.set(5, 2);
                return target.set(character.getPosition()).add(0.5f, 0.5f);
            }
        });
        //bat.setAction(new WanderInRange(bat, 5, bat.getPosition().cpy(), 2));
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

    public void update(float delta) {
        if (delta > 0.05f) {
            delta = 0.05f;
        }

        character.update(delta);
        for (Entity entity : entities) {
            entity.update(delta);
        }

        /*
        if (pose_attack && attackController.getAttack().getState() == Attack.State.ATTACK && attackController.getAttack().getPoseTime() > 0.2f) {
            if (bat.getHitbox().intersects(lastleft ? sword_hitbox_flip : sword_hitbox) && bat.attack(0.5f)) {
                if (lastleft) {
                    bat.applyForce(new Impulse(new Vector2(-100, 40)).calculate(bat, delta));
                } else {
                    bat.applyForce(new Impulse(new Vector2(100, 40)).calculate(bat, delta));
                }
            }
        } else if (bat.getHitbox().intersects(character.getHitbox()) && character.attack(2f)) {
            System.out.println("TOOK DAMAGE");
            // BATS SHOULD PICK UP PLAYERS AND CARRY THEM THROUGH THE AIR BEFORE DROPPING THEM SOMEWHERE
            character.applyForce(new Impulse(new Vector2(0, 25)).calculate(character, delta));
            bat.applyForce(new Impulse(new Vector2(0, 75)).calculate(bat, delta));
        }
        */

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
        //drawHitbox(game.batch, (RectangleHitbox) character.getHitbox());
        character.getBody().draw(game.batch, delta);
        /*
        if (lastleft) {
            drawHitbox((PolygonHitbox) sword_hitbox_flip, (int)((PolygonHitbox) sword_hitbox_flip).getX(), (int)((PolygonHitbox) sword_hitbox_flip).getY());
        } else {
            drawHitbox((PolygonHitbox) sword_hitbox, (int)((PolygonHitbox) sword_hitbox).getX(), (int)((PolygonHitbox) sword_hitbox).getY());
        }
        */

        bat.getBody().draw(game.batch, delta);
        //drawHitbox((PolygonHitbox) bat.getHitbox(), (int) ((PolygonHitbox) bat.getHitbox()).getX(), (int)((PolygonHitbox) bat.getHitbox()).getY());

        game.batch.end();
    }

    private void drawHitbox(PolygonHitbox hitbox, int xposition, int yposition) {
        Polygon polygon = hitbox.getPolygon();
        for (int x = 0; x < 160; x++) {
            for (int y = 0; y < 160; y++) {
                // range from xposition -5 to xposition + 5
                float xpos = xposition + (x / 16f) - 5f;
                float ypos = yposition + (y / 16f) - 5f;
                if (polygon.contains(xpos, ypos)) {
                    game.batch.draw(CastleTile.SOLID_FLOOR.getTexture(), xpos * scale, ypos * scale, 2, 2);
                }
            }
        }
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

    public void setCharacter(Character character) {
        this.character = character;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

}
