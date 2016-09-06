package ninja.amp.mobilegame.objects.characters.npc.ai.actions.range;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class RadiusRange extends Range {

    private float radius;

    public RadiusRange(Vector2 anchor, float radius) {
        super(anchor);

        this.radius = radius;
    }

    public float getRadius() {
        return radius;
    }

    @Override
    public boolean inRange(Vector2 vector) {
        Vector2 anchor = getAnchor();
        return Math.abs(vector.x - anchor.x) <= radius && Math.abs(vector.y - anchor.y) <= radius;
    }

    @Override
    public Vector2 randomInRange(Vector2 vector) {
        return vector.set(getAnchor()).add(MathUtils.random(radius) - (radius / 2), MathUtils.random(radius) - (radius / 2));
    }

}
