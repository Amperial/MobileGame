package ninja.amp.engine.persistence;

import java.util.HashSet;
import java.util.Set;

public class Persistence {

    private Set<Persistent> data;

    public Persistence() {
        data = new HashSet<Persistent>();
    }

    public void attachData(Persistent persistent) {
        data.add(persistent);
    }

    public void save() {
        for (Persistent persistent : data) {
            persistent.save();
        }
    }

    public void load() {
        for (Persistent persistent : data) {
            persistent.load();
        }
    }

    public void dispose() {
        data.clear();
    }

}
