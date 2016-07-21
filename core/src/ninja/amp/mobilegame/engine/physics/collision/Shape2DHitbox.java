package ninja.amp.mobilegame.engine.physics.collision;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Intersector;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;

public abstract class Shape2DHitbox implements Hitbox {

    public abstract Shape2D getShape();

    @Override
    public boolean intersects(Hitbox hitbox) {
        if (hitbox instanceof Shape2DHitbox) {
            return overlaps(getShape(), ((Shape2DHitbox) hitbox).getShape());
        } else {
            return hitbox.intersects(this);
        }
    }

    public static boolean overlaps(Shape2D s1, Shape2D s2) {
        if (s1 instanceof Rectangle) {
            if (s2 instanceof Rectangle) {
                return Intersector.overlaps((Rectangle) s1, (Rectangle) s2);
            } else if (s2 instanceof Circle) {
                return Intersector.overlaps((Circle) s2, (Rectangle) s1);
            }
        } else if (s1 instanceof Circle) {
            if (s2 instanceof Rectangle) {
                return Intersector.overlaps((Circle) s1, (Rectangle) s2);
            } else if (s2 instanceof Circle) {
                return Intersector.overlaps((Circle) s1, (Circle) s2);
            }
        }
        return false;
    }

}
