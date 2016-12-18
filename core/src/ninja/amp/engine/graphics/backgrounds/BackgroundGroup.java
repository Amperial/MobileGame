package ninja.amp.engine.graphics.backgrounds;

import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.Arrays;
import java.util.List;

public class BackgroundGroup implements Background {

    private List<Background> layers;
    private float scale;

    public BackgroundGroup(Background... layers) {
        this.layers = Arrays.asList(layers);
    }

    @Override
    public void draw(Batch batch, float x, float y, float width, float height) {
        for (Background layer : layers) {
            layer.draw(batch, x, y, width, height);
        }
    }

    @Override
    public float getScale() {
        return scale;
    }

    @Override
    public void setScale(float scale) {
        this.scale = scale;
        for (Background layer : layers) {
            layer.setScale(scale);
        }
    }

}
