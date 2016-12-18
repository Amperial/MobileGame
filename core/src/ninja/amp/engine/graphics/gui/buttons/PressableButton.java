package ninja.amp.engine.graphics.gui.buttons;

import com.badlogic.gdx.graphics.g2d.Batch;
import ninja.amp.engine.graphics.gui.Anchor;
import ninja.amp.engine.graphics.gui.Offset;
import ninja.amp.engine.graphics.gui.Origin;
import ninja.amp.engine.graphics.textures.Texture;

public class PressableButton extends Button {

    private Texture pressed;

    public PressableButton(Texture normal, Texture pressed, Anchor anchor, Origin origin, Offset offset) {
        super(normal, anchor, origin, offset);
        this.pressed = pressed;
    }

    public PressableButton(Texture normal, Texture pressed, Anchor anchor, Origin origin) {
        super(normal, anchor, origin);
        this.pressed = pressed;
    }

    public PressableButton(Texture normal, Texture pressed, Anchor anchor, Offset offset) {
        super(normal, anchor, offset);
        this.pressed = pressed;
    }

    public PressableButton(Texture normal, Texture pressed, Anchor anchor) {
        super(normal, anchor);
        this.pressed = pressed;
    }

    @Override
    public void draw(Batch batch, float delta) {
        super.draw(batch, delta);
        if (isPressed()) {
            batch.draw(pressed.getRegion(), getScreenX(), getScreenY(), getScreenWidth(), getScreenHeight());
        }
    }

}
