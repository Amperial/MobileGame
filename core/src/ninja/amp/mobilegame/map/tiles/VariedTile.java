package ninja.amp.mobilegame.map.tiles;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.JsonValue;
import ninja.amp.mobilegame.engine.graphics.Atlas;

public class VariedTile implements TileContainer {

    public VariedTile() {

    }

    @Override
    public Tile getTile(int[][] tiles, int x, int y) {
        return null;
    }

    public static VariedTile load(JsonValue tile, Atlas atlas) {
        return null;
    }

}
