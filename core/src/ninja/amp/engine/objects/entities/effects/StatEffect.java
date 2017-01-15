package ninja.amp.engine.objects.entities.effects;

import ninja.amp.engine.objects.entities.stats.bonuses.StatBonus;

public class StatEffect implements Effect {

    private StatBonus statBonus;
    private float lifetime;

    public StatEffect(StatBonus statBonus, float lifetime) {
        this.statBonus = statBonus;
        this.lifetime = lifetime;
    }

    public StatBonus getStatBonus() {
        return statBonus;
    }

    @Override
    public boolean active() {
        return lifetime > 0;
    }

    @Override
    public void update(float delta) {
        lifetime -= delta;
    }

}
