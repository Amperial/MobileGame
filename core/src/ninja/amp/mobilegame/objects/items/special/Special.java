package ninja.amp.mobilegame.objects.items.special;

import ninja.amp.mobilegame.engine.physics.mass.Mass;
import ninja.amp.mobilegame.objects.items.Item;
import ninja.amp.mobilegame.objects.items.ItemType;

public abstract class Special extends Item {

    public Special(String name, Mass mass) {
        super(ItemType.SPECIAL, name, mass);
    }

}
