package ninja.amp.engine.objects.entities.npc.ai.actions.movement;

import com.badlogic.gdx.math.Vector2;
import ninja.amp.engine.objects.entities.Entity;
import ninja.amp.engine.objects.entities.npc.ai.actions.Action;
import ninja.amp.engine.physics.forces.Force;

public class Stop implements Action {

    private Entity entity;
    private Force force;
    private boolean yAxis;

    public Stop(Entity entity, final float acceleration, boolean yAxis) {
        this.entity = entity;
        this.yAxis = yAxis;

        if (yAxis) {
            force = new Force() {
                Vector2 vector = new Vector2();

                @Override
                public Vector2 calculate(Entity entity, float delta) {
                    return vector.set(entity.getVelocity()).scl(-acceleration);
                }
            };
        } else {
            force = new Force() {
                Vector2 vector = new Vector2();

                @Override
                public Vector2 calculate(Entity entity, float delta) {
                    return vector.set(entity.getVelocity().x, 0).scl(-acceleration);
                }
            };
        }
    }

    @Override
    public boolean canPerform() {
        return yAxis || entity.isOnGround();
    }

    @Override
    public void begin() {
    }

    @Override
    public void perform(float deltaTime) {
        entity.applyForce(force.calculate(entity, deltaTime));
        if (Math.abs(entity.getVelocity().x) < 0.01f) {
            entity.getVelocity().x = 0;
        }
        if (yAxis && Math.abs(entity.getVelocity().y) < 0.01f) {
            entity.getVelocity().y = 0;
        }
    }

    @Override
    public void cancel() {
    }

    @Override
    public boolean isComplete() {
        return yAxis ? entity.getVelocity().isZero() : entity.getVelocity().x == 0;
    }

}
