package ninja.amp.engine.objects.entities.npc;

import ninja.amp.engine.map.World;
import ninja.amp.engine.objects.entities.Entity;
import ninja.amp.engine.objects.entities.npc.ai.Agent;
import ninja.amp.engine.objects.entities.stats.Stat;
import ninja.amp.engine.physics.mass.Mass;
import ninja.amp.engine.physics.vectors.LVector2;

public abstract class NPC extends Entity implements Agent {

    private State state;

    public NPC(World world, LVector2 position, LVector2 velocity, LVector2 acceleration, Mass mass, State state, Stat health, Stat protection, Stat strength) {
        super(world, position, velocity, acceleration, mass, health, protection, strength);
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
