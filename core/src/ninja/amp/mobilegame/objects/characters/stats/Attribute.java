package ninja.amp.mobilegame.objects.characters.stats;

public abstract class Attribute implements StatEffect {

    private int level;
    private int min;
    private int max;

    public Attribute(int level, int min, int max) {
        this.level = level;
        this.min = min;
        this.max = max;
    }

    public int getLevel() {
        return level;
    }

    public int getMin() {
        return min;
    }

    public int getMax() {
        return max;
    }

}
