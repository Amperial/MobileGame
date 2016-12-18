package ninja.amp.engine.map.tiles;

import com.badlogic.gdx.utils.JsonValue;
import ninja.amp.engine.graphics.textures.atlas.Atlas;

public class Surface implements TileContainer {

    private Tile[] tiles;

    public Surface(Tile... tiles) {
        this.tiles = tiles;
    }

    @Override
    public Tile getTile(int[][] tiles, int x, int y) {
        int id = tiles[x][y];
        int index = 0;
        if (y + 1 >= tiles[x].length || tiles[x][y + 1] == id) index += 1;
        if (x - 1 < 0 || tiles[x - 1][y] == id) index += 2;
        if (y - 1 < 0 || tiles[x][y - 1] == id) index += 4;
        if (x + 1 >= tiles.length || tiles[x + 1][y] == id) index += 8;
        return this.tiles[index];
    }

    public static Surface load(JsonValue tile, Atlas atlas) {
        Tile[] tiles = new Tile[16];
        String prefix = "surfaces/" + tile.getString("surface") + "/";
        for (int i = 0; i < 16; i++) {
            tiles[i] = new StaticTile(atlas.createRegionTexture(prefix + i));
        }
        return new Surface(tiles);
    }

}
