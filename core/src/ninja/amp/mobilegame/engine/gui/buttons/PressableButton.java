package ninja.amp.mobilegame.engine.gui.buttons;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import ninja.amp.mobilegame.engine.gui.Origin;
import ninja.amp.mobilegame.engine.gui.Anchor;
import ninja.amp.mobilegame.engine.resources.texture.Texture;

public class PressableButton extends Button {

    private Texture pressed;

    public PressableButton(Texture normal, Texture pressed, Anchor anchor, Origin origin, Vector2 offset) {
        super(normal, anchor, origin, offset);
        this.pressed = pressed;
    }

    public PressableButton(Texture normal, Texture pressed, Anchor anchor, Origin origin) {
        super(normal, anchor, origin);
        this.pressed = pressed;
    }

    public PressableButton(Texture normal, Texture pressed, Anchor anchor, Vector2 offset) {
        super(normal, anchor, offset);
        this.pressed = pressed;
    }

    public PressableButton(Texture normal, Texture pressed, Anchor anchor) {
        super(normal, anchor);
        this.pressed = pressed;
    }

    @Override
    public void draw(Batch batch) {
        super.draw(batch);
        if (isPressed()) {
            batch.draw(pressed.getRegion(), getScreenX(), getScreenY(), getScreenWidth(), getScreenHeight());
        }
    }

}
