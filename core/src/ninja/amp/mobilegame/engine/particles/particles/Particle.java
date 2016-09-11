package ninja.amp.mobilegame.engine.particles.particles;

import ninja.amp.mobilegame.engine.graphics.Texture;

public interface Particle {

    Texture getTexture();

    float getX();

    float getY();

    float getSize();

    float getRotation();

    boolean isAlive();

    void update(float delta);

}
