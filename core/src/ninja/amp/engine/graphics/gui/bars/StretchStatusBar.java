package ninja.amp.engine.graphics.gui.bars;

import com.badlogic.gdx.graphics.g2d.Batch;
import ninja.amp.engine.graphics.gui.Anchor;
import ninja.amp.engine.graphics.gui.Offset;
import ninja.amp.engine.graphics.gui.Origin;
import ninja.amp.engine.graphics.textures.Texture;

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
