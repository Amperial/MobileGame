package ninja.amp.mobilegame.objects.items.special;

import ninja.amp.mobilegame.objects.items.Item;
import ninja.amp.mobilegame.objects.items.ItemType;

public abstract class Special extends Item {

    public Special(String name) {
        super(name, ItemType.SPECIAL);
    }

}
