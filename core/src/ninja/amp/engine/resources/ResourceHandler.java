package ninja.amp.engine.resources;

import java.util.HashSet;
import java.util.Set;

public class ResourceHandler implements Resource {

    private Set<Resource> resources = new HashSet<Resource>();

    public void attachResource(Resource resource) {
        resources.add(resource);
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
