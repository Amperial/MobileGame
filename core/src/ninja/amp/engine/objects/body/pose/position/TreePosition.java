package ninja.amp.engine.objects.body.pose.position;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

public class TreePosition implements Position {

    private Position parent;
    private float offsetX;
    private float offsetY;
    private float originX;
    private float originY;
    private float width;
    private float height;
    private float rotation;

    public TreePosition(Position parent, float offsetX, float offsetY, float originX, float originY, float width, float height, float rotation) {
        this.parent = parent;
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.originX = originX;
        this.originY = originY;
        this.width = width;
        this.height = height;
        this.rotation = rotation;
    }

    public Position getParent() {
        return parent;
    }

    @Override
    public float getOffsetX() {
        return offsetX;
    }

    @Override
    public float getOffsetY() {
        return offsetY;
    }

    @Override
    public float getOriginX() {
        return originX;
    }

    @Override
    public float getOriginY() {
        return originY;
    }

    @Override
    public float getWidth() {
        return width;
    }

    @Override
    public float getHeight() {
        return height;
    }

    @Override
    public float getRotation() {
        return getParent().getRotation() + rotation;
    }

    @Override
    public float getX() {
        Position parent = getParent();
        return parent.getX() + rotateX(getOffsetX(), getOffsetY(), parent.getRotation());
    }

    @Override
    public float getY() {
        Position parent = getParent();
        return parent.getY() + rotateY(getOffsetX(), getOffsetY(), parent.getRotation());
    }

    @Override
    public float getFlippedX() {
        Position parent = getParent();
        return parent.getFlippedX() + rotateX(-getOffsetX(), -getOffsetY(), parent.getRotation());
    }

    @Override
    public void draw(Batch batch, float delta, TextureRegion region, float scale, boolean flipped) {
        float originX = getOriginX();
        float originY = getOriginY();
        if (flipped) {
            batch.draw(region, (getFlippedX() + originX) * scale, (getY() - originY) * scale, -originX * scale, originY * scale, -getWidth() * scale, getHeight() * scale, 1, 1, -getRotation());
        } else {
            batch.draw(region, (getX() - originX) * scale, (getY() - originY) * scale, originX * scale, originY * scale, getWidth() * scale, getHeight() * scale, 1, 1, getRotation());
        }
    }

    private static float rotateX(float x, float y, float degrees) {
        return x * MathUtils.cosDeg(degrees) - y * MathUtils.sinDeg(degrees);
    }

    private static float rotateY(float x, float y, float degrees) {
        return y * MathUtils.cosDeg(degrees) + x * MathUtils.sinDeg(degrees);
    }

}
