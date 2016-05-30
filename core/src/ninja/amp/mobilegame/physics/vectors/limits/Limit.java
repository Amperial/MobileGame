package ninja.amp.mobilegame.physics.vectors.limits;

import com.badlogic.gdx.math.Vector;
import com.badlogic.gdx.math.Vector2;

public interface Limit<T extends Vector<T>> {

    Limit<Vector2> NONE = new Limit<Vector2>() {
        @Override
        public void apply(Vector2 value) {
        }
    };

    void apply(T value);

}
