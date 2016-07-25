package ninja.amp.mobilegame.map;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import ninja.amp.mobilegame.MobileGame;
import ninja.amp.mobilegame.engine.physics.collision.RectangleHitbox;
import ninja.amp.mobilegame.engine.physics.forces.SimpleForce;
import ninja.amp.mobilegame.engine.physics.mass.StaticMass;
import ninja.amp.mobilegame.engine.physics.vectors.LVector2;
import ninja.amp.mobilegame.engine.physics.vectors.limits.Limit;
import ninja.amp.mobilegame.objects.Entity;
import ninja.amp.mobilegame.objects.characters.Character;
import ninja.amp.mobilegame.engine.physics.forces.Force;

import java.util.HashSet;
import java.util.Set;

public class World {

    private MobileGame game;
    private Character character;

    private Set<Entity> entities = new HashSet<Entity>();
    private Force gravity = new SimpleForce(new Vector2(0, -20f), false);

    public World(MobileGame game) {
        this.game = game;

        // load map, character, etc.
        character = new Character(this,
                new LVector2(Limit.NONE),   // TODO: Position
                new LVector2(Limit.NONE),   // TODO: Velocity
                new LVector2(Limit.NONE),   // TODO: Acceleration
                new StaticMass(1),          // TODO: Mass
                new RectangleHitbox(new Rectangle(0, 0, 1, 1)) {
                    @Override
                    public Vector2 getPosition() {
                        return character.getPosition();
                    }
                });
        entities.add(character);
    }

    public void update(float delta) {
        if (delta > 0.05f) {
            delta = 0.05f;
        }

        for (Entity entity : entities) {
            entity.applyForce(gravity.calculate(entity, delta));
            entity.update(delta);
        }
    }

    public void render(float delta) {
    }

    public Character getCharacter() {
        return character;
    }

}
