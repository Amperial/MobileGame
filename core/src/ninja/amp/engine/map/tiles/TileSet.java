package ninja.amp.engine.map.tiles;

import com.badlogic.gdx.utils.JsonValue;
import ninja.amp.engine.graphics.textures.atlas.Atlas;
import ninja.amp.engine.graphics.textures.atlas.GameAtlas;
import ninja.amp.engine.map.Map;
import ninja.amp.engine.resources.ResourceHandler;

public class TileSet {

    private Map map;
    private TileContainer[] tiles;

    public TileSet(Map map) {
        this.map = map;
    }

    public TileContainer getTileContainer(int tile) {
        return tiles[tile];
    }

    public void loadTileset(JsonValue tiles, ResourceHandler handler) {
        Atlas tileAtlas = new Atlas(GameAtlas.TILES, handler);

        int length = tiles.size;
        this.tiles = new TileContainer[length];

        for (int i = 0; i < length; i++) {
            JsonValue tile = tiles.get(i);
            String type = tile.getString("type");
            if (type.equals("static")) {
                this.tiles[i] = StaticTile.load(tile, tileAtlas);
            } else if (type.equals("varied")) {
                this.tiles[i] = VariedTile.load(tile, tileAtlas);
            } else if (type.equals("surface")) {
                this.tiles[i] = Surface.load(tile, tileAtlas);
            }
        }
    }

}
