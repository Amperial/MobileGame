package ninja.amp.engine.objects.entities.npc.ai.actions.movement;

import com.badlogic.gdx.math.Vector2;
import ninja.amp.engine.objects.entities.Entity;
import ninja.amp.engine.objects.entities.npc.ai.actions.range.Range;

public class FollowEntity extends Follow {

    private Entity target;
    private Vector2 offset;

    private Vector2 temp = new Vector2();

    public FollowEntity(Entity entity, Entity target, Vector2 offset, float springConstant, Range range) {
        super(entity, springConstant, range);

        this.target = target;
        this.offset = offset;
    }

    public FollowEntity(Entity entity, Entity target, Vector2 offset, float springConstant) {
        super(entity, springConstant);

        this.target = target;
        this.offset = offset;
    }

    @Override
    public Vector2 getTarget() {
        return temp.set(target.getPosition()).add(offset);
    }

}
