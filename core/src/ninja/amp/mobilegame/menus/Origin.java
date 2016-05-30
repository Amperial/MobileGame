package ninja.amp.mobilegame.menus;

import com.badlogic.gdx.math.Vector2;

public enum Origin {
    TOP_LEFT(new Vector2(0f, 1f)),
    TOP(new Vector2(0.5f, 1f)),
    TOP_RIGHT(new Vector2(1f, 1f)),
    LEFT(new Vector2(0f, 0.5f)),
    CENTER(new Vector2(0.5f, 0.5f)),
    RIGHT(new Vector2(1f, 0.5f)),
    BOTTOM_LEFT(new Vector2(0f, 0f)),
    BOTTOM(new Vector2(0.5f, 0f)),
    BOTTOM_RIGHT(new Vector2(1f, 0f));

    private Vector2 position;

    Origin(Vector2 position) {
        this.position = position;
    }

    public float getX() {
        return position.x;
    }

    public float getY() {
        return position.y;
    }

}
