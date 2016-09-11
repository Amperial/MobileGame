package ninja.amp.mobilegame.engine.particles;

import ninja.amp.mobilegame.engine.particles.particles.Particle;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ParticleDrawer {

    private ParticlePool pool;
    private Set<Particle> particles;

    public ParticleDrawer(ParticlePool pool) {
        this.pool = pool;

        particles = new HashSet<Particle>();
    }

    public Set<Particle> getParticles() {
        return particles;
    }

    public void addParticle(Particle particle) {
        particles.add(particle);
    }

    public int getSize() {
        return particles.size();
    }

    public void update(float delta) {
        Iterator<Particle> iterator = particles.iterator();
        while (iterator.hasNext()) {
            Particle particle = iterator.next();
            particle.update(delta);
            if (!particle.isAlive()) {
                pool.free(particle);
                iterator.remove();
            }
        }
    }

}
