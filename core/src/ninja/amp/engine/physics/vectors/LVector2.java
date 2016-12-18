package ninja.amp.engine.physics.vectors;

import com.badlogic.gdx.math.Vector2;
import ninja.amp.engine.physics.vectors.limits.Limit;

public class LVector2 extends Vector2 {

    private Limit<Vector2> limit;

    public LVector2(Limit<Vector2> limit) {
        this.limit = limit;
    }

    public LVector2(float x, float y, Limit<Vector2> limit) {
        super(x, y);
        this.limit = limit;
    }

    public LVector2(Vector2 v, Limit<Vector2> limit) {
        super(v);
        this.limit = limit;
    }

    public void limit() {
        limit.limit(this);
    }

}
