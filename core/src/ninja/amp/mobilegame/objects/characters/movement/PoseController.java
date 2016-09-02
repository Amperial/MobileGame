package ninja.amp.mobilegame.objects.characters.movement;

import ninja.amp.mobilegame.objects.Entity;
import ninja.amp.mobilegame.objects.body.pose.Pose;
import ninja.amp.mobilegame.objects.body.pose.Poseable;

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
