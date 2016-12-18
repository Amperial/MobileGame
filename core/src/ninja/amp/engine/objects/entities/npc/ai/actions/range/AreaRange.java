package ninja.amp.engine.objects.entities.npc.ai.actions.range;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class AreaRange extends Range {

    private Rectangle area;

    public AreaRange(Vector2 anchor, Rectangle area) {
        super(anchor);

        this.area = area;
    }

    @Override
    public boolean inRange(Vector2 vector) {
        return area.setPosition(getAnchor()).contains(vector);
    }

    @Override
    public Vector2 randomInRange(Vector2 vector) {
        vector.x = MathUtils.random(area.getX(), area.getX() + area.getWidth());
        vector.y = MathUtils.random(area.getY(), area.getY() + area.getHeight());
        return vector;
    }

}
