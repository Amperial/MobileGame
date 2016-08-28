package ninja.amp.mobilegame.objects.characters.npc.ai.actions.movement;

import com.badlogic.gdx.math.Vector2;
import ninja.amp.mobilegame.objects.Entity;

public abstract class Wander extends Follow {

    private Entity entity;
    private Vector2 anchor;
    private Vector2 target;

    private Vector2 temp = new Vector2();

    public Wander(Entity entity, float springConstant, Vector2 anchor) {
        super(entity, springConstant);

        this.entity = entity;
        this.anchor = anchor;
        this.target = anchor.cpy();
    }

    public Vector2 getAnchor() {
        return anchor;
    }

    public abstract Vector2 newTarget();

    @Override
    public Vector2 getTarget() {
        if (entity.getPosition().epsilonEquals(target, 0.001f)) {
            target = newTarget();
        }
        return temp.set(target);
    }

}
