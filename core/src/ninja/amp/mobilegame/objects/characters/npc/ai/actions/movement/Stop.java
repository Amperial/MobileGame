package ninja.amp.mobilegame.objects.characters.npc.ai.actions.movement;

import com.badlogic.gdx.math.Vector2;
import ninja.amp.mobilegame.engine.physics.forces.Force;
import ninja.amp.mobilegame.objects.Entity;
import ninja.amp.mobilegame.objects.characters.npc.ai.actions.Action;

public class Stop implements Action {

    private Entity entity;
    private Force force;

    public Stop(Entity entity, final float acceleration, boolean yAxis) {
        this.entity = entity;

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

}
