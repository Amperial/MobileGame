package ninja.amp.engine.transitions;

public class Transition {

    private Transitionable transitionable;
    private float time;
    private float transition = 0;

    public Transition(Transitionable transitionable, float time) {
        this.transitionable = transitionable;
        this.time = time;
    }

    public float getTime() {
        return time;
    }

    public boolean inTransition() {
        return transition > 0;
    }

    public float current() {
        return transition;
    }

    public void update(float delta) {
        transition += delta;
        if (transition >= time) {
            end();
        }
    }

    public void end() {
        transition = 0;
        transitionable.setTransition(null);
    }

}
