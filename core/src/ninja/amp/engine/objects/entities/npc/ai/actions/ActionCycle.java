package ninja.amp.engine.objects.entities.npc.ai.actions;

public class ActionCycle implements Action {

    private Action[] actions;
    private int current = 0;

    public ActionCycle(Action... actions) {
        this.actions = actions;
    }

    @Override
    public boolean canPerform() {
        return actions[current].canPerform();
    }

    @Override
    public void begin() {
        actions[current].begin();
    }

    @Override
    public void perform(float deltaTime) {
        for (int i = 0; i < actions.length; i++) {
            int next = (current + i) % actions.length;
            if (actions[next].canPerform() && !actions[next].isComplete()) {
                if (i != 0) {
                    actions[current].cancel();
                    current = next;
                    actions[current].begin();
                }
                actions[current].perform(deltaTime);
                return;
            }
        }
    }

    @Override
    public void cancel() {
        actions[current].cancel();
    }

    @Override
    public boolean isComplete() {
        return false;
    }

}
