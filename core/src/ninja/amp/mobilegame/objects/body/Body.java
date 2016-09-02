package ninja.amp.mobilegame.objects.body;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import ninja.amp.mobilegame.objects.body.pose.Pose;
import ninja.amp.mobilegame.objects.body.pose.Poseable;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public abstract class Body implements Poseable {

    private Set<BodyPart> parts = new TreeSet<BodyPart>(new Comparator<BodyPart>() {
        @Override
        public int compare(BodyPart o1, BodyPart o2) {
            if (o1.getDepth() < o2.getDepth()) {
                return -1;
            } else if (o1.getDepth() > o2.getDepth()) {
                return 1;
            } else {
                return 0;
            }
        }
    });
    private Set<BodyPart> flippedParts = new TreeSet<BodyPart>(new Comparator<BodyPart>() {
        @Override
        public int compare(BodyPart o1, BodyPart o2) {
            if (o1.getFlippedDepth() < o2.getFlippedDepth()) {
                return -1;
            } else if (o1.getFlippedDepth() > o2.getFlippedDepth()) {
                return 1;
            } else {
                return 0;
            }
        }
    });
    private Pose pose;
    private float poseTime;

    @Override
    public Pose getPose() {
        return pose;
    }

    @Override
    public void setPose(Pose pose) {
        this.pose = pose;
        this.poseTime = 0;
        for (BodyPart part : parts) {
            part.setTargetPosition(pose.getPosition(part.getId()));
        }
    }

    @Override
    public float getPoseTime() {
        return poseTime;
    }

    public abstract Vector2 position();

    public abstract boolean flip();

    public void addBodyPart(BodyPart part) {
        parts.add(part);
        flippedParts.add(part);
    }

    public void draw(Batch batch, float delta) {
        poseTime += delta;

        boolean flipped = flip();
        if (flipped) {
            for (BodyPart part : flippedParts) {
                part.draw(batch, delta, true);
            }
        } else {
            for (BodyPart part : parts) {
                part.draw(batch, delta, false);
            }
        }
    }

    public void setScale(float scale) {
        for (BodyPart part : parts) {
            part.setScale(scale);
        }
    }

}
