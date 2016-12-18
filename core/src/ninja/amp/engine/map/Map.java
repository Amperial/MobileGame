package ninja.amp.engine.map;

import ninja.amp.engine.graphics.gui.Scalable;
import ninja.amp.engine.map.collision.CollisionLayer;
import ninja.amp.engine.map.tiles.TileLayer;
import ninja.amp.engine.map.tiles.TileSet;

public class Map implements Scalable {

    private int level;
    private String name;
    private int width;
    private int height;
    private float scale;
    private TileSet tileset;
    private TileLayer foreground;
    private TileLayer midground;
    private TileLayer background;
    private CollisionLayer collision;

    public Map(int level, String name, int width, int height) {
        this.level = level;
        this.name = name;
        this.width = width;
        this.height = height;

        tileset = new TileSet(this);

        midground = new TileLayer(this);
        foreground = new TileLayer(this);
        background = new TileLayer(this);

        collision = new CollisionLayer(this);
    }

    public int getLevel() {
        return level;
    }

    public String getName() {
        return name;
    }

    public float getWidth() {
        return width * scale;
    }

    public float getHeight() {
        return height * scale;
    }

    public TileSet getTileset() {
        return tileset;
    }

    public TileLayer getForeground() {
        return foreground;
    }

    public TileLayer getMidground() {
        return midground;
    }

    public TileLayer getBackground() {
        return background;
    }

    public CollisionLayer getCollision() {
        return collision;
    }

    @Override
    public float getScale() {
        return scale;
    }

    @Override
    public void setScale(float scale) {
        this.scale = scale;

        foreground.setScale(scale);
        midground.setScale(scale);
        background.setScale(scale);
    }

}
