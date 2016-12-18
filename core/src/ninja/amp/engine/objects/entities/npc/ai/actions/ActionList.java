package ninja.amp.engine.objects.entities.npc.ai.actions;

public class ActionList implements Action {

    private Action[] actions;
    private int current = -1;

    public ActionList(Action... actions) {
        this.actions = actions;
    }

    @Override
    public boolean canPerform() {
        for (Action action : actions) {
            if (action.canPerform()) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void begin() {
    }

    @Override
    public void perform(float deltaTime) {
        for (int i = 0; i < actions.length; i++) {
            if (actions[i].canPerform()) {
                if (current != i) {
                    if (current >= 0) {
                        actions[current].cancel();
                    }
                    current = i;
                    actions[current].begin();
                }
                actions[current].perform(deltaTime);
                return;
            }
        }
        current = -1;
    }

    @Override
    public void cancel() {
        if (current >= 0) {
            actions[current].cancel();
        }
    }

    @Override
    public boolean isComplete() {
        return false;
    }

}
