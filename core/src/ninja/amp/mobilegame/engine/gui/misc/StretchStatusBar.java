package ninja.amp.mobilegame.engine.gui.misc;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import ninja.amp.mobilegame.engine.gui.Anchor;
import ninja.amp.mobilegame.engine.gui.Origin;
import ninja.amp.mobilegame.engine.resources.texture.Texture;

public abstract class StretchStatusBar extends StatusBar {

    private Texture fill;

    public StretchStatusBar(Texture texture, Texture fill, Anchor anchor, Origin origin, Vector2 offset) {
        super(texture, anchor, origin, offset);
        this.fill = fill;
    }

    @Override
    public void draw(Batch batch) {
        batch.draw(fill.getRegion(), getScreenX(), getScreenY(), getScreenWidth() * fillPercent(), getScreenHeight());
        super.draw(batch);
    }

}
