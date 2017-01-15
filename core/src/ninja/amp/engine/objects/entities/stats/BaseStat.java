package ninja.amp.engine.objects.entities.stats;

public class BaseStat implements Stat {

    private StatType type;
    private float base;

    public BaseStat(StatType type, float base) {
        this.type = type;
        this.base = base;
    }

    @Override
    public StatType getType() {
        return type;
    }

    @Override
    public float getBase() {
        return base;
    }

    public float getBonuses() {
        return 0;
    }

    @Override
    public float calculate() {
        return getBase() + getBonuses();
    }

}
