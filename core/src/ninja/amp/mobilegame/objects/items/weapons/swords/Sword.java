package ninja.amp.mobilegame.objects.items.weapons.swords;

import ninja.amp.mobilegame.engine.graphics.Texture;
import ninja.amp.mobilegame.engine.physics.mass.Mass;
import ninja.amp.mobilegame.objects.characters.movement.Position;
import ninja.amp.mobilegame.objects.items.weapons.Weapon;

public abstract class Sword extends Weapon {

    public Sword(String name, Mass mass, Texture texture, Position position) {
        super(name, mass, texture, position);
    }

}
