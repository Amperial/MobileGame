package ninja.amp.mobilegame.physics.forces;

import com.badlogic.gdx.math.Vector2;
import ninja.amp.mobilegame.objects.Entity;

public class Impulse extends Force {

    private Vector2 force;

    public Impulse(Vector2 force) {
        this.force = force;
    }

    @Override
    public Vector2 calculate(Entity entity, float delta) {
        return force.scl(entity.getMass());
    }

    @Override
    public boolean persist(Entity entity, float delta) {
        return false;
    }

}
