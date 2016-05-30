package ninja.amp.mobilegame.physics.forces;

import com.badlogic.gdx.math.Vector2;
import ninja.amp.mobilegame.objects.Entity;

public class GravitationalForce extends Force {

    private Entity target;
    private float strength;
    
    public GravitationalForce(Entity target, float strength) {
        this.target = target;
        this.strength = strength;
    }

    @Override
    public Vector2 calculate(Entity entity, float delta) {
        Vector2 distance = target.getPosition().cpy().sub(entity.getPosition());
        float force = strength * target.getMass() * entity.getMass() / distance.len2();
        return distance.nor().scl(force);
    }

    @Override
    public boolean persist(Entity entity, float delta) {
        return true;
    }

}
