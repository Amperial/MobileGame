package ninja.amp.mobilegame.objects.items.armor;

import ninja.amp.mobilegame.engine.physics.mass.Mass;
import ninja.amp.mobilegame.objects.items.Item;
import ninja.amp.mobilegame.objects.items.ItemType;

public abstract class Leggings extends Item {

    public Leggings(String name, Mass mass) {
        super(ItemType.LEGGINGS, name, mass);
    }

}
