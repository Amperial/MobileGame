package ninja.amp.mobilegame.engine.particles.emitters;

import com.badlogic.gdx.graphics.g2d.Batch;
import ninja.amp.mobilegame.engine.particles.ParticleDrawer;
import ninja.amp.mobilegame.engine.particles.ParticlePool;
import ninja.amp.mobilegame.engine.particles.particles.Particle;

public class ConstantEmitter implements ParticleEmitter {

    private ParticlePool pool;
    private ParticleDrawer drawer;
    private int max;
    private float delay;
    private float scale;
    private float time = 0;

    public ConstantEmitter(ParticlePool pool, int max, float delay) {
        this.pool = pool;
        this.max = max;
        this.delay = delay;

        drawer = new ParticleDrawer(pool);
    }

    public void spawn(Particle particle) {
    }

    @Override
    public void emit(float delta) {
        drawer.update(delta);

        time += delta;
        int amount = Math.min((int) (time / delay), max - drawer.getSize());
        for (int i = 0; i < amount && pool.hasAvailable(); i++) {
            Particle particle = pool.claim();
            spawn(particle);
            drawer.addParticle(particle);
        }
        time -= (amount * delay);
    }

    @Override
    public void draw(Batch batch) {
        for (Particle particle : drawer.getParticles()) {
            float size = particle.getSize()  * scale;
            batch.draw(particle.getTexture().getRegion(), particle.getX() * scale, particle.getY() * scale, 0.5f, 0.5f, 1, 1, size, size, particle.getRotation());
        }
    }

    @Override
    public void setScale(float scale) {
        this.scale = scale;
    }

}
