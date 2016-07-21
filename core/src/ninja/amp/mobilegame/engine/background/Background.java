package ninja.amp.mobilegame.engine.background;

import com.badlogic.gdx.graphics.g2d.Batch;

public interface Background {

    void setScale(float scale);

    void draw(Batch batch, float x, float y, float width, float height);

}
