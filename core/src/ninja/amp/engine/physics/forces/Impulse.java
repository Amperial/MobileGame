package ninja.amp.engine.physics.forces;

import com.badlogic.gdx.math.Vector2;
import ninja.amp.engine.objects.entities.Entity;

public class Impulse extends Force {

    private Vector2 force;

    private Vector2 vector = new Vector2();

    public Impulse(Vector2 force) {
        this.force = force;
    }

    @Override
    public Vector2 calculate(Entity entity, float delta) {
        return vector.set(force).scl(1 / delta);
    }

}
