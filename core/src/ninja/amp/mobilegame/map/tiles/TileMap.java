package ninja.amp.mobilegame.map.tiles;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import ninja.amp.mobilegame.objects.Entity;

public class TileMap {

    private Tile[][][] tiles;
    private float scale;

    public int width = 100;
    public int height = 50;

    public TileMap() {
        CastleTile.load();

        tiles = new Tile[width][height][];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x][y] = new Tile[1];
            }
        }
        for (int x = 0; x < width; x++) {
            for  (int y = 1; y < height; y++) {
                tiles[x][y][0] = CastleTile.BRICK_1;
            }
            tiles[x][0][0] = CastleTile.SOLID_FLOOR;
        }

        tiles[9][2][0] = CastleTile.SOLID_FLOOR;
        tiles[13][4][0] = CastleTile.SOLID_FLOOR;
        tiles[14][4][0] = CastleTile.SOLID_FLOOR;
        tiles[14][5][0] = CastleTile.SOLID_FLOOR;
        tiles[15][5][0] = CastleTile.SOLID_FLOOR;
        tiles[15][6][0] = CastleTile.SOLID_FLOOR;
        tiles[16][2][0] = CastleTile.SOLID_FLOOR;
        tiles[17][1][0] = CastleTile.SOLID_FLOOR;
        tiles[17][2][0] = CastleTile.SOLID_FLOOR;
        tiles[19][1][0] = CastleTile.SOLID_FLOOR;
        tiles[19][2][0] = CastleTile.SOLID_FLOOR;
        tiles[19][3][0] = CastleTile.SOLID_FLOOR;
        tiles[7][6][0] = CastleTile.SOLID_FLOOR;
        tiles[8][6][0] = CastleTile.SOLID_FLOOR;
        tiles[9][6][0] = CastleTile.SOLID_FLOOR;
        tiles[10][6][0] = CastleTile.SOLID_FLOOR;


    }

    public void draw(Batch batch, int xs, int xm, int ys, int ym) {
        for (int x = xs; x < xm; x++) {
            for (int y = ys; y < ym; y++) {
                for (Tile tile : tiles[x][y]) {
                    if (tile != null) {
                        // TODO: Dont render tiles that wont be visible
                        batch.draw(tile.getTexture(), x*scale, y*scale, scale, scale);
                    }
                }
            }
        }
    }

    public void move(Entity entity, Vector2 vector) {
        // move x
        if (vector.x > 0f) {
            move_x(entity, vector.x);
        } else if (vector.x < 0f) {
            move_x(entity, vector.x);
        }

        // move y
        if (vector.y > 0f) {
            move_y(entity, vector.y);
        } else if (vector.y < 0f) {
            move_y(entity, vector.y);
        }

    }

    private void move_x(Entity entity, float x) {
        Vector2 position = entity.getPosition();

        int tile_ymin = (int) position.y;
        int tile_ymax = (int) Math.ceil(position.y);

        if (x > 0f) {
            int tile_xmin = (int) position.x + 1;
            int tile_xmax = (int) Math.ceil(position.x + 1 + x);

            for (int tile_x = tile_xmin; tile_x <= tile_xmax; tile_x++) {
                boolean solid_column = false;
                for (int tile_y = tile_ymin; tile_y <= tile_ymax; tile_y++) {
                    if (tiles[tile_x][tile_y][0].isSolid()) {
                        solid_column = true;
                    }
                }

                if (solid_column) {
                    break;
                } else if (position.x < tile_x){
                    float d = Math.min(x, tile_x - position.x);
                    position.x += d;
                    x -= d;
                }
            }
        } else if (x < 0f) {
            int tile_xmin = (int) (position.x + x);
            int tile_xmax = (int) Math.ceil(position.x);

            for (int tile_x = tile_xmax; tile_x >= tile_xmin; tile_x--) {
                boolean solid_column = false;
                for (int tile_y = tile_ymin; tile_y <= tile_ymax; tile_y++) {
                    if (tiles[tile_x][tile_y][0].isSolid()) {
                        solid_column = true;
                    }
                }

                if (solid_column) {
                    if (position.x > tile_x + 1) {
                        float d = Math.min(-x, position.x - tile_x + 1);
                        position.x -= d;
                        break;
                    }
                } else if (position.x > tile_x){
                    float d = Math.min(-x, position.x - tile_x);
                    position.x -= d;
                    x += d;
                }
            }
        }

        if (x != 0) {
            entity.getVelocity().x = 0;
        }
    }

    private void move_y(Entity entity, float y) {
        Vector2 position = entity.getPosition();

        float moved_y = 0;

        int tile_xmin = (int) position.x;
        int tile_xmax = (int) Math.ceil(position.x);

        if (y > 0f) {
            int tile_ymin = (int) position.y + 1;
            int tile_ymax = (int) Math.ceil(position.y + 1 + y);

            for (int tile_y = tile_ymin; tile_y <= tile_ymax; tile_y++) {
                boolean solid_row = false;
                for (int tile_x = tile_xmin; tile_x <= tile_xmax; tile_x++) {
                    if (tiles[tile_x][tile_y][0].isSolid()) {
                        solid_row = true;
                    }
                }

                if (solid_row) {
                    break;
                } else if (position.y < tile_y){
                    float d = Math.min(y, tile_y - position.y);
                    position.y += d;
                    y -= d;
                }
            }
        } else if (y < 0f) {
            int tile_ymin = (int) (position.y + y);
            int tile_ymax = (int) Math.ceil(position.y);

            for (int tile_y = tile_ymax; tile_y >= tile_ymin; tile_y--) {
                boolean solid_row = false;
                for (int tile_x = tile_xmin; tile_x <= tile_xmax; tile_x++) {
                    if (tiles[tile_x][tile_y][0].isSolid()) {
                        solid_row = true;
                    }
                }

                if (solid_row) {
                    if (position.y > tile_y + 1) {
                        float d = Math.min(-y, position.y - tile_y + 1);
                        position.y -= d;
                        break;
                    }
                } else if (position.y > tile_y){
                    float d = Math.min(-y, position.y - tile_y);
                    position.y -= d;
                    y += d;
                }
            }
        }

        if (y != 0) {
            entity.getVelocity().y = 0;
        }
    }

    public float getWidth() {
        return width * scale;
    }

    public float getHeight() {
        return height * scale;
    }

    public void dispose() {
        CastleTile.dispose();
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

}
