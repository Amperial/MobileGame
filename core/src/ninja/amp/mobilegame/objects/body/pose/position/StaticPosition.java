package ninja.amp.mobilegame.objects.body.pose.position;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class StaticPosition implements Position {

    private float offsetX;
    private float offsetY;
    private float originX;
    private float originY;
    private float width;
    private float height;
    private float rotation;

    public StaticPosition(float offsetX, float offsetY, float originX, float originY, float width, float height, float rotation) {
        this.offsetX = offsetX;
        this.offsetY = offsetY;
        this.originX = originX;
        this.originY = originY;
        this.width = width;
        this.height = height;
        this.rotation = rotation;
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
        return rotation;
    }

    @Override
    public float getX() {
        return getOffsetX();
    }

    @Override
    public float getY() {
        return getOffsetY();
    }

    @Override
    public float getFlippedX() {
        return -getOffsetX();
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

}
