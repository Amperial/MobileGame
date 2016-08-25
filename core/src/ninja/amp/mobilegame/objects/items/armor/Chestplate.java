package ninja.amp.mobilegame.objects.items.armor;

import ninja.amp.mobilegame.engine.physics.mass.Mass;
import ninja.amp.mobilegame.objects.items.Item;
import ninja.amp.mobilegame.objects.items.ItemType;

public abstract class Chestplate extends Item {

    public Chestplate(String name, Mass mass) {
        super(ItemType.CHESTPLATE, name, mass);
    }

}
