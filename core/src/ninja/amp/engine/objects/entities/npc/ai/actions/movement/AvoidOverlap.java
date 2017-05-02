package ninja.amp.engine.objects.entities.npc.ai.actions.movement;

import com.badlogic.gdx.math.Vector2;
import ninja.amp.engine.map.World;
import ninja.amp.engine.objects.entities.Entity;
import ninja.amp.engine.objects.entities.npc.ai.actions.Action;
import ninja.amp.engine.physics.collision.Hitbox;
import ninja.amp.engine.physics.collision.RectangleHitbox;
import ninja.amp.engine.physics.forces.Force;

// TODO: REMOVE
public class AvoidOverlap implements Action {

    private World world;
    private Entity entity;
    private Force force;

    public AvoidOverlap(final World world, Entity entity) {
        this.world = world;
        this.entity = entity;

        force = new Force() {
            Vector2 vector = new Vector2();

            @Override
            public Vector2 calculate(Entity entity, float delta) {
                vector.setZero();
                Hitbox hitbox = entity.getHitbox();
                for (Entity worldEntity : world.getEntities()) {
                    if (worldEntity != entity && hitbox.intersects(worldEntity.getHitbox())) {

                        float x = entity.getPosition().x;
                        if (hitbox instanceof RectangleHitbox) {
                            x += ((RectangleHitbox) hitbox).getRectangle().getWidth() / 2;
                        }

                        float entityX = worldEntity.getPosition().x;
                        Hitbox entityHitbox = worldEntity.getHitbox();
                        if (entityHitbox instanceof RectangleHitbox) {
                            entityX += ((RectangleHitbox) entityHitbox).getRectangle().getWidth() / 2;
                        }

                        if (x - entityX == 0) {
                            vector.add((float)Math.random() - 0.5f, 0);
                        } else {
                            vector.add(x - entityX, 0);
                        }
                    }
                }
                return vector.scl(1000);
            }
        };
    }

    @Override
    public boolean canPerform() {
        Hitbox hitbox = entity.getHitbox();
        float x = entity.getPosition().x;
        if (hitbox instanceof RectangleHitbox) {
            x += ((RectangleHitbox) hitbox).getRectangle().getWidth() / 2;
        }
        for (Entity worldEntity : world.getEntities()) {
            if (worldEntity != entity && hitbox.intersects(worldEntity.getHitbox())) {
                float entityX = worldEntity.getPosition().x;
                Hitbox entityHitbox = worldEntity.getHitbox();
                if (entityHitbox instanceof RectangleHitbox) {
                    entityX += ((RectangleHitbox) entityHitbox).getRectangle().getWidth() / 2;
                }
                if (Math.abs(x - entityX) < 1) {
                    return true;
                }
                //if (Math.abs(x - entityX) < 0.1f) {
                //    return true;
                //}
            }
        }
        return false;
    }

    @Override
    public void begin() {
    }

    @Override
    public void perform(float deltaTime) {
        entity.applyForce(force.calculate(entity, deltaTime));
    }

    @Override
    public void cancel() {
    }

    @Override
    public boolean isComplete() {
        return !canPerform();
    }

}
