package ninja.amp.mobilegame.engine.gui.misc;

import com.badlogic.gdx.graphics.g2d.Batch;
import ninja.amp.mobilegame.engine.graphics.Texture;
import ninja.amp.mobilegame.engine.gui.Anchor;
import ninja.amp.mobilegame.engine.gui.Offset;
import ninja.amp.mobilegame.engine.gui.Origin;

public abstract class StretchStatusBar extends StatusBar {

    private Texture fill;

    public StretchStatusBar(Texture texture, Texture fill, Anchor anchor, Origin origin, Offset offset) {
        super(texture, anchor, origin, offset);
        this.fill = fill;
    }

    @Override
    public void draw(Batch batch, float delta) {
        batch.draw(fill.getRegion(), getScreenX(), getScreenY(), getScreenWidth() * fillPercent(), getScreenHeight());
        super.draw(batch, delta);
    }

}
