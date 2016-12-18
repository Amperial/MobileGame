package ninja.amp.engine.objects.body.pose.position;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class ElasticPosition implements Positionable, Position {

    private Position target;
    private float elasticity;
    private float offsetX;
    private float offsetY;
    private float originX;
    private float originY;
    private float rotation;
    private float x;
    private float y;
    private float flippedX;
    private boolean snap;

    public ElasticPosition(Position target, float elasticity) {
        this.target = target;
        this.elasticity = elasticity;
        this.snap = true;
    }

    public void snap() {
        snap = true;
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
        return target.getWidth();
    }

    @Override
    public float getHeight() {
        return target.getHeight();
    }

    @Override
    public float getRotation() {
        return rotation;
    }

    @Override
    public float getX() {
        return target.getX();
    }

    @Override
    public float getY() {
        return target.getY();
    }

    @Override
    public float getFlippedX() {
        return target.getFlippedX();
    }

    @Override
    public void draw(Batch batch, float delta, TextureRegion region, float scale, boolean flipped) {
        if (snap) {
            offsetX = target.getOffsetX();
            offsetY = target.getOffsetY();
            originX = target.getOriginX();
            originY = target.getOriginY();
            rotation = target.getRotation();
            x = target.getX();
            y = target.getY();
            flippedX = target.getFlippedX();
            snap = false;
        } else {
            offsetX += (target.getOffsetX() - offsetX) * elasticity * delta;
            offsetY += (target.getOffsetY() - offsetY) * elasticity * delta;
            originX += (target.getOriginX() - originX) * elasticity * delta;
            originY += (target.getOriginY() - originY) * elasticity * delta;
            rotation += (target.getRotation() - rotation) * elasticity * delta;
            x += (target.getX() - x) * elasticity * delta;
            y += (target.getY() - y) * elasticity * delta;
            flippedX += (target.getFlippedX() - flippedX) * elasticity * delta;
        }
        float originX = getOriginX();
        float originY = getOriginY();
        if (flipped) {
            batch.draw(region, (getFlippedX() + originX) * scale, (getY() - originY) * scale, -originX * scale, originY * scale, -getWidth() * scale, getHeight() * scale, 1, 1, -getRotation());
        } else {
            batch.draw(region, (getX() - originX) * scale, (getY() - originY) * scale, originX * scale, originY * scale, getWidth() * scale, getHeight() * scale, 1, 1, getRotation());
        }
    }

    @Override
    public Position getPosition() {
        return this;
    }

    @Override
    public Position getTargetPosition() {
        return target;
    }

    @Override
    public void setTargetPosition(Position target) {
        this.target = target;
    }

}
