package ninja.amp.engine.physics.forces;

import com.badlogic.gdx.math.Vector2;
import ninja.amp.engine.objects.entities.Entity;

public abstract class Force {

    public abstract Vector2 calculate(Entity entity, float delta);

}
