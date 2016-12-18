package ninja.amp.engine.map.collision;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import ninja.amp.engine.map.Map;
import ninja.amp.engine.objects.entities.Entity;
import ninja.amp.engine.physics.collision.RectangleHitbox;

public class CollisionLayer {

    private Map map;
    private Collision[][] tiles;

    private Rectangle defaultHitbox = new Rectangle(0, 0, 1, 1);

    public CollisionLayer(Map map) {
        this.map = map;
    }

    public Collision getTile(int x, int y) {
        return tiles[x][y];
    }

    public void loadTiles(int[][] tiles, int width, int height) {
        this.tiles = new Collision[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                this.tiles[x][y] = Collision.fromOrdinal(tiles[x][y]);
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
                    if (isCollision(tile_x, tile_x, covered_ymin, covered_ymax, Collision.SOLID)) {
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
                    if (isCollision(tile_x, tile_x, covered_ymin, covered_ymax, Collision.SOLID)) {
                        break;
                    } else {
                        float d = Math.max(x, tile_x - position.x - hitbox_x);
                        position.x += d;
                        x -= d;
                    }
                }
            }

            if (x != 0f) {
                entity.getVelocity().x = 0;
            }
        }

        covered_xmin = (int) (position.x + hitbox_x);
        covered_xmax = MathUtils.ceilPositive(position.x + hitbox_x + hitbox_w) - 1;

        if (vector.y != 0f) {
            float y = vector.y;

            if (y > 0) {
                int move_y = MathUtils.ceilPositive(position.y + hitbox_y + hitbox_h + y) - 1;
                for (int tile_y = covered_ymax; tile_y <= move_y; tile_y++) {
                    if (isCollision(covered_xmin, covered_xmax, tile_y, tile_y, Collision.SOLID)) {
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
                    if (tile_y == covered_ymin || entity.getCollisionState() == CollisionState.PLATFORM) {
                        if (isCollision(covered_xmin, covered_xmax, tile_y, tile_y, Collision.SOLID)) {
                            break;
                        }
                    } else if (isCollision(covered_xmin, covered_xmax, tile_y, tile_y, Collision.SOLID, Collision.PLATFORM)) {
                        break;
                    }
                    float d = Math.max(y, tile_y - position.y - hitbox_y);
                    position.y += d;
                    y -= d;
                }

            }

            if (y != 0f) {
                entity.getVelocity().y = 0;
            }
        }
    }

    public boolean isCollision(int xMin, int xMax, int yMin, int yMax, Collision... collisions) {
        for (int x = xMin; x <= xMax; x++) {
            for (int y = yMin; y <= yMax; y++) {
                for (Collision collision : collisions) {
                    if (tiles[x][y] == collision) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean isOnGround(Entity entity) {
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

        int covered_xmin = (int) (position.x + hitbox_x);
        int covered_xmax = MathUtils.ceilPositive(position.x + hitbox_x + hitbox_w) - 1;
        int covered_ymin = (int) (position.y + hitbox_y);
        int ground_y = covered_ymin - 1;

        if (entity.getCollisionState() == CollisionState.PLATFORM) {
            return covered_ymin == (position.y + hitbox_y) && isCollision(covered_xmin, covered_xmax, ground_y, ground_y, Collision.SOLID);
        }
        return covered_ymin == (position.y + hitbox_y) && isCollision(covered_xmin, covered_xmax, ground_y, ground_y, Collision.SOLID, Collision.PLATFORM);
    }

    public boolean isOnPlatform(Entity entity) {
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

        int covered_xmin = (int) (position.x + hitbox_x);
        int covered_xmax = MathUtils.ceilPositive(position.x + hitbox_x + hitbox_w) - 1;
        int covered_ymin = (int) (position.y + hitbox_y);
        int ground_y = covered_ymin - 1;

        boolean platform = isCollision(covered_xmin, covered_xmax, ground_y, ground_y, Collision.PLATFORM);
        boolean solid = isCollision(covered_xmin, covered_xmax, ground_y, ground_y, Collision.SOLID);
        return covered_ymin == (position.y + hitbox_y) && platform && !solid;
    }

    public boolean isInPlatform(Entity entity) {
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

        return isCollision(covered_xmin, covered_xmax, covered_ymin, covered_ymax, Collision.PLATFORM);
    }

}
