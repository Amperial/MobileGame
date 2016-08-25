package ninja.amp.mobilegame.engine.gui.buttons;

import com.badlogic.gdx.graphics.g2d.Batch;
import ninja.amp.mobilegame.engine.graphics.Texture;
import ninja.amp.mobilegame.engine.gui.Anchor;
import ninja.amp.mobilegame.engine.gui.Offset;
import ninja.amp.mobilegame.engine.gui.Origin;

public class ToggleButton extends Button {

    private Texture off;
    private boolean toggled = true;

    public ToggleButton(Texture on, Texture off, Anchor anchor, Origin origin, Offset offset) {
        super(on, anchor, origin, offset);
        this.off = off;
    }

    public ToggleButton(Texture on, Texture off, Anchor anchor, Origin origin) {
        super(on, anchor, origin);
        this.off = off;
    }

    public ToggleButton(Texture on, Texture off, Anchor anchor, Offset offset) {
        super(on, anchor, offset);
        this.off = off;
    }

    public ToggleButton(Texture on, Texture off, Anchor anchor) {
        super(on, anchor);
        this.off = off;
    }

    @Override
    public void click() {
        setToggled(!isToggled());
    }

    public boolean isToggled() {
        return toggled;
    }

    public void setToggled(boolean toggled) {
        this.toggled = toggled;
    }

    @Override
    public void draw(Batch batch, float delta) {
        if (toggled) {
            super.draw(batch, delta);
        } else {
            batch.draw(off.getRegion(), getScreenX(), getScreenY(), getScreenWidth(), getScreenHeight());
        }
    }

}
