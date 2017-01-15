package ninja.amp.engine.objects.entities.stats;

import ninja.amp.engine.objects.entities.Entity;
import ninja.amp.engine.objects.entities.effects.Effect;
import ninja.amp.engine.objects.entities.effects.StatEffect;
import ninja.amp.engine.objects.entities.stats.bonuses.StatBonus;
import ninja.amp.engine.objects.items.Item;

public class EntityStat extends ScalingStat {

    private Entity entity;

    public EntityStat(StatType type, float base, float scaling, int maxLevel, int level) {
        super(type, base, scaling, maxLevel, level);
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    @Override
    public float getBonuses() {
        float bonus = 0;

        // Add item bonuses
        for (Item item : entity.getItems()) {
            for (StatBonus statBonus : item.getStatBonuses()) {
                bonus += statBonus.getBonus(this);
            }
        }

        // Add effect bonuses
        for (Effect effect : entity.getEffects()) {
            if (effect instanceof StatEffect) {
                bonus += ((StatEffect) effect).getStatBonus().getBonus(this);
            }
        }

        return bonus;
    }

}
