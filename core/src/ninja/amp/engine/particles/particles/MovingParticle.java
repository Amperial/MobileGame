package ninja.amp.engine.particles.particles;

import ninja.amp.engine.graphics.textures.Texture;

public class MovingParticle implements Particle {

    private Texture texture;
    private float x;
    private float dx;
    private float y;
    private float dy;
    private float size;
    private float rotation;
    private float drotation;
    private float lifetime;

    public void set(Texture texture, float x, float dx, float y, float dy, float size, float rotation, float drotation, float lifetime) {
        this.texture = texture;
        this.x = x;
        this.dx = dx;
        this.y = y;
        this.dy = dy;
        this.size = size;
        this.rotation = rotation;
        this.drotation = drotation;
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
        x += dx * delta;
        y += dy * delta;
        rotation += drotation * delta;
    }

    public float getLifetime() {
        return lifetime;
    }

}
