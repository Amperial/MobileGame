package ninja.amp.engine.objects.entities.character.movement;

import ninja.amp.engine.objects.body.pose.Pose;
import ninja.amp.engine.objects.body.pose.Poseable;
import ninja.amp.engine.objects.entities.Entity;

public abstract class PoseController {

    private Poseable poseable;

    public PoseController(Poseable poseable) {
        this.poseable = poseable;
    }

    public void update(Entity entity, float delta) {
        Pose pose = getPose(getMove());
        if (!pose.equals(poseable.getPose())) {
            poseable.setPose(pose);
        }
    }

    public abstract Move getMove();

    public abstract Pose getPose(Move move);

}
