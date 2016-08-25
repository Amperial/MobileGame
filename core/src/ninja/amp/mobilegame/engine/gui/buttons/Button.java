package ninja.amp.mobilegame.engine.gui.buttons;

import ninja.amp.mobilegame.engine.gui.Object;
import ninja.amp.mobilegame.engine.gui.Offset;
import ninja.amp.mobilegame.engine.gui.Origin;
import ninja.amp.mobilegame.engine.gui.Anchor;
import ninja.amp.mobilegame.engine.graphics.Texture;

public class Button extends Object {

    private int pressed = -1;
    private int hovered = -1;

    public Button(Texture texture, Anchor anchor, Origin origin, Offset offset) {
        super(texture, anchor, origin, offset);
    }

    public Button(Texture texture, Anchor anchor, Origin origin) {
        super(texture, anchor, origin);
    }

    public Button(Texture texture, Anchor anchor, Offset offset) {
        super(texture, anchor, offset);
    }

    public Button(Texture texture, Anchor anchor) {
        super(texture, anchor);
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
