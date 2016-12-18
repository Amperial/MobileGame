package ninja.amp.game.objects.items.armor;

import ninja.amp.engine.objects.items.Item;
import ninja.amp.engine.physics.mass.Mass;
import ninja.amp.game.objects.items.GameItemType;

public abstract class Helmet extends Item {

    public Helmet(String name, Mass mass) {
        super(GameItemType.HELMET, name, mass);
    }

}
