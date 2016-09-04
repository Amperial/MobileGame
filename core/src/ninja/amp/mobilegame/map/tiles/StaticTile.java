package ninja.amp.mobilegame.map.tiles;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.JsonValue;
import ninja.amp.mobilegame.engine.graphics.Atlas;
import ninja.amp.mobilegame.engine.graphics.Texture;

public class StaticTile implements Tile, TileContainer {

    private Texture texture;

    public StaticTile(Texture texture) {
        this.texture = texture;
    }

    @Override
    public TextureRegion getTexture() {
        return texture.getRegion();
    }

    @Override
    public Tile getTile(int[][] tiles, int x, int y) {
        return this;
    }

    public static StaticTile load(JsonValue tile, Atlas atlas) {
        return new StaticTile(atlas.createRegionTexture(tile.getString("tile")));
    }

}
