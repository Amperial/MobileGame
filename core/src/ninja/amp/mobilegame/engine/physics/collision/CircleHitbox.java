package ninja.amp.mobilegame.engine.physics.collision;

import com.badlogic.gdx.math.Circle;
import com.badlogic.gdx.math.Shape2D;
import com.badlogic.gdx.math.Vector2;

public abstract class CircleHitbox extends Shape2DHitbox {

    private Circle circle;

    public CircleHitbox(Circle circle) {
        this.circle = circle;
    }

    @Override
    public Shape2D getShape() {
        circle.setPosition(getPosition());
        return circle;
    }

    public abstract Vector2 getPosition();

}
