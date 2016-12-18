package ninja.amp.engine.particles.particles;

import ninja.amp.engine.graphics.textures.Texture;

public interface Particle {

    Texture getTexture();

    float getX();

    float getY();

    float getSize();

    float getRotation();

    boolean isAlive();

    void update(float delta);

}
