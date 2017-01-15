package ninja.amp.engine.resources;

import java.util.HashSet;
import java.util.Set;

public class ResourceHandler implements Savable {

    private Set<Resource> resources = new HashSet<Resource>();

    public void attachResource(Resource resource) {
        resources.add(resource);
    }

    @Override
    public void save() {
        for (Resource resource : resources) {
            if (resource instanceof Savable) {
                ((Savable) resource).save();
            }
        }
    }

    @Override
    public void load() {
        for (Resource resource : resources) {
            if (resource instanceof Savable) {
                ((Savable) resource).load();
            }
        }
    }

    @Override
    public void dispose() {
        for (Resource resource : resources) {
            resource.dispose();
        }
        resources.clear();
        resources = null;
    }

}
