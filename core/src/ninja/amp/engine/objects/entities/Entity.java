package ninja.amp.engine.objects.entities;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import ninja.amp.engine.map.World;
import ninja.amp.engine.map.collision.CollisionState;
import ninja.amp.engine.objects.body.Body;
import ninja.amp.engine.objects.entities.effects.Effect;
import ninja.amp.engine.objects.entities.stats.EntityStat;
import ninja.amp.engine.objects.entities.stats.Stat;
import ninja.amp.engine.objects.items.Item;
import ninja.amp.engine.physics.collision.Hitbox;
import ninja.amp.engine.physics.mass.Mass;
import ninja.amp.engine.physics.vectors.LVector2;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class Entity {

    private World world;
    private LVector2 position;
    private LVector2 velocity;
    private LVector2 acceleration;
    private Mass mass;

    protected Body body;
    private Hitbox hitbox;
    private float immunity;
    private CollisionState state;

    private Stat health;
    private Stat protection;
    private Stat strength;

    private float currentHealth;

    // TODO: Use equipment class for items
    private Set<Item> items;
    private Set<Effect> effects;

    private boolean dead = false;

    public Entity(World world, LVector2 position, LVector2 velocity, LVector2 acceleration, Mass mass, Stat health, Stat protection, Stat strength) {
        this.world = world;
        this.position = position;
        this.velocity = velocity;
        this.acceleration = acceleration;
        this.mass = mass;

        this.state = CollisionState.NORMAL;

        this.health = health;
        this.protection = protection;
        this.strength = strength;
        if (health instanceof EntityStat) {
            ((EntityStat) health).setEntity(this);
        }
        if (protection instanceof EntityStat) {
            ((EntityStat) protection).setEntity(this);
        }
        if (strength instanceof EntityStat) {
            ((EntityStat) strength).setEntity(this);
        }

        this.items = new HashSet<Item>();
        this.effects = new HashSet<Effect>();
    }

    public void initialize() {
        initializeBody();
        currentHealth = health.calculate();
        // Override to initialize additional code
    }

    public void initializeBody() {
        // Override to initialize entity body
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

    public Body getBody() {
        return body;
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

    public CollisionState getCollisionState() {
        return state;
    }

    public void setCollisionState(CollisionState state) {
        this.state = state;
    }

    public boolean isOnGround() {
        return world.getMap().getCollision().isOnGround(this);
    }

    public boolean isOnPlatform() {
        return world.getMap().getCollision().isOnPlatform(this);
    }

    public boolean isInPlatform() {
        return world.getMap().getCollision().isInPlatform(this);
    }

    public Stat getHealth() {
        return health;
    }

    public Stat getProtection() {
        return protection;
    }

    public Stat getStrength() {
        return strength;
    }

    public float getCurrentHealth() {
        return currentHealth;
    }

    public void setCurrentHealth(float health) {
        this.currentHealth = health;
    }

    public Set<Item> getItems() {
        return items;
    }

    public Set<Effect> getEffects() {
        return effects;
    }

    public void clearEffects() {
        effects.clear();
    }

    public void addEffect(Effect effect) {
        effects.add(effect);
    }

    public boolean isDead() {
        return dead;
    }

    public void die() {
        dead = true;
    }

    public boolean attack(float damage, float immunity) {
        if (isImmune()) {
            return false;
        } else {
            damage -= protection.calculate();
            currentHealth -= damage;
            if (currentHealth <= 0) {
                currentHealth = 0;
                die();
            } else {
                this.immunity = immunity;
            }
            return true;
        }
    }

    public void applyForce(Vector2 force) {
        acceleration.add(force);
    }

    public void update(float delta) {
        Iterator<Effect> effectIterator = effects.iterator();
        while (effectIterator.hasNext()) {
            Effect effect = effectIterator.next();
            effect.update(delta);
            if (!effect.active()) {
                effectIterator.remove();
            }
        }
        currentHealth = Math.min(currentHealth, health.calculate());

        acceleration.scl(1 / mass.getMass());
        acceleration.limit();

        velocity.add(acceleration.cpy().scl(delta));
        acceleration.setZero();
        velocity.limit();

        //position.add(velocity.cpy().scl(deltaTime));
        world.getMap().getCollision().move(this, velocity.cpy().scl(delta));
        position.limit();

        if (immunity > 0) {
            immunity -= delta;
            if (immunity < 0) {
                immunity = 0;
            }
        }
    }

    public void draw(Batch batch, float delta) {
        if (body != null) {
            body.draw(batch, delta);
        }
    }

}
