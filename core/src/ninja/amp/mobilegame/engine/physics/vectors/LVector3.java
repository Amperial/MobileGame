package ninja.amp.mobilegame.engine.physics.vectors;

import com.badlogic.gdx.math.Vector3;
import ninja.amp.mobilegame.engine.physics.vectors.limits.Limit;

public class LVector3 extends Vector3 {

    private Limit<Vector3> limit;

    public LVector3(Limit<Vector3> limit) {
        this.limit = limit;
    }

    public LVector3(float x, float y, float z, Limit<Vector3> limit) {
        super(x, y, z);
        this.limit = limit;
    }

    public LVector3(Vector3 v, Limit<Vector3> limit) {
        super(v);
        this.limit = limit;
    }

    public void limit() {
        limit.limit(this);
    }

}
