package ninja.amp.engine.objects.entities.npc.ai.actions;

import java.util.Random;

public class RestRandom extends TimedAction {

    private static final Random RANDOM = new Random();

    private float minTime;
    private float maxTime;

    public RestRandom(float minTime, float maxTime) {
        this.minTime = minTime;
        this.maxTime = maxTime;
    }

    @Override
    public float getTime() {
        return RANDOM.nextFloat() * (maxTime - minTime) + minTime;
    }

}
