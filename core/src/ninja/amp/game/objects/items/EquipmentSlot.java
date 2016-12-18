package ninja.amp.game.objects.items;

import ninja.amp.engine.objects.items.Item;
import ninja.amp.engine.physics.mass.Mass;

public class EquipmentSlot implements Mass {

    private GameItemType type;
    private Item item;

    public EquipmentSlot(GameItemType type) {
        this.type = type;
    }

    public boolean hasItem() {
        return item != null;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public float getMass() {
        return item.getMass();
    }

}
