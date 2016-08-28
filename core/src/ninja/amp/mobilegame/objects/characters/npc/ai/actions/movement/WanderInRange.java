package ninja.amp.mobilegame.objects.characters.npc.ai.actions.movement;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import ninja.amp.mobilegame.objects.Entity;

public class WanderInRange extends Wander {

    private float radius;
    private Vector2 target = new Vector2();

    public WanderInRange(Entity entity, float springConstant, Vector2 anchor, float radius) {
        super(entity, springConstant, anchor);

        this.radius = radius;
    }

    @Override
    public Vector2 newTarget() {
        target.set(getAnchor());
        target.add(MathUtils.random(radius) - (radius / 2), MathUtils.random(radius) - (radius / 2));
        System.out.println("Anchor: " + getAnchor() + ", radius: " + radius);
        System.out.println("New target: " + target);
        return target;
    }

}
