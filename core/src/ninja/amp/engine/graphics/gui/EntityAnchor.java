package ninja.amp.engine.graphics.gui;

import ninja.amp.engine.objects.entities.Entity;

public class EntityAnchor implements Anchor {

    private Entity entity;

    public EntityAnchor(Entity entity) {
        this.entity = entity;
    }

    @Override
    public float getX() {
        return entity.getBody().position().x;
    }

    @Override
    public float getY() {
        return entity.getBody().position().y;
    }

}
