package ninja.amp.mobilegame.objects.items.weapons.spears;

import ninja.amp.mobilegame.engine.graphics.Texture;
import ninja.amp.mobilegame.engine.physics.mass.Mass;
import ninja.amp.mobilegame.objects.body.pose.position.Position;
import ninja.amp.mobilegame.objects.items.weapons.Weapon;

public abstract class Spear extends Weapon {

    public Spear(String name, Mass mass, Texture texture, Position position) {
        super(name, mass, texture, position);
    }

}
