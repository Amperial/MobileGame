package ninja.amp.engine.objects.entities.stats.bonuses;

import ninja.amp.engine.objects.entities.stats.Stat;
import ninja.amp.engine.objects.entities.stats.StatType;

public class ScalingBonus implements StatBonus {

    private StatType type;
    private float scaling;

    public ScalingBonus(StatType type, float scaling) {
        this.type = type;
        this.scaling = scaling;
    }

    @Override
    public StatType getType() {
        return type;
    }

    @Override
    public float getBonus(Stat stat) {
        return stat.getType() == getType() ? stat.getBase() * scaling : 0;
    }

}
