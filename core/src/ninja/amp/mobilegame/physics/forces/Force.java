package ninja.amp.mobilegame.physics.forces;

import com.badlogic.gdx.math.Vector2;
import ninja.amp.mobilegame.objects.Entity;

public abstract class Force {

    public abstract Vector2 calculate(Entity entity, float delta);

    public boolean persist(Entity entity, float delta) {
        return false;
    }

}
