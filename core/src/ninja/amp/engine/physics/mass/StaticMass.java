package ninja.amp.engine.physics.mass;

public class StaticMass implements Mass {

    private float mass;

    public StaticMass(float mass) {
        this.mass = mass;
    }

    @Override
    public float getMass() {
        return mass;
    }

}
