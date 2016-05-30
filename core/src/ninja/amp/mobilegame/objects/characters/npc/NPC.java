package ninja.amp.mobilegame.objects.characters.npc;

import com.badlogic.gdx.math.Vector2;
import ninja.amp.mobilegame.map.World;
import ninja.amp.mobilegame.objects.characters.Character;
import ninja.amp.mobilegame.objects.characters.npc.ai.Agent;
import ninja.amp.mobilegame.physics.vectors.LVector2;

public abstract class NPC extends Character implements Agent {

    private State state;

    public NPC(World world, Vector2 position, LVector2 velocity, LVector2 acceleration, float mass, State state) {
        super(world, position, velocity, acceleration, mass);
        this.state = state;
    }

    public State getState() {
        return state;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        if (!hasAction()) {
            chooseAction();
        } else if (!getAction().canPerform()) {
            getAction().cancel();

            chooseAction();
        }

        getAction().perform(deltaTime);
    }

}
