package ninja.amp.mobilegame.engine.physics.collision;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Polygon;

public class CircleHitbox extends PolygonHitbox {

    private Circle circle;

    public CircleHitbox(Circle circle, int vertices) {
        super(new Polygon(circleVertices(circle, vertices)));

        this.circle = circle;
    }

    public Circle getCircle() {
        return circle;
    }

    public static float[] circleVertices(Circle circle, int vertices) {
        float radius = circle.radius;
        float angle = -360f / (float) vertices;
        float[] verts = new float[vertices * 2];
        for (int i = 0; i < vertices; i++) {
            verts[i * 2] = radius * MathUtils.sinDeg(i * angle);
            verts[i * 2 + 1] = radius * MathUtils.cosDeg(i * angle);
        }
        return verts;
    }

}
