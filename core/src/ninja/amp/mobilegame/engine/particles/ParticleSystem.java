package ninja.amp.mobilegame.engine.particles;

import com.badlogic.gdx.graphics.g2d.Batch;
import ninja.amp.mobilegame.engine.particles.effects.ParticleEffect;
import ninja.amp.mobilegame.engine.particles.emitters.ParticleEmitter;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class ParticleSystem {

    private Set<ParticleEffect> effects = new HashSet<ParticleEffect>();
    private Set<ParticleEmitter> emitters = new HashSet<ParticleEmitter>();

    public void addEffect(ParticleEffect effect) {
        effects.add(effect);
    }

    public void addEmitter(ParticleEmitter emitter) {
        emitters.add(emitter);
    }

    public void update(float delta) {
        Iterator<ParticleEffect> iterator = effects.iterator();
        while (iterator.hasNext()) {
            ParticleEffect effect = iterator.next();
            effect.update(delta);
            if (effect.isComplete()) {
                iterator.remove();
            }
        }
        for (ParticleEmitter emitter : emitters) {
            emitter.emit(delta);
        }
    }

    public void draw(Batch batch) {
        for (ParticleEffect effect : effects) {
            effect.draw(batch);
        }
        for (ParticleEmitter emitter : emitters) {
            emitter.draw(batch);
        }
    }

    public void setScale(float scale) {
        for (ParticleEffect effect : effects) {
            effect.setScale(scale);
        }
        for (ParticleEmitter emitter : emitters) {
            emitter.setScale(scale);
        }
    }

}
