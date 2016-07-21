package ninja.amp.mobilegame.engine.gui.buttons;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import ninja.amp.mobilegame.engine.gui.Anchor;
import ninja.amp.mobilegame.engine.gui.Origin;
import ninja.amp.mobilegame.engine.resources.texture.Texture;

public class HoverableButton extends Button {

    private Texture hovered;

    public HoverableButton(Texture normal, Texture hovered, Anchor anchor, Origin origin, Vector2 offset) {
        super(normal, anchor, origin, offset);
        this.hovered = hovered;
    }

    public HoverableButton(Texture normal, Texture hovered, Anchor anchor, Origin origin) {
        super(normal, anchor, origin);
        this.hovered = hovered;
    }

    public HoverableButton(Texture normal, Texture hovered, Anchor anchor, Vector2 offset) {
        super(normal, anchor, offset);
        this.hovered = hovered;
    }

    public HoverableButton(Texture normal, Texture hovered, Anchor anchor) {
        super(normal, anchor);
        this.hovered = hovered;
    }

    @Override
    public void draw(Batch batch) {
        super.draw(batch);
        if (isHovered()) {
            batch.draw(hovered.getRegion(), getScreenX(), getScreenY(), getScreenWidth(), getScreenHeight());
        }
    }

}
