package ninja.amp.mobilegame.objects;

import com.badlogic.gdx.math.Vector2;
import ninja.amp.mobilegame.map.World;
import ninja.amp.mobilegame.physics.vectors.LVector2;

public class Entity {

    private World world;
    private Vector2 position;
    private LVector2 velocity;
    private LVector2 acceleration;
    private float mass;

    public Entity(World world, Vector2 position, LVector2 velocity, LVector2 acceleration, float mass) {
        this.world = world;
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
        this.mass = mass;
    }

    public World getWorld() {
        return world;
    }

    public Vector2 getPosition() {
        return position;
    }

    public LVector2 getVelocity() {
        return velocity;
    }

    public LVector2 getAcceleration() {
        return acceleration;
    }

    public float getMass() {
        return mass;
    }

    public void setMass(float mass) {
        this.mass = mass;
    }

    public void applyForce(Vector2 force) {
        acceleration.add(force);
    }

    public void update(float deltaTime) {
        acceleration.scl(1 / mass);
        acceleration.limit();

        velocity.add(acceleration.cpy().scl(deltaTime));
        acceleration.setZero();
        velocity.limit();
        
        position.add(velocity.cpy().scl(deltaTime));
    }

}
