package ninja.amp.engine.graphics.gui.screens;

import com.badlogic.gdx.Gdx;
import ninja.amp.engine.graphics.gui.Anchor;

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
