package ninja.amp.engine.graphics.gui.bars;

import com.badlogic.gdx.graphics.g2d.Batch;
import ninja.amp.engine.graphics.gui.Anchor;
import ninja.amp.engine.graphics.gui.Offset;
import ninja.amp.engine.graphics.gui.Origin;
import ninja.amp.engine.graphics.textures.Texture;

public abstract class TileStatusBar extends StatusBar {

    private Texture tile;

    public TileStatusBar(Texture texture, Texture tile, Anchor anchor, Origin origin, Offset offset) {
        super(texture, anchor, origin, offset);
        this.tile = tile;
    }

    @Override
    public void draw(Batch batch, float delta) {
        float tilewidth = tile.getRegion().getRegionWidth() * getScale();
        int tiles = (int) (fillPercent() * getScreenWidth() / tilewidth);
        for (int i = 0; i < tiles; i++) {
            batch.draw(tile.getRegion(), getScreenX() + (i * tilewidth), getScreenY(), tilewidth, getScreenHeight());
        }
        super.draw(batch, delta);
    }

}
