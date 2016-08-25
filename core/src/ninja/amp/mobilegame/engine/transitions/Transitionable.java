package ninja.amp.mobilegame.engine.transitions;

public interface Transitionable {

    boolean hasTransition();

    Transition getTransition();

    void setTransition(Transition transition);

}
