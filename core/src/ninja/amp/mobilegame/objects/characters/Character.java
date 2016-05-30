package ninja.amp.mobilegame.objects.characters;

import com.badlogic.gdx.math.Vector2;
import ninja.amp.mobilegame.map.World;
import ninja.amp.mobilegame.objects.Entity;
import ninja.amp.mobilegame.physics.vectors.LVector2;

public class Character extends Entity {

    private int level;

    public Character(World world, Vector2 position, LVector2 velocity, LVector2 acceleration, float mass) {
        super(world, position, velocity, acceleration, mass);
    }

}
