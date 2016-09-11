package ninja.amp.mobilegame.engine.particles;

import ninja.amp.mobilegame.engine.particles.particles.Particle;

public abstract class ParticlePool {

    private Particle[] particles;
    private int available;

    public ParticlePool(int size) {
        particles = new Particle[size];
        available = size;
        for (int i = 0; i < available; i++) {
            particles[i] = create();
        }
    }

    public int getAvailable() {
        return available;
    }

    public boolean hasAvailable() {
        return available > 0;
    }

    public Particle claim() {
        if (available > 0) {
            available--;
            Particle particle = particles[available];
            particles[available] = null;
            return particle;
        }
        return null;
    }

    public void free(Particle particle) {
        if (available < particles.length) {
            particles[available] = particle;
            available++;
        }
    }

    public abstract Particle create();

}
