package ninja.amp.mobilegame.objects.characters.npc;

import ninja.amp.mobilegame.engine.physics.mass.Mass;
import ninja.amp.mobilegame.map.World;
import ninja.amp.mobilegame.objects.Entity;
import ninja.amp.mobilegame.objects.characters.npc.ai.Agent;
import ninja.amp.mobilegame.engine.physics.collision.Hitbox;
import ninja.amp.mobilegame.engine.physics.vectors.LVector2;

public abstract class NPC extends Entity implements Agent {

    private State state;

    public NPC(World world, LVector2 position, LVector2 velocity, LVector2 acceleration, Mass mass, State state, Hitbox hitbox) {
        super(world, position, velocity, acceleration, mass, hitbox);
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
