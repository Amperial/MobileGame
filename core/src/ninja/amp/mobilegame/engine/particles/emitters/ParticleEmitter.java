package ninja.amp.mobilegame.engine.particles.emitters;

import com.badlogic.gdx.graphics.g2d.Batch;

public interface ParticleEmitter {

    void emit(float delta);

    void draw(Batch batch);

    void setScale(float scale);

}
