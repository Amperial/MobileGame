package ninja.amp.mobilegame.map;

import ninja.amp.mobilegame.map.collision.CollisionLayer;
import ninja.amp.mobilegame.map.tiles.TileLayer;

public class Map {

    private int width;
    private int height;
    private float scale;

    private TileLayer foreground;
    private TileLayer midground;
    private TileLayer background;
    private CollisionLayer collision;

    public Map(int width, int height) {
        this.width = width;
        this.height = height;

        midground = new TileLayer(this);
        foreground = new TileLayer(this);
        background = new TileLayer(this);

        collision = new CollisionLayer(this);
    }

    public float getWidth() {
        return width * scale;
    }

    public float getHeight() {
        return height * scale;
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

    public void setScale(float scale) {
        this.scale = scale;

        foreground.setScale(scale);
        midground.setScale(scale);
        background.setScale(scale);
    }

}
