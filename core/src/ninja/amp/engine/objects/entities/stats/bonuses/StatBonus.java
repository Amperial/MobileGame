package ninja.amp.engine.objects.entities.stats.bonuses;

import ninja.amp.engine.objects.entities.stats.Stat;
import ninja.amp.engine.objects.entities.stats.StatType;

public interface StatBonus {

    StatType getType();

    float getBonus(Stat stat);

}
