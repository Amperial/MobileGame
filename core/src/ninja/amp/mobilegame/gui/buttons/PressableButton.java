package ninja.amp.mobilegame.gui.buttons;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ninja.amp.mobilegame.gui.Anchor;
import ninja.amp.mobilegame.gui.Origin;

public class PressableButton extends Button {

    private TextureRegion pressed;

    public PressableButton(TextureRegion normal, TextureRegion pressed, Anchor anchor, Origin origin, Vector2 offset) {
        super(normal, anchor, origin, offset);
        this.pressed = pressed;
    }

    public PressableButton(TextureRegion normal, TextureRegion pressed, Anchor anchor, Origin origin) {
        super(normal, anchor, origin);
        this.pressed = pressed;
    }

    public PressableButton(TextureRegion normal, TextureRegion pressed, Anchor anchor, Vector2 offset) {
        super(normal, anchor, offset);
        this.pressed = pressed;
    }

    public PressableButton(TextureRegion normal, TextureRegion pressed, Anchor anchor) {
        super(normal, anchor);
        this.pressed = pressed;
    }

    @Override
    public void draw(Batch batch) {
        batch.draw(isPressed() ? pressed : getTexture(), getScreenX(), getScreenY(), getScreenWidth(), getScreenHeight());
    }

}
