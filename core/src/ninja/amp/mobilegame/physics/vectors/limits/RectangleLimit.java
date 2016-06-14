package ninja.amp.mobilegame.physics.vectors.limits;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class RectangleLimit implements Limit<Vector2> {

    private Rectangle rectangle;

    public RectangleLimit(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    @Override
    public void apply(Vector2 value) {
        // TODO
        value.x = clamp(value.x, rectangle.getX(), rectangle.getX() + rectangle.getWidth());
        value.y = clamp(value.y, rectangle.getY(), rectangle.getY() + rectangle.getHeight());
    }

    private float clamp(float value, float min, float max) {
        if (value < min) {
            return min;
        } else if (value > max) {
            return max;
        } else {
            return value;
        }
    }
}
