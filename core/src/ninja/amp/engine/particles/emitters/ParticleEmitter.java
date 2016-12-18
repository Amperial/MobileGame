package ninja.amp.engine.particles.emitters;

import com.badlogic.gdx.graphics.g2d.Batch;
import ninja.amp.engine.graphics.gui.Scalable;

public interface ParticleEmitter extends Scalable {

    void emit(float delta);

    void draw(Batch batch);

}
