package ninja.amp.mobilegame.objects.characters.npc.ai.actions.movement;

import com.badlogic.gdx.math.Vector2;
import ninja.amp.mobilegame.objects.Entity;
import ninja.amp.mobilegame.objects.characters.npc.ai.actions.range.Range;

public class Wander extends Follow {

    private Entity entity;
    private Vector2 target;

    private Vector2 temp = new Vector2();

    public Wander(Entity entity, float springConstant, Range range) {
        super(entity, springConstant, range);

        this.entity = entity;
        this.target = range.getAnchor().cpy();
    }

    public Vector2 newTarget() {
        return getRange().randomInRange(target);
    }

    @Override
    public Vector2 getTarget() {
        if (entity.getPosition().epsilonEquals(target, 0.01f) || entity.getVelocity().isZero(0.0001f)) {
            target = newTarget();
        }
        return temp.set(target);
    }

}
