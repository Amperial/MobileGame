package ninja.amp.mobilegame.engine.particles.effects;

import com.badlogic.gdx.graphics.g2d.Batch;

public interface ParticleEffect  {

    void play();

    void update(float delta);

    void draw(Batch batch);

    boolean isComplete();

    void setScale(float scale);

}
