package ninja.amp.engine.graphics.gui;

import com.badlogic.gdx.math.Vector2;

public class StaticOffset implements Offset {

    private Vector2 offset;

    public StaticOffset(float x, float y) {
        this.offset = new Vector2(x, y);
    }

    public StaticOffset(Vector2 offset) {
        this.offset = offset;
    }

    @Override
    public Vector2 getOffset() {
        return offset;
    }

}
