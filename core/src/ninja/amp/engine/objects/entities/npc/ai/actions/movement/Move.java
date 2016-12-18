package ninja.amp.engine.objects.entities.npc.ai.actions.movement;

import com.badlogic.gdx.math.Vector2;
import ninja.amp.engine.objects.entities.Entity;
import ninja.amp.engine.objects.entities.npc.ai.actions.Action;
import ninja.amp.engine.physics.forces.Force;

public class Move implements Action {

    private Entity entity;
    private Vector2 target;
    private Force force;

    public Move(Entity entity, Vector2 position, final float springConstant) {
        this.entity = entity;
        this.target = position;

        force = new Force() {
            Vector2 vector = new Vector2();

            @Override
            public Vector2 calculate(Entity entity, float delta) {
                return vector.set(entity.getVelocity()).scl(-2).add(target.sub(entity.getPosition()).scl(springConstant)).scl(springConstant);
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
        return entity.getPosition().epsilonEquals(target, 0.01f);
    }

}
