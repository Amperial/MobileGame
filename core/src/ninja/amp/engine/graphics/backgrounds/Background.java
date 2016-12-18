package ninja.amp.engine.graphics.backgrounds;

import com.badlogic.gdx.graphics.g2d.Batch;
import ninja.amp.engine.graphics.gui.Scalable;

public interface Background extends Scalable {

    void draw(Batch batch, float x, float y, float width, float height);

}
