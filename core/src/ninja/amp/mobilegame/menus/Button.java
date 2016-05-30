package ninja.amp.mobilegame.menus;


import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;

public class Button extends Object {

    private TextureRegion texture;

    public Button(TextureRegion texture, Anchor anchor, Origin origin, Vector2 offset) {
        super(anchor, origin, offset);
        this.texture = texture;
    }

    public Button(TextureRegion texture, Anchor anchor, Origin origin) {
        super(anchor, origin);
        this.texture = texture;
    }

    public Button(TextureRegion texture, Anchor anchor, Vector2 offset) {
        super(anchor, offset);
        this.texture = texture;
    }

    public Button(TextureRegion texture, Anchor anchor) {
        super(anchor);
        this.texture = texture;
    }


    @Override
    public float getWidth() {
        return texture.getRegionWidth();
    }

    @Override
    public float getHeight() {
        return texture.getRegionHeight();
    }

    public boolean contains(float x, float y) {
        float minX = getScreenX();
        float minY = getScreenY();

        return x >= minX && x <= minX + getScreenWidth() && y >= minY && y <= minY + getScreenHeight();
    }

    public void click() {
    }

    public void draw(Batch batch) {
        batch.draw(texture, getScreenX(), getScreenY(), getScreenWidth(), getScreenHeight());
    }

}
