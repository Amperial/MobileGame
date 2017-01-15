package ninja.amp.engine.objects.items;

import ninja.amp.engine.objects.entities.stats.bonuses.StatBonus;
import ninja.amp.engine.physics.mass.Mass;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public abstract class Item implements Mass {

    private ItemType type;
    private String name;
    private Mass mass;
    private Set<StatBonus> statBonuses;

    public Item(ItemType type, String name, Mass mass, StatBonus... statBonuses) {
        this.name = name;
        this.type = type;
        this.mass = mass;
        this.statBonuses = new HashSet<StatBonus>(Arrays.asList(statBonuses));
    }

    public ItemType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    @Override
    public float getMass() {
        return mass.getMass();
    }

    public Set<StatBonus> getStatBonuses() {
        return statBonuses;
    }

    public void addStatBonus(StatBonus statBonus) {
        statBonuses.add(statBonus);
    }

}
