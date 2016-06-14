package ninja.amp.mobilegame.gui.buttons;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ninja.amp.mobilegame.gui.Anchor;
import ninja.amp.mobilegame.gui.Object;
import ninja.amp.mobilegame.gui.Origin;

public class Button extends Object {

    private TextureRegion texture;
    private int pressed = -1;
    private int hovered = -1;

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

    public TextureRegion getTexture() {
        return texture;
    }

    @Override
    public float getWidth() {
        return texture.getRegionWidth();
    }

    @Override
    public float getHeight() {
        return texture.getRegionHeight();
    }

    @Override
    public void draw(Batch batch) {
        batch.draw(texture, getScreenX(), getScreenY(), getScreenWidth(), getScreenHeight());
    }

    public void setPressed(int pointer) {
        this.pressed = pointer;
    }

    public boolean isPressed(int pointer) {
        return pressed == pointer;
    }

    public boolean isPressed() {
        return pressed >= 0;
    }

    public void setHovered(int pointer) {
        this.hovered = pointer;
    }

    public boolean isHovered(int pointer) {
        return hovered == pointer;
    }

    public boolean isHovered() {
        return hovered >= 0;
    }

    public void click() {
    }

}
