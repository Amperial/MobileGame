package ninja.amp.engine.graphics.gui.buttons;

import com.badlogic.gdx.graphics.g2d.Batch;
import ninja.amp.engine.graphics.gui.Anchor;
import ninja.amp.engine.graphics.gui.Offset;
import ninja.amp.engine.graphics.gui.Origin;
import ninja.amp.engine.graphics.textures.Texture;

public class HoverableButton extends Button {

    private Texture hovered;

    public HoverableButton(Texture normal, Texture hovered, Anchor anchor, Origin origin, Offset offset) {
        super(normal, anchor, origin, offset);
        this.hovered = hovered;
    }

    public HoverableButton(Texture normal, Texture hovered, Anchor anchor, Origin origin) {
        super(normal, anchor, origin);
        this.hovered = hovered;
    }

    public HoverableButton(Texture normal, Texture hovered, Anchor anchor, Offset offset) {
        super(normal, anchor, offset);
        this.hovered = hovered;
    }

    public HoverableButton(Texture normal, Texture hovered, Anchor anchor) {
        super(normal, anchor);
        this.hovered = hovered;
    }

    @Override
    public void draw(Batch batch, float delta) {
        super.draw(batch, delta);
        if (isHovered()) {
            batch.draw(hovered.getRegion(), getScreenX(), getScreenY(), getScreenWidth(), getScreenHeight());
        }
    }

}
