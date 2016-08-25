package ninja.amp.mobilegame.screens;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector3;
import ninja.amp.mobilegame.engine.physics.vectors.limits.Limit;

public class ScreenCamera extends OrthographicCamera {

    private Limit<Vector3> limit;

    public ScreenCamera(Screen screen, Limit<Vector3> limit) {
        this.limit = limit;
    }

    public void apply(Batch batch) {
        limit.limit(position);
        update();
        batch.setProjectionMatrix(combined);
    }

    public void setLimit(Limit<Vector3> limit) {
        this.limit = limit;
    }

}
