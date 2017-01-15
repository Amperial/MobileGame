package ninja.amp.engine.objects.entities.stats;

public interface Stat {

    StatType getType();

    float getBase();

    float calculate();

}
