package ninja.amp.game.objects.items.special;

import ninja.amp.engine.objects.items.Item;
import ninja.amp.engine.physics.mass.Mass;
import ninja.amp.game.objects.items.GameItemType;

public abstract class Special extends Item {

    public Special(String name, Mass mass) {
        super(GameItemType.SPECIAL, name, mass);
    }

}
