package ninja.amp.mobilegame.engine.physics.forces;

import com.badlogic.gdx.math.Vector2;
import ninja.amp.mobilegame.objects.Entity;

public class FrictionForce extends Force {

    private Vector2 force;

    private Vector2 vector = new Vector2();

    public FrictionForce(Vector2 force) {
        this.force = force;
    }

    @Override
    public Vector2 calculate(Entity entity, float delta) {
        return vector.set(entity.getVelocity()).scl(force).scl(-1);
    }

}
