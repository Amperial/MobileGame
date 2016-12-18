package ninja.amp.engine.objects.entities.npc.ai.actions.movement;

import com.badlogic.gdx.math.Vector2;
import ninja.amp.engine.objects.entities.Entity;
import ninja.amp.engine.objects.entities.npc.ai.actions.Action;
import ninja.amp.engine.objects.entities.npc.ai.actions.range.Range;
import ninja.amp.engine.physics.forces.Force;

public abstract class Follow implements Action {

    private Entity entity;
    private Range range;
    private Force force;

    public Follow(Entity entity, final float springConstant, Range range) {
        this.entity = entity;
        this.range = range;

        force = new Force() {
            Vector2 vector = new Vector2();

            @Override
            public Vector2 calculate(Entity entity, float delta) {
                return vector.set(entity.getVelocity()).scl(-2).add(getTarget().sub(entity.getPosition()).scl(springConstant)).scl(springConstant);
            }
        };
    }

    public Follow(Entity entity, float springConstant) {
        this(entity, springConstant, Range.NONE);
    }

    public Range getRange() {
        return range;
    }

    @Override
    public boolean canPerform() {
        return range.inRange(getTarget());
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
        return false;
    }

    public abstract Vector2 getTarget();

}
