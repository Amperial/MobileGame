package ninja.amp.engine.objects.entities.npc.ai.actions.movement;

import com.badlogic.gdx.math.Vector2;
import ninja.amp.engine.objects.entities.Entity;
import ninja.amp.engine.objects.entities.npc.ai.actions.Action;
import ninja.amp.engine.objects.entities.npc.ai.actions.range.Range;
import ninja.amp.engine.physics.forces.Force;

public abstract class Jump implements Action {

    private Entity entity;
    private Force force;
    private Range range;
    private boolean jumped;

    public Jump(Entity entity, final float force, final float horizontal, Range range) {
        this.entity = entity;
        this.range = range;

        this.force = new Force() {
            Vector2 vector = new Vector2();

            @Override
            public Vector2 calculate(Entity entity, float delta) {
                return vector.set(getTarget().x > entity.getPosition().x ? horizontal : -horizontal, force).scl(1 / delta);
            }
        };
    }

    public Range getRange() {
        return range;
    }

    @Override
    public boolean canPerform() {
        return range.inRange(getTarget()) && (jumped || entity.isOnGround());
    }

    @Override
    public void begin() {
        jumped = false;
    }

    @Override
    public void perform(float deltaTime) {
        if (!jumped) {
            entity.applyForce(force.calculate(entity, deltaTime));
            jumped = true;
        }
    }

    @Override
    public void cancel() {
        jumped = false;
    }

    @Override
    public boolean isComplete() {
        return jumped && entity.isOnGround();
    }

    public abstract Vector2 getTarget();

}
