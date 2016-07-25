package ninja.amp.mobilegame.engine.gui;

import com.badlogic.gdx.Gdx;

public class ScreenAnchor implements Anchor {

    private float percentX;
    private float percentY;

    public ScreenAnchor(float percentX, float percentY) {
        this.percentX = percentX;
        this.percentY = percentY;
    }

    @Override
    public float getX() {
        return percentX * Gdx.graphics.getWidth(); // TODO: Remove Gdx.graphics usage
    }

    @Override
    public float getY() {
        return percentY * Gdx.graphics.getHeight(); // TODO: Remove Gdx.graphics usage
    }

}
