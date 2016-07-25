package ninja.amp.mobilegame.engine.gui.misc;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import ninja.amp.mobilegame.engine.gui.Anchor;
import ninja.amp.mobilegame.engine.gui.Origin;
import ninja.amp.mobilegame.engine.resources.texture.Texture;

public abstract class TileStatusBar extends StatusBar {

    private Texture tile;

    public TileStatusBar(Texture texture, Texture tile, Anchor anchor, Origin origin, Vector2 offset) {
        super(texture, anchor, origin, offset);
        this.tile = tile;
    }

    @Override
    public void draw(Batch batch) {
        float tilewidth = tile.getRegion().getRegionWidth() * getScale();
        int tiles = (int) (fillPercent() * getScreenWidth() / tilewidth);
        for (int i = 0; i < tiles; i++) {
            batch.draw(tile.getRegion(), getScreenX() + (i * tilewidth), getScreenY(), tilewidth, getScreenHeight());
        }
        super.draw(batch);
    }

}
