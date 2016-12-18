package ninja.amp.engine.physics.forces;

import com.badlogic.gdx.math.Vector2;
import ninja.amp.engine.objects.entities.Entity;

public class StoppingForce extends Impulse {

    public StoppingForce(Vector2 force) {
        super(force);
    }

    @Override
    public Vector2 calculate(Entity entity, float delta) {
        return super.calculate(entity, delta).scl(entity.getVelocity()).scl(-1);
    }

}
