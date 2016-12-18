package ninja.amp.engine.transitions;

public interface Transitionable {

    boolean hasTransition();

    Transition getTransition();

    void setTransition(Transition transition);

}
