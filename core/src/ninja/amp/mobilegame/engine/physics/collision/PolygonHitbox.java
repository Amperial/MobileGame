package ninja.amp.mobilegame.engine.physics.collision;

import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Polygon;

public class PolygonHitbox implements Hitbox {

    private Polygon polygon;

    public PolygonHitbox(Polygon polygon) {
        this.polygon = polygon;
    }

    public Polygon getPolygon() {
        polygon.setOrigin(getOriginX(), getOriginY());
        polygon.setPosition(getX(), getY());
        polygon.setRotation(getRotation());
        polygon.dirty();
        return polygon;
    }

    public float getX() {
        return 0;
    }

    public float getY() {
        return 0;
    }

    public float getOriginX() {
        return 0;
    }

    public float getOriginY() {
        return 0;
    }

    public float getRotation() {
        return 0;
    }

    @Override
    public boolean intersects(Hitbox hitbox) {
        if (hitbox instanceof PolygonHitbox) {
            return Intersector.overlapConvexPolygons(getPolygon(), ((PolygonHitbox) hitbox).getPolygon());
        } else {
            return hitbox.intersects(this);
        }
    }

}
