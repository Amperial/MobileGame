package ninja.amp.mobilegame.physics.vectors.limits;

import com.badlogic.gdx.math.Vector;

public class LengthLimit<T extends Vector<T>> implements Limit<T> {

    private float length2;

    public LengthLimit(float length) {
        this.length2 = length * length;
    }

    @Override
    public void apply(T value) {
        value.limit2(length2);
    }

}
