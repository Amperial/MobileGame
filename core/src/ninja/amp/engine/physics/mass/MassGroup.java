package ninja.amp.engine.physics.mass;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class MassGroup implements Mass {

    private Set<Mass> masses = new HashSet<Mass>();

    public MassGroup(Mass... masses) {
        this.masses.addAll(Arrays.asList(masses));
    }

    public void addMass(Mass mass) {
        masses.add(mass);
    }

    @Override
    public float getMass() {
        float total = 0;
        for (Mass mass : masses) {
            total += mass.getMass();
        }
        return total;
    }

}
