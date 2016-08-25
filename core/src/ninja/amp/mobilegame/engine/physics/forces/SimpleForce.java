package ninja.amp.mobilegame.engine.physics.forces;

import com.badlogic.gdx.math.Vector2;
import ninja.amp.mobilegame.objects.Entity;

public class SimpleForce extends Force {

    private Vector2 force;
    private boolean persist;

    public SimpleForce(Vector2 force, boolean persist) {
        this.force = force;
        this.persist = persist;
    }

    @Override
    public Vector2 calculate(Entity entity, float delta) {
        return force;
    }

    @Override
    public boolean persist(Entity entity, float delta) {
        return persist;
    }

}
