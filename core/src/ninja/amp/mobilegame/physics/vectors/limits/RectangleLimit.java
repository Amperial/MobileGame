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
    }

}
