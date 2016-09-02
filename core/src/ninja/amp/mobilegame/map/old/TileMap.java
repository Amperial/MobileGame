package ninja.amp.mobilegame.map.old;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import ninja.amp.mobilegame.engine.physics.collision.RectangleHitbox;
import ninja.amp.mobilegame.objects.Entity;

public class TileMap {

    private Tile[][][] tiles;
    private float scale;

    public int width = 100;
    public int height = 50;

    private Rectangle defaultHitbox = new Rectangle(0, 0, 1, 1);

    public TileMap() {
        CastleTile.load();

        tiles = new Tile[width][height][];
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                tiles[x][y] = new Tile[1];
            }
        }
        for (int x = 0; x < width; x++) {
            for (int y = 1; y < height; y++) {
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
                        batch.draw(tile.getTexture(), x * scale, y * scale, scale, scale);
                    }
                }
            }
        }
    }

    public void move(Entity entity, Vector2 vector) {
        Vector2 position = entity.getPosition();
        Rectangle hitbox;
        if (entity.getHitbox() instanceof RectangleHitbox) {
            hitbox = ((RectangleHitbox) entity.getHitbox()).getRectangle();
        } else {
            hitbox = defaultHitbox;
        }
        float hitbox_x = hitbox.getX();
        float hitbox_y = hitbox.getY();
        float hitbox_w = hitbox.getWidth();
        float hitbox_h = hitbox.getHeight();

        int covered_xmin = (int) (position.x + hitbox_x);
        int covered_xmax = MathUtils.ceilPositive(position.x + hitbox_x + hitbox_w) - 1;
        int covered_ymin = (int) (position.y + hitbox_y);
        int covered_ymax = MathUtils.ceilPositive(position.y + hitbox_y + hitbox_h) - 1;

        if (vector.x != 0f) {
            float x = vector.x;

            if (x > 0) {
                int move_x = MathUtils.ceilPositive(position.x + hitbox_x + hitbox_w + x) - 1;
                for (int tile_x = covered_xmax; tile_x <= move_x; tile_x++) {
                    if (solid_area(tile_x, tile_x, covered_ymin, covered_ymax)) {
                        break;
                    } else {
                        float d = Math.min(x, tile_x + 1 - position.x - hitbox_x - hitbox_w);
                        position.x += d;
                        x -= d;
                    }
                }
            } else {
                int move_x = (int) (position.x + hitbox_x + x);
                for (int tile_x = covered_xmin; tile_x >= move_x; tile_x--) {
                    if (solid_area(tile_x, tile_x, covered_ymin, covered_ymax)) {
                        break;
                    } else {
                        float d = Math.max(x, tile_x - position.x - hitbox_x);
                        position.x += d;
                        x -= d;
                    }
                }
            }

            if (x != 0) {
                entity.getVelocity().x = 0;
            }
        }

        if (vector.y != 0f) {
            float y = vector.y;

            if (y > 0) {
                int move_y = MathUtils.ceilPositive(position.y + hitbox_y + hitbox_h + y) - 1;
                for (int tile_y = covered_ymax; tile_y <= move_y; tile_y++) {
                    if (solid_area(covered_xmin, covered_xmax, tile_y, tile_y)) {
                        break;
                    } else {
                        float d = Math.min(y, tile_y + 1 - position.y - hitbox_y - hitbox_h);
                        position.y += d;
                        y -= d;
                    }
                }
            } else {
                int move_y = (int) (position.y + hitbox_y + y);
                for (int tile_y = covered_ymin; tile_y >= move_y; tile_y--) {
                    if (solid_area(covered_xmin, covered_xmax, tile_y, tile_y)) {
                        break;
                    } else {
                        float d = Math.max(y, tile_y - position.y - hitbox_y);
                        position.y += d;
                        y -= d;
                    }
                }
            }

            if (y != 0) {
                entity.getVelocity().y = 0;
            }
        }
    }

    private boolean solid_area(int x_min, int x_max, int y_min, int y_max) {
        for (int x = x_min; x <= x_max; x++) {
            for (int y = y_min; y <= y_max; y++) {
                if (tiles[x][y][0].isSolid()) {
                    return true;
                }
            }
        }
        return false;
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
