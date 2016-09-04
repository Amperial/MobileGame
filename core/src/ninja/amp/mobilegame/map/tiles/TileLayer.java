package ninja.amp.mobilegame.map.tiles;

import com.badlogic.gdx.graphics.g2d.Batch;
import ninja.amp.mobilegame.map.Map;

public class TileLayer {

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
        for (int x = xs; x < xm; x++) {
            for (int y = ys; y < ym; y++) {
                if (tiles[x][y] != null) {
                    // TODO: Dont render tiles that wont be visible
                    batch.draw(tiles[x][y].getTexture(), x * scale, y * scale, scale, scale);
                }
            }
        }
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

}
