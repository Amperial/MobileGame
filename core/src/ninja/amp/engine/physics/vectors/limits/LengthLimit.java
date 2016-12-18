package ninja.amp.engine.physics.vectors.limits;

import com.badlogic.gdx.math.Vector;

public class LengthLimit<T extends Vector<T>> extends Limit<T> {

    private float length2;

    public LengthLimit(float length) {
        this.length2 = length * length;
    }

    @Override
    public boolean test(T value) {
        return length2 >= value.len2();
    }

    @Override
    public void apply(T value) {
        value.limit2(length2);
    }

}
