package ninja.amp.engine.objects.entities.stats.bonuses;

import ninja.amp.engine.objects.entities.stats.Stat;
import ninja.amp.engine.objects.entities.stats.StatType;

public class StaticBonus implements StatBonus {

    private StatType type;
    private float bonus;

    public StaticBonus(StatType type, float bonus) {
        this.type = type;
        this.bonus = bonus;
    }

    @Override
    public StatType getType() {
        return type;
    }

    @Override
    public float getBonus(Stat stat) {
        return stat.getType() == getType() ? bonus : 0;
    }

}
