package ninja.amp.mobilegame.engine.background;

import com.badlogic.gdx.graphics.g2d.Batch;

import java.util.Arrays;
import java.util.List;

public class BackgroundGroup implements Background {

    private List<Background> layers;

    public BackgroundGroup(Background... layers) {
        this.layers = Arrays.asList(layers);
    }

    @Override
    public void setScale(float scale) {
        for (Background layer : layers) {
            layer.setScale(scale);
        }
    }

    @Override
    public void draw(Batch batch, float x, float y, float width, float height) {
        for (Background layer : layers) {
            layer.draw(batch, x, y, width, height);
        }
    }

}
