package ninja.amp.mobilegame.map.tiles;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public enum  CastleTile implements Tile {
    BRICK_1(0, 0, false),
    BRICK_2(1, 0, false),
    BRICK_3(2, 0, false),
    BRICK_4(3, 0, false),
    LARGE_WINDOW_1(4, 0, false),
    LARGE_WINDOW_2(5, 0, false),
    LARGE_WINDOW_3(4, 1, false),
    LARGE_WINDOW_4(5, 1, false),
    LARGE_WINDOW_5(4, 2, false),
    LARGE_WINDOW_6(5, 2, false),
    BOOKSHELF(3, 8, false),
    TAPESTRY_RED(5, 7, false),
    TORCH(2, 7, false),
    CANDLESTICK(2, 8, false),
    DOOR_LIGHT(0, 4, false),
    DOOR_DARK(1, 4, false),
    BONES(12, 8, false),
    FLOOR_UNDER(11, 0, false),
    FLOOR(11, 1, false),
    BRICK_TOP(8, 0, false),
    SOLID_FLOOR(13, 2, true);

    public static Texture castle;
    public static Texture castle_n;
    private TextureRegion region;
    private TextureRegion region_n;
    private int x, y;
    private boolean solid;

    CastleTile(int x, int y, boolean solid) {
        this.x = x;
        this.y = y;
        this.solid = solid;
    }

    @Override
    public TextureRegion getTexture() {
        return region;
    }

    @Override
    public TextureRegion getNormal() {
        return region_n;
    }

    @Override
    public boolean isSolid() {
        return solid;
    }

    public static void load() {
        castle = new Texture(Gdx.files.internal("tiles/castle.png"));
        castle_n = new Texture(Gdx.files.internal("tiles/castle_n.png"));
        castle.setFilter(Texture.TextureFilter.Nearest, Texture.TextureFilter.Nearest);
        for (CastleTile castleTile : values()) {
            castleTile.region = new TextureRegion(castle, castleTile.x*16, castleTile.y*16, 16, 16);
            castleTile.region_n = new TextureRegion(castle_n, castleTile.x*16, castleTile.y*16, 16, 16);
        }
    }

    public static void dispose() {
        castle.dispose();
        castle_n.dispose();
    }
}
