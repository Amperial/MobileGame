package ninja.amp.mobilegame.objects.characters.movement;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.MathUtils;

public class Position {

    private Position parent;
    private float offsetX;
    private float offsetY;
    private float originX;
    private float originY;
    private float width;
    private float height;
    private float rotation;
    private float totalTime;

    public Position(Position parent, float offsetX, float offsetY, float originX, float originY, float width, float height, float rotation) {
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

    public void setParent(Position parent) {
        this.parent = parent;
    }

    public float getOffsetX() {
        return offsetX;
    }

    public float getOffsetY() {
        return offsetY;
    }

    public float getOriginX() {
        return originX;
    }

    public float getOriginY() {
        return originY;
    }

    public float getWidth() {
        return width;
    }

    public float getHeight() {
        return height;
    }

    public float getRotation() {
        return parent.getRotation() + rotation;
    }

    public float getX(boolean flipped) {
        if (flipped) {
            return parent.getX(true) + getRotatedX(-getOffsetX(), getOffsetY(), -parent.getRotation());
        } else {
            return parent.getX(false) + getRotatedX(getOffsetX(), getOffsetY(), parent.getRotation());
        }
    }

    public float getY(boolean flipped) {
        if (flipped) {
            return parent.getY(true) + getRotatedY(-getOffsetX(), getOffsetY(), -parent.getRotation());
        } else {
            return parent.getY(false) + getRotatedY(getOffsetX(), getOffsetY(), parent.getRotation());
        }
    }

    private float getRotatedX(float x, float y, float degrees) {
        return x * MathUtils.cosDeg(degrees) - y * MathUtils.sinDeg(degrees);
    }

    private float getRotatedY(float x, float y, float degrees) {
        return y * MathUtils.cosDeg(degrees) + x * MathUtils.sinDeg(degrees);
    }

    public void draw(Batch batch, float delta, TextureRegion region, float scale, boolean flipped) {
        totalTime += delta;
        float originX = getOriginX();
        float originY = getOriginY();
        if (flipped) {
            float width = getWidth() * scale;
            // draw from bottom right corner to the left
            float x = (getX(true) + originX) * scale;
            batch.draw(region, x, (getY(true) - originY) * scale, -originX * scale, originY * scale, -width, getHeight() * scale, 1, 1, -getRotation());
        } else {
            batch.draw(region, (getX(false) - originX) * scale, (getY(true) - originY) * scale, originX * scale, originY * scale, getWidth() * scale, getHeight() * scale, 1, 1, getRotation());
        }
    }

    public float getTotalTime() {
        return totalTime;
    }

    public void resetTotalTime() {
        totalTime = 0;
    }

}
