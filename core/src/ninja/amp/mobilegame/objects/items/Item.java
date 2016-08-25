package ninja.amp.mobilegame.objects.items;

import ninja.amp.mobilegame.engine.physics.mass.Mass;
import ninja.amp.mobilegame.objects.characters.stats.StatEffect;

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
