package ninja.amp.mobilegame.objects.items;

import ninja.amp.mobilegame.objects.characters.stats.StatEffect;

public abstract class Item implements StatEffect {

    private String name;
    private ItemType type;

    public Item(String name, ItemType type) {
        this.name = name;
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public ItemType getType() {
        return type;
    }

}
