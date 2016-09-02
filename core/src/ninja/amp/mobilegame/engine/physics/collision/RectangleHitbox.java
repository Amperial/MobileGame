package ninja.amp.mobilegame.engine.physics.collision;

import com.badlogic.gdx.math.Polygon;
import com.badlogic.gdx.math.Rectangle;

public class RectangleHitbox extends PolygonHitbox {

    private Rectangle rectangle;

    public RectangleHitbox(Rectangle rectangle) {
        super(new Polygon(rectangleVertices(rectangle)));

        this.rectangle = rectangle;
    }

    public Rectangle getRectangle() {
        return rectangle;
    }

    public static float[] rectangleVertices(Rectangle rectangle) {
        float left = rectangle.getX();
        float right = left + rectangle.getWidth();
        float bottom = rectangle.getY();
        float top = bottom + rectangle.getHeight();
        return new float[]{
                left, bottom,
                right, bottom,
                right, top,
                left, top
        };
    }

}
