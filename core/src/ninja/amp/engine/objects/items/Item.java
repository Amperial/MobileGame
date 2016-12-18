package ninja.amp.engine.objects.items;

import ninja.amp.engine.objects.entities.stats.StatEffect;
import ninja.amp.engine.physics.mass.Mass;

public abstract class Item implements StatEffect, Mass {

    private ItemType type;
    private String name;
    private Mass mass;

    public Item(ItemType type, String name, Mass mass) {
        this.name = name;
        this.type = type;
        this.mass = mass;
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

}
