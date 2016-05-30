package ninja.amp.mobilegame.objects.items.armor;

import ninja.amp.mobilegame.objects.items.Item;
import ninja.amp.mobilegame.objects.items.ItemType;

public abstract class Shield extends Item {

    public Shield(String name) {
        super(name, ItemType.SHIELD);
    }

}
