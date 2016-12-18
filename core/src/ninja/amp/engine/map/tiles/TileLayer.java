package ninja.amp.engine.map.tiles;

import com.badlogic.gdx.graphics.g2d.Batch;
import ninja.amp.engine.graphics.gui.Scalable;
import ninja.amp.engine.map.Map;

public class TileLayer implements Scalable {

    private Map map;
    private float scale;

    private Tile[][] tiles;

    public TileLayer(Map map) {
        this.map = map;
    }

    public void loadTiles(int[][] tiles, int width, int height) {
        TileSet tileset = map.getTileset();

        this.tiles = new Tile[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                if (tiles[x][y] >= 0) {
                    this.tiles[x][y] = tileset.getTileContainer(tiles[x][y]).getTile(tiles, x, y);
                }
            }
        }
    }

    public void draw(Batch batch, int xs, int xm, int ys, int ym) {
        Tile tile;
        for (int x = xs; x < xm; x++) {
            for (int y = ys; y < ym; y++) {
                tile = tiles[x][y];
                if (tile != null) {
                    // TODO: Dont render tiles that wont be visible
                    batch.draw(tile.getTexture(), x * scale, y * scale, tile.getWidth() * scale, tile.getHeight() * scale);
                }
            }
        }
    }

    @Override
    public float getScale() {
        return scale;
    }

    @Override
    public void setScale(float scale) {
        this.scale = scale;
    }

}
