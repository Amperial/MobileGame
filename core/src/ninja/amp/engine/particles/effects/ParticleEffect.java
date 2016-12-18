package ninja.amp.engine.particles.effects;

import com.badlogic.gdx.graphics.g2d.Batch;
import ninja.amp.engine.graphics.gui.Scalable;

public interface ParticleEffect extends Scalable {

    void play();

    void update(float delta);

    void draw(Batch batch);

    boolean isComplete();

}
