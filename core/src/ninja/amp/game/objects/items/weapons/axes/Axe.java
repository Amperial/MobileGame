package ninja.amp.game.objects.items.weapons.axes;

import ninja.amp.engine.graphics.textures.Texture;
import ninja.amp.engine.objects.body.pose.position.Position;
import ninja.amp.engine.physics.mass.Mass;
import ninja.amp.game.objects.items.weapons.Weapon;

public abstract class Axe extends Weapon {

    public Axe(String name, Mass mass, Texture texture, Position position) {
        super(name, mass, texture, position);
    }

}
