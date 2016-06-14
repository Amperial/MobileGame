package ninja.amp.mobilegame.gui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;

public abstract class Object implements Anchor {

    private Anchor anchor;
    private Origin origin;
    private Vector2 offset;
    private float scale;

    public Object(Anchor anchor, Origin origin, Vector2 offset) {
        this.anchor = anchor;
        this.origin = origin;
        this.offset = offset;
    }

    public Object(Anchor anchor, Origin origin) {
        this(anchor, origin, Vector2.Zero);
    }

    public Object(Anchor anchor, Vector2 offset) {
        this(anchor, Origin.CENTER, offset);
    }

    public Object(Anchor anchor) {
        this(anchor, Origin.CENTER, Vector2.Zero);
    }

    public Anchor getAnchor() {
        return anchor;
    }

    public void setAnchor(Anchor anchor) {
        this.anchor = anchor;
    }

    public Vector2 getOffset() {
        return offset;
    }

    public void setOffset(Vector2 offset) {
        this.offset = offset;
    }

    public Origin getOrigin() {
        return origin;
    }

    public void setOrigin(Origin origin) {
        this.origin = origin;
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

    @Override
    public float getX() {
        return anchor.getX() + (offset.x * scale);
    }

    @Override
    public float getY() {
        return anchor.getY() + (offset.y * scale);
    }

    public abstract float getWidth();

    public abstract float getHeight();

    public float getScreenX() {
        return getX() - (origin.getX() * getWidth() * scale);
    }

    public float getScreenY() {
        return getY() - (origin.getY() * getHeight() * scale);
    }

    public float getScreenWidth() {
        return getWidth() * scale;
    }

    public float getScreenHeight() {
        return getHeight() * scale;
    }

    public boolean contains(float x, float y) {
        float minX = getScreenX();
        float minY = getScreenY();

        return x >= minX && x <= minX + getScreenWidth() && y >= minY && y <= minY + getScreenHeight();
    }

    public abstract void draw(Batch batch);

}
