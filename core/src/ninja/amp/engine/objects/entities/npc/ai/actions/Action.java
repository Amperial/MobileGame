package ninja.amp.engine.objects.entities.npc.ai.actions;

public interface Action {

    boolean canPerform();//can begin or continue to perform the action

    void begin();//called when action is beginning to be performed

    void perform(float deltaTime);//continue performing the action

    void cancel();//cancel performing the action

    boolean isComplete();//checks if an action is complete

}
