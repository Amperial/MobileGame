package ninja.amp.engine.map.tiles;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.JsonValue;
import ninja.amp.engine.graphics.textures.Texture;
import ninja.amp.engine.graphics.textures.atlas.Atlas;

public class StaticTile implements Tile, TileContainer {

    private Texture texture;
    private int width;
    private int height;

    public StaticTile(Texture texture, int width, int height) {
        this.texture = texture;
        this.width = width;
        this.height = height;
    }

    public StaticTile(Texture texture) {
        this(texture, 1, 1);
    }

    @Override
    public TextureRegion getTexture() {
        return texture.getRegion();
    }

    @Override
    public int getWidth() {
        return width;
    }

    @Override
    public int getHeight() {
        return height;
    }

    @Override
    public Tile getTile(int[][] tiles, int x, int y) {
        return this;
    }

    public static StaticTile load(JsonValue tile, Atlas atlas) {
        Texture texture = atlas.createRegionTexture(tile.getString("tile"));
        boolean flipX = false;
        if (tile.has("flipX")) {
            flipX = tile.getBoolean("flipX");
        }
        boolean flipY = false;
        if (tile.has("flipY")) {
            flipY = tile.getBoolean("flipY");
        }
        texture.getRegion().flip(flipX, flipY);
        if (tile.has("width") && tile.has("height")) {
            return new StaticTile(texture, tile.getInt("width"), tile.getInt("height"));
        } else {
            return new StaticTile(texture);
        }
    }

}
