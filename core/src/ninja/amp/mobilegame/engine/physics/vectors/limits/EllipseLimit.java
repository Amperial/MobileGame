package ninja.amp.mobilegame.engine.physics.vectors.limits;

import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Vector2;

public class EllipseLimit extends Limit<Vector2> {

    private Ellipse ellipse;

    public EllipseLimit(Ellipse ellipse) {
        this.ellipse = ellipse;
    }

    @Override
    public boolean test(Vector2 value) {
        return ellipse.contains(value);
    }

    @Override
    public void apply(Vector2 value) {
        // TODO
    }

}
