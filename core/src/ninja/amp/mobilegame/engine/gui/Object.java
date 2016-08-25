package ninja.amp.mobilegame.engine.gui;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import ninja.amp.mobilegame.engine.graphics.Texture;

public class Object implements Anchor {

    private Texture texture;
    private Anchor anchor;
    private Origin origin;
    private Offset offset;
    private float scale;

    public Object(Texture texture, Anchor anchor, Origin origin, Offset offset) {
        this.texture = texture;
        this.anchor = anchor;
        this.origin = origin;
        this.offset = offset;
    }

    public Object(Texture texture, Anchor anchor, Origin origin) {
        this(texture, anchor, origin, new StaticOffset(Vector2.Zero));
    }

    public Object(Texture texture, Anchor anchor, Offset offset) {
        this(texture, anchor, Origin.CENTER, offset);
    }

    public Object(Texture texture, Anchor anchor) {
        this(texture, anchor, Origin.CENTER, new StaticOffset(Vector2.Zero));
    }

    public Anchor getAnchor() {
        return anchor;
    }

    public void setAnchor(Anchor anchor) {
        this.anchor = anchor;
    }

    public Vector2 getOffset() {
        return offset.getOffset();
    }

    public void setOffset(Offset offset) {
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
        return anchor.getX() + (offset.getOffset().x * scale);
    }

    @Override
    public float getY() {
        return anchor.getY() + (offset.getOffset().y * scale);
    }

    public float getWidth() {
        return texture.getRegion().getRegionWidth();
    }

    public float getHeight() {
        return texture.getRegion().getRegionHeight();
    }

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

    public void draw(Batch batch, float delta) {
        batch.draw(texture.getRegion(), getScreenX(), getScreenY(), getScreenWidth(), getScreenHeight());
    }

}
