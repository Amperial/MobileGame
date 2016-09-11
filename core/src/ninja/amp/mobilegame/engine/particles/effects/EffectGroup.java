package ninja.amp.mobilegame.engine.particles.effects;

import com.badlogic.gdx.graphics.g2d.Batch;

public class EffectGroup implements ParticleEffect {

    private ParticleEffect[] effects;

    public EffectGroup(ParticleEffect... effects) {
        this.effects = effects;
    }

    @Override
    public void play() {
        for (ParticleEffect effect : effects) {
            effect.play();
        }
    }

    @Override
    public void update(float delta) {
        for (ParticleEffect effect : effects) {
            effect.update(delta);
        }
    }

    @Override
    public void draw(Batch batch) {
        for (ParticleEffect effect : effects) {
            effect.draw(batch);
        }
    }

    @Override
    public boolean isComplete() {
        for (ParticleEffect effect : effects) {
            if (!effect.isComplete()) {
                return false;
            }
        }
        return true;
    }

    @Override
    public void setScale(float scale) {
        for (ParticleEffect effect : effects) {
            effect.setScale(scale);
        }
    }

}
