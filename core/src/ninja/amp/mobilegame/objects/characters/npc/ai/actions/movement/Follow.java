package ninja.amp.mobilegame.objects.characters.npc.ai.actions.movement;

import com.badlogic.gdx.math.Vector2;
import ninja.amp.mobilegame.engine.physics.forces.Force;
import ninja.amp.mobilegame.objects.Entity;
import ninja.amp.mobilegame.objects.characters.npc.ai.actions.Action;

public abstract class Follow implements Action {

    private Entity entity;
    private Force force;

    public Follow(Entity entity, final float springConstant) {
        this.entity = entity;

        force = new Force() {
            Vector2 vector = new Vector2();
            @Override
            public Vector2 calculate(Entity entity, float delta) {
                return vector.set(entity.getVelocity()).scl(-2).add(getTarget().sub(entity.getPosition()).scl(springConstant)).scl(springConstant);
            }
        };
    }

    @Override
    public boolean canPerform() {
        return true;
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
