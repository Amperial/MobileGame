package ninja.amp.engine.particles.particles;

import ninja.amp.engine.graphics.textures.Texture;

public class SimpleParticle implements Particle {

    private Texture texture;
    private float x;
    private float y;
    private float size;
    private float rotation;
    private float lifetime;

    public void set(Texture texture, float x, float y, float size, float rotation, float lifetime) {
        this.texture = texture;
        this.x = x;
        this.y = y;
        this.size = size;
        this.rotation = rotation;
        this.lifetime = lifetime;
    }

    @Override
    public Texture getTexture() {
        return texture;
    }

    @Override
    public float getX() {
        return x;
    }

    @Override
    public float getY() {
        return y;
    }

    @Override
    public float getSize() {
        return size;
    }

    @Override
    public float getRotation() {
        return rotation;
    }

    @Override
    public boolean isAlive() {
        return lifetime > 0;
    }

    @Override
    public void update(float delta) {
        lifetime -= delta;
    }

    public float getLifetime() {
        return lifetime;
    }

}
