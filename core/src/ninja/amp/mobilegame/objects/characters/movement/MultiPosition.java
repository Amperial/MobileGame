package ninja.amp.mobilegame.objects.characters.movement;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public abstract class MultiPosition extends Position {


    public MultiPosition() {
        super(null, 0, 0, 0, 0, 0, 0, 0);
    }

    @Override
    public Position getParent() {
        return current().getParent();
    }

    @Override
    public float getOffsetX() {
        return current().getOffsetX();
    }

    @Override
    public float getOffsetY() {
        return current().getOffsetY();
    }

    @Override
    public float getOriginX() {
        return current().getOriginX();
    }

    @Override
    public float getOriginY() {
        return current().getOriginY();
    }

    @Override
    public float getWidth() {
        return current().getWidth();
    }

    @Override
    public float getHeight() {
        return current().getHeight();
    }

    @Override
    public float getRotation() {
        return current().getRotation();
    }

    @Override
    public float getX(boolean flipped) {
        return current().getX(flipped);
    }

    @Override
    public float getY(boolean flipped) {
        return current().getY(flipped);
    }

    @Override
    public void draw(Batch batch, float delta, TextureRegion region, float scale, boolean flipped) {
        current().draw(batch, delta, region, scale, flipped);
    }

    public abstract Position current();

}
