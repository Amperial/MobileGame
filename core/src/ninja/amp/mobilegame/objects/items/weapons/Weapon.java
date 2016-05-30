package ninja.amp.mobilegame.objects.items.weapons;

import ninja.amp.mobilegame.objects.items.Item;
import ninja.amp.mobilegame.objects.items.ItemType;

public abstract class Weapon extends Item {

    public Weapon(String name) {
        super(name, ItemType.WEAPON);
    }

}
