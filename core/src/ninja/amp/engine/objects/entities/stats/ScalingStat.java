package ninja.amp.engine.objects.entities.stats;

public class ScalingStat extends BaseStat {

    private float scaling;
    private int maxLevel;
    private int level;

    public ScalingStat(StatType type, float base, float scaling, int maxLevel, int level) {
        super(type, base);

        this.scaling = scaling;
        this.maxLevel = maxLevel;
        setLevel(level);
    }

    public float getScaling() {
        return scaling;
    }

    public int getMaxLevel() {
        return maxLevel;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = Math.min(level, maxLevel);
    }

    public void levelUp() {
        setLevel(getLevel() + 1);
    }

    @Override
    public float calculate() {
        return super.calculate() + (getScaling() * getLevel());
    }

}
