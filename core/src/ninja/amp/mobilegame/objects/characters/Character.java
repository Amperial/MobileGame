package ninja.amp.mobilegame.objects.characters;

import ninja.amp.mobilegame.engine.physics.mass.Mass;
import ninja.amp.mobilegame.map.World;
import ninja.amp.mobilegame.objects.Entity;
import ninja.amp.mobilegame.engine.physics.collision.Hitbox;
import ninja.amp.mobilegame.engine.physics.vectors.LVector2;

public class Character extends Entity {

    public Character(World world, LVector2 position, LVector2 velocity, LVector2 acceleration, Mass mass, Hitbox hitbox) {
        super(world, position, velocity, acceleration, mass, hitbox);
    }

}
