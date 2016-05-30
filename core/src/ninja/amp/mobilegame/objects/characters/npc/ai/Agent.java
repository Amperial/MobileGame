package ninja.amp.mobilegame.objects.characters.npc.ai;

import ninja.amp.mobilegame.objects.characters.npc.ai.actions.Action;

public interface Agent {

    boolean hasAction();//has action that is not complete

    Action getAction();//get current action

    void chooseAction();//choose and begin next action

}
