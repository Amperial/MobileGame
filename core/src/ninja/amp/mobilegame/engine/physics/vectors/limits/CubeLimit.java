package ninja.amp.mobilegame.engine.physics.vectors.limits;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector3;

public class CubeLimit extends Limit<Vector3> {

    private Vector3 lowerCorner;
    private Vector3 upperCorner;

    public CubeLimit(Vector3 lowerCorner, Vector3 upperCorner) {
        this.lowerCorner = lowerCorner;
        this.upperCorner = upperCorner;
    }

    @Override
    public boolean test(Vector3 value) {
        return value.x >= lowerCorner.x &&
                value.y >= lowerCorner.y &&
                value.z >= lowerCorner.z &&
                value.x <= upperCorner.x &&
                value.y <= upperCorner.y &&
                value.z <= upperCorner.z;
    }

    @Override
    public void apply(Vector3 value) {
        value.x = MathUtils.clamp(value.x, lowerCorner.x, upperCorner.x);
        value.y = MathUtils.clamp(value.y, lowerCorner.y, upperCorner.y);
        value.z = MathUtils.clamp(value.z, lowerCorner.z, upperCorner.z);
    }

}
