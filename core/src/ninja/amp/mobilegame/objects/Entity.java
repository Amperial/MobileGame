package ninja.amp.mobilegame.objects;

import com.badlogic.gdx.math.Vector2;
import ninja.amp.mobilegame.engine.physics.collision.Hitbox;
import ninja.amp.mobilegame.engine.physics.mass.Mass;
import ninja.amp.mobilegame.engine.physics.vectors.LVector2;
import ninja.amp.mobilegame.map.World;

public class Entity {

    private World world;
    private LVector2 position;
    private LVector2 velocity;
    private LVector2 acceleration;
    private Mass mass;

    private Hitbox hitbox;
    private float immunity;

    public Entity(World world, LVector2 position, LVector2 velocity, LVector2 acceleration, Mass mass) {
        this.world = world;
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
        this.mass = mass;
    }

    public World getWorld() {
        return world;
    }

    public LVector2 getPosition() {
        return position;
    }

    public void setPosition(LVector2 position) {
        this.position = position;
    }

    public LVector2 getVelocity() {
        return velocity;
    }

    public void setVelocity(LVector2 velocity) {
        this.velocity = velocity;
    }

    public LVector2 getAcceleration() {
        return acceleration;
    }

    public void setAcceleration(LVector2 acceleration) {
        this.acceleration = acceleration;
    }

    public float getMass() {
        return mass.getMass();
    }

    public void setMass(Mass mass) {
        this.mass = mass;
    }

    public Hitbox getHitbox() {
        return hitbox;
    }

    public void setHitbox(Hitbox hitbox) {
        this.hitbox = hitbox;
    }

    public boolean isImmune() {
        return immunity > 0;
    }

    public boolean attack(float immunity) {
        if (isImmune()) {
            return false;
        } else {
            this.immunity = immunity;
            return true;
        }
    }

    public void applyForce(Vector2 force) {
        acceleration.add(force);
    }

    public void update(float deltaTime) {
        acceleration.scl(1 / mass.getMass());
        acceleration.limit();

        velocity.add(acceleration.cpy().scl(deltaTime));
        acceleration.setZero();
        velocity.limit();

        //position.add(velocity.cpy().scl(deltaTime));
        world.map.move(this, velocity.cpy().scl(deltaTime));
        position.limit();

        if (immunity > 0) {
            immunity -= deltaTime;
            if (immunity < 0) {
                immunity = 0;
            }
        }
    }

}
