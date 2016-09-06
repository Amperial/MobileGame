package ninja.amp.mobilegame.map.tiles;

import com.badlogic.gdx.utils.JsonValue;
import ninja.amp.mobilegame.engine.graphics.atlas.Atlas;

import java.util.Random;

public class VariedTile implements TileContainer {

    private Tile[] tiles;
    private Random random;

    public VariedTile(long seed, Tile... tiles) {
        this.tiles = tiles;
        this.random = new Random(seed);
    }

    @Override
    public Tile getTile(int[][] tiles, int x, int y) {
        return this.tiles[random.nextInt(this.tiles.length)];
    }

    public static VariedTile load(JsonValue tile, Atlas atlas) {
        JsonValue value = tile.get("tiles");
        int length = value.size;

        long seed = 0;
        Tile[] tiles = new Tile[length];
        for (int i = 0; i < length; i++) {
            String variant = value.getString(i);
            seed += variant.hashCode();
            tiles[i] = new StaticTile(atlas.createRegionTexture(variant));
        }

        return new VariedTile(seed, tiles);
    }

}
