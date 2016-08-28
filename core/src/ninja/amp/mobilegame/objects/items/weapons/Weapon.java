package ninja.amp.mobilegame.objects.items.weapons;

import ninja.amp.mobilegame.engine.graphics.Texture;
import ninja.amp.mobilegame.engine.physics.mass.Mass;
import ninja.amp.mobilegame.objects.body.pose.position.Position;
import ninja.amp.mobilegame.objects.items.Item;
import ninja.amp.mobilegame.objects.items.ItemType;

public abstract class Weapon extends Item {

    private Texture texture;
    private Position position;

    public Weapon(String name, Mass mass, Texture texture, Position position) {
        super(ItemType.WEAPON, name, mass);
        this.texture = texture;
        this.position = position;
    }

    public Texture getTexture() {
        return texture;
    }

    public Position getPosition() {
        return position;
    }

}
