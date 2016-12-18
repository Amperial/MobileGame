package ninja.amp.engine.physics.forces;

import com.badlogic.gdx.math.Vector2;
import ninja.amp.engine.objects.entities.Entity;

public class MultiForce extends Force {

    private Force[] forces;

    private Vector2 vector = new Vector2();

    public MultiForce(Force... forces) {
        this.forces = forces;
    }

    @Override
    public Vector2 calculate(Entity entity, float delta) {
        vector.setZero();
        for (Force force : forces) {
            vector.add(force.calculate(entity, delta));
        }
        return vector;
    }

}
