package ninja.amp.mobilegame.engine.gui.buttons;

import com.badlogic.gdx.graphics.g2d.Batch;
import ninja.amp.mobilegame.engine.graphics.Texture;
import ninja.amp.mobilegame.engine.gui.Anchor;
import ninja.amp.mobilegame.engine.gui.Offset;
import ninja.amp.mobilegame.engine.gui.Origin;

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
