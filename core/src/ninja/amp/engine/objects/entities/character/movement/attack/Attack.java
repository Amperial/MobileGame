package ninja.amp.engine.objects.entities.character.movement.attack;

import ninja.amp.engine.objects.body.pose.Pose;
import ninja.amp.engine.objects.body.pose.Poseable;

public class Attack implements Poseable {

    private Pose pose;
    private Type type;
    private float duration;
    private float windup;
    private float followup;
    private float poseTime;

    public Attack(Pose pose, Type type, float duration, float windup, float followup) {
        this.pose = pose;
        this.type = type;
        this.duration = duration;
        this.windup = windup;
        this.followup = followup;
        this.poseTime = 0;
    }

    public Type getType() {
        return type;
    }

    public float getDuration() {
        return duration;
    }

    public float getWindup() {
        return windup;
    }

    public float getFollowup() {
        return followup;
    }

    @Override
    public Pose getPose() {
        return pose;
    }

    @Override
    public void setPose(Pose pose) {
        this.pose = pose;
        reset();
    }

    @Override
    public float getPoseTime() {
        return poseTime;
    }

    public void update(float delta) {
        poseTime += delta;
    }

    public void reset() {
        poseTime = 0;
    }

    public State getState() {
        if (poseTime > duration) {
            return State.COMPLETE;
        } else if (poseTime < windup) {
            return State.WINDUP;
        } else if ((duration - poseTime) < followup) {
            return State.FOLLOWUP;
        } else {
            return State.ATTACK;
        }
    }

    public enum State {
        WINDUP, // Attack is winding up and can change
        ATTACK, // Attack has begun and cannot change
        FOLLOWUP, // A followup attack can now be chained
        COMPLETE // Attack is complete
    }

    public enum Type {
        STANDARD, // Single press and release during windup
        HEAVY, // Single press and hold during windup
        COMBO, // Double press during windup
        FOLLOWUP // Press during followup

        /*
        OTHER ATTACK TYPE IDEAS:
        JUMP ATTACK: Press jump button during windup
        AERIAL ATTACK: Press attack button midair
         */
    }

}
