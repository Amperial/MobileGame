package ninja.amp.mobilegame.objects.items.armor;

import ninja.amp.mobilegame.engine.physics.mass.Mass;
import ninja.amp.mobilegame.objects.items.Item;
import ninja.amp.mobilegame.objects.items.ItemType;

public abstract class Ring extends Item {

    public Ring(String name, Mass mass) {
        super(ItemType.RING, name, mass);
    }

}
