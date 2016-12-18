package ninja.amp.game.objects.items.weapons;

import ninja.amp.engine.graphics.textures.Texture;
import ninja.amp.engine.objects.body.pose.position.Position;
import ninja.amp.engine.objects.items.Item;
import ninja.amp.engine.physics.mass.Mass;
import ninja.amp.game.objects.items.GameItemType;

public abstract class Weapon extends Item {

    private Texture texture;
    private Position position;

    public Weapon(String name, Mass mass, Texture texture, Position position) {
        super(GameItemType.WEAPON, name, mass);
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
