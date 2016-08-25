package ninja.amp.mobilegame.objects.items.weapons.bows;

import ninja.amp.mobilegame.engine.graphics.Texture;
import ninja.amp.mobilegame.engine.physics.mass.Mass;
import ninja.amp.mobilegame.objects.characters.movement.Position;
import ninja.amp.mobilegame.objects.items.weapons.Weapon;

public abstract class Bow extends Weapon {

    public Bow(String name, Mass mass, Texture texture, Position position) {
        super(name, mass, texture, position);
    }

}
