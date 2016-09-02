package ninja.amp.mobilegame.engine.physics.forces;

import com.badlogic.gdx.math.Vector2;
import ninja.amp.mobilegame.objects.Entity;

public class GravitationalForce extends Force {

    private Entity target;
    private float strength;

    private Vector2 vector = new Vector2();

    public GravitationalForce(Entity target, float strength) {
        this.target = target;
        this.strength = strength;
    }

    @Override
    public Vector2 calculate(Entity entity, float delta) {
        vector.set(target.getPosition()).sub(entity.getPosition());
        float force = strength * target.getMass() * entity.getMass() / vector.len2();
        return vector.nor().scl(force);
    }

}
