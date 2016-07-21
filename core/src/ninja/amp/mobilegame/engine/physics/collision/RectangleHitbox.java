package ninja.amp.mobilegame.engine.physics.collision;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;

public abstract class RectangleHitbox extends Shape2DHitbox {

    private Rectangle rectangle;

    public RectangleHitbox(Rectangle rectangle) {
        this.rectangle = rectangle;
    }

    @Override
    public Shape2D getShape() {
        return rectangle.setPosition(getPosition());
    }

    public abstract Vector2 getPosition();

}
