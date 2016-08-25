package ninja.amp.mobilegame.engine.background;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import ninja.amp.mobilegame.engine.graphics.Texture;

public class BackgroundLayer implements Background {

    private Texture texture;
    private Vector2 ratio;
    private Vector2 offset;
    private TileMode mode;
    private float scale;

    public BackgroundLayer(Texture texture, Vector2 ratio, Vector2 offset, TileMode mode) {
        this.texture = texture;
        this.ratio = ratio;
        this.offset = offset;
        this.mode = mode;
    }
    
    public BackgroundLayer(Texture texture, Vector2 ratio, Vector2 offset) {
        this(texture, ratio, offset, TileMode.SINGLE);
    }

    public BackgroundLayer(Texture texture, Vector2 ratio, TileMode mode) {
        this(texture, ratio, Vector2.Zero, mode);
    }

    public BackgroundLayer(Texture texture, TileMode mode) {
        this(texture, Vector2.Zero, Vector2.Zero, mode);
    }

    public BackgroundLayer(Texture texture, Vector2 ratio) {
        this(texture, ratio, Vector2.Zero, TileMode.SINGLE);
    }

    public BackgroundLayer(Texture texture) {
        this(texture, Vector2.Zero, Vector2.Zero, TileMode.SINGLE);
    }

    public Vector2 getRatio() {
        return ratio;
    }

    public void setRatio(Vector2 ratio) {
        this.ratio = ratio;
    }

    public Vector2 getOffset() {
        return offset;
    }

    public void setOffset(Vector2 offset) {
        this.offset = offset;
    }

    public float getScale() {
        return scale;
    }

    @Override
    public void setScale(float scale) {
        this.scale = scale;
    }

    public TileMode getMode() {
        return mode;
    }

    public void setMode(TileMode mode) {
        this.mode = mode;
    }

    @Override
    public void draw(Batch batch, float x, float y, float width, float height) {
        float w = texture.getRegion().getRegionWidth() * scale;
        float h = texture.getRegion().getRegionHeight() * scale;
        
        x = (x * ratio.x) + (offset.x * scale);
        y = (y * ratio.y) + (offset.y * scale);
        
        if (!isVisible(x, y, w, h, width, height)) {
            return;
        }
        
        if (mode == TileMode.REPEAT_X || mode == TileMode.REPEAT_XY) {
            x = (x % w) - w;
        }
        if (mode == TileMode.REPEAT_Y || mode == TileMode.REPEAT_XY) {
            y = (y % h) - h;
        }
        
        switch (mode) {
            case SINGLE:
                batch.draw(texture.getRegion(), x, y, w, h);
                break;
            case REPEAT_X:
                while (x < width) {
                    batch.draw(texture.getRegion(), x, y, w, h);
                    x += w;
                }
                break;
            case REPEAT_Y:
                while (x < height) {
                    batch.draw(texture.getRegion(), x, y, w, h);
                    y += h;
                }
                break;
            case REPEAT_XY:
                while (x < width) {
                    float cy = y;
                    while (cy < height) {
                        batch.draw(texture.getRegion(), x, cy, w, h);
                        cy += h;
                    }
                    x += w;
                }
                break;
        }
    }

    private boolean isVisible(float x, float y, float w, float h, float width, float height) {
        boolean xInBounds = x <= width && x + w >= 0;
        boolean yInBounds = y <= height && y + h >= 0;
        
        return mode == TileMode.REPEAT_XY || (mode == TileMode.REPEAT_X && yInBounds) || (mode == TileMode.REPEAT_Y && xInBounds) || (xInBounds && yInBounds);
    }

}
