package ninja.amp.engine.objects.entities.npc.ai.actions.movement;

import com.badlogic.gdx.math.Vector2;
import ninja.amp.engine.objects.entities.Entity;

public class Patrol extends Follow {

    private Entity entity;
    private Vector2[] targets;
    private int current = 0;

    private Vector2 temp = new Vector2();

    public Patrol(Entity entity, float springConstant, Vector2... targets) {
        super(entity, springConstant);

        this.entity = entity;
        this.targets = targets;
    }

    @Override
    public Vector2 getTarget() {
        if (entity.getPosition().epsilonEquals(targets[current], 0.01f)) {
            current = (current + 1) % targets.length;
        }
        return temp.set(targets[current]);
    }

}
