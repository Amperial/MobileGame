package ninja.amp.engine.objects.entities.npc.ai.actions;

public abstract class TimedAction implements Action {

    private float remaining = -1;

    public abstract float getTime();

    public float getRemaining() {
        return remaining;
    }

    @Override
    public boolean canPerform() {
        return true;
    }

    @Override
    public void begin() {
        remaining = getTime();
    }

    @Override
    public void perform(float deltaTime) {
        remaining -= deltaTime;
        if (remaining < 0) {
            remaining = 0;
        }
    }

    @Override
    public void cancel() {
        remaining = -1;
    }

    @Override
    public boolean isComplete() {
        return remaining == 0;
    }

}
