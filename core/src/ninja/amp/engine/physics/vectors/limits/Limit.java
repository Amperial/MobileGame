package ninja.amp.engine.physics.vectors.limits;

import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class Limit<T extends Vector<T>> {

    public static Limit<Vector2> VEC2 = new Limit<Vector2>();

    public static Limit<Vector3> VEC3 = new Limit<Vector3>();

    public void limit(T value) {
        if (!test(value)) {
            apply(value);
        }
    }

    public boolean test(T value) {
        return true;
    }

    public void apply(T value) {
    }

}
