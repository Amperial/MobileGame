package ninja.amp.mobilegame.gui;

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
        return percentX * Gdx.graphics.getWidth();
    }

    @Override
    public float getY() {
        return percentY * Gdx.graphics.getHeight();
    }

}
