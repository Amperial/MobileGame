package ninja.amp.mobilegame.map.tiles;

import com.badlogic.gdx.graphics.g2d.Batch;
import ninja.amp.mobilegame.map.Map;

public class TileLayer {

    private Map map;
    private float scale;

    protected Tile[][] tiles;

    public TileLayer(Map map) {
        this.map = map;
    }

    public void loadTiles(int[][] tiles, int width, int height) {
        this.tiles = new Tile[width][height];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                switch (tiles[x][y]) {
                    case 0:
                        this.tiles[x][y] = null;
                        break;
                    case 1:
                        this.tiles[x][y] = null;
                        break;
                    case 2:
                        this.tiles[x][y] = null;
                        break;
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
