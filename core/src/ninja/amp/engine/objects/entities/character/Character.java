package ninja.amp.engine.objects.entities.character;

import ninja.amp.engine.map.World;
import ninja.amp.engine.objects.entities.Entity;
import ninja.amp.engine.objects.entities.character.movement.MoveController;
import ninja.amp.engine.objects.entities.character.movement.PoseController;
import ninja.amp.engine.objects.entities.character.movement.attack.AttackController;
import ninja.amp.engine.objects.entities.stats.Stat;
import ninja.amp.engine.physics.mass.Mass;
import ninja.amp.engine.physics.vectors.LVector2;

public abstract class Character extends Entity {

    protected AttackController attacker;
    protected MoveController move;
    protected PoseController pose;

    public Character(World world, LVector2 position, LVector2 velocity, LVector2 acceleration, Mass mass, Stat health, Stat protection, Stat strength) {
        super(world, position, velocity, acceleration, mass, health, protection, strength);
    }

    @Override
    public void initialize() {
        super.initialize();
        initializeAttacker();
        initializeMove();
        initializePose();
    }

    public abstract void initializeAttacker();

    public abstract void initializeMove();

    public abstract void initializePose();

    public AttackController getAttacker() {
        return attacker;
    }

    public MoveController getMove() {
        return move;
    }

    public PoseController getPose() {
        return pose;
    }

    @Override
    public void update(float delta) {
        // Update movement controller
        move.update(this, delta);

        // Update entity
        super.update(delta);

        // Update pose controller
        pose.update(this, delta);
    }

}
