package ninja.amp.mobilegame.engine.physics.collision;

import com.badlogic.gdx.math.Rectangle;
import ninja.amp.mobilegame.objects.Entity;

public class EntityHitbox extends RectangleHitbox {

    private Entity entity;

    public EntityHitbox(Entity entity, Rectangle rectangle) {
        super(rectangle);

        this.entity = entity;
    }

    @Override
    public float getX() {
        return entity.getPosition().x;
    }

    @Override
    public float getY() {
        return entity.getPosition().y;
    }

}
