package ninja.amp.mobilegame.gui.buttons;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ninja.amp.mobilegame.gui.Anchor;
import ninja.amp.mobilegame.gui.Origin;

public class HoverableButton extends Button {

    private TextureRegion hovered;

    public HoverableButton(TextureRegion normal, TextureRegion hovered, Anchor anchor, Origin origin, Vector2 offset) {
        super(normal, anchor, origin, offset);
        this.hovered = hovered;
    }

    public HoverableButton(TextureRegion normal, TextureRegion hovered, Anchor anchor, Origin origin) {
        super(normal, anchor, origin);
        this.hovered = hovered;
    }

    public HoverableButton(TextureRegion normal, TextureRegion hovered, Anchor anchor, Vector2 offset) {
        super(normal, anchor, offset);
        this.hovered = hovered;
    }

    public HoverableButton(TextureRegion normal, TextureRegion hovered, Anchor anchor) {
        super(normal, anchor);
        this.hovered = hovered;
    }

    @Override
    public void draw(Batch batch) {
        batch.draw(isHovered() ? hovered : getTexture(), getScreenX(), getScreenY(), getScreenWidth(), getScreenHeight());
    }

}
