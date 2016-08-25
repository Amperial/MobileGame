package ninja.amp.mobilegame.objects.characters.npc.ai.actions;

import ninja.amp.mobilegame.objects.Entity;

public class Idle implements Action {

    private Entity entity;

    public Idle(Entity entity) {
        this.entity = entity;
    }

    @Override
    public boolean canPerform() {
        return true;
    }

    @Override
    public void begin() {
        entity.getVelocity().set(0, 0);
    }

    @Override
    public void perform(float deltaTime) {
    }

    @Override
    public void cancel() {
    }

    @Override
    public boolean isComplete() {
        return false;
    }

}
