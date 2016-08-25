package ninja.amp.mobilegame.engine.physics.vectors.limits;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class RectangleLimit extends Limit<Vector2> {

    private Rectangle rectangle;

    public RectangleLimit(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    @Override
    public boolean test(Vector2 value) {
        return rectangle.contains(value);
    }

    @Override
    public void apply(Vector2 value) {
        value.x = MathUtils.clamp(value.x, rectangle.getX(), rectangle.getX() + rectangle.getWidth());
        value.y = MathUtils.clamp(value.y, rectangle.getY(), rectangle.getY() + rectangle.getHeight());
    }

}
