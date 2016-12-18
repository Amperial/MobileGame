package ninja.amp.engine.objects.entities.npc.ai.actions.range;

import com.badlogic.gdx.math.Vector2;

public abstract class Range {

    public static final Range NONE = new Range(new Vector2()) {
        @Override
        public boolean inRange(Vector2 vector) {
            return true;
        }

        @Override
        public Vector2 randomInRange(Vector2 vector) {
            return vector;
        }
    };

    private Vector2 anchor;

    public Range(Vector2 anchor) {
        this.anchor = anchor;
    }

    public Vector2 getAnchor() {
        return anchor;
    }

    public abstract boolean inRange(Vector2 vector);

    public abstract Vector2 randomInRange(Vector2 vector);

}
