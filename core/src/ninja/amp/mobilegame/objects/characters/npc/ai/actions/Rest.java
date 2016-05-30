package ninja.amp.mobilegame.objects.characters.npc.ai.actions;

public class Rest extends TimedAction {

    private float time;

    public Rest(float time) {
    }

    @Override
    public float getTime() {
        return time;
    }

}
