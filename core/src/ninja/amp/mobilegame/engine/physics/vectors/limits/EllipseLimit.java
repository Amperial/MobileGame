package ninja.amp.mobilegame.engine.physics.vectors.limits;

import com.badlogic.gdx.math.Ellipse;
import com.badlogic.gdx.math.Vector2;

public class EllipseLimit implements Limit<Vector2> {

    private Ellipse ellipse;

    public EllipseLimit(Ellipse ellipse) {
        this.ellipse = ellipse;
    }

    @Override
    public void apply(Vector2 value) {
        // TODO
    }

}
