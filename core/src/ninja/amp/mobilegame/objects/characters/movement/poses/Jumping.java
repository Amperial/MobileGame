package ninja.amp.mobilegame.objects.characters.movement.poses;

import ninja.amp.mobilegame.objects.body.Body;
import ninja.amp.mobilegame.objects.body.pose.Pose;
import ninja.amp.mobilegame.objects.body.pose.position.Position;
import ninja.amp.mobilegame.objects.body.pose.position.StaticPosition;
import ninja.amp.mobilegame.objects.body.pose.position.TreePosition;

import java.util.HashMap;
import java.util.Map;

public class Jumping implements Pose {

    private Map<String, Position> positions = new HashMap<String, Position>();

    public Jumping(final Body body) {
        Position torso = new StaticPosition(0, 0, 0, 0, 0.5f, 7f / 8f, 0) {
            @Override
            public float getX() {
                return body.position().x;
            }

            @Override
            public float getY() {
                return body.position().y;
            }

            @Override
            public float getFlippedX() {
                return body.position().x + getWidth();
            }
        };
        positions.put("torso", torso);

        positions.put("head", new TreePosition(torso, 0.25f, 13f / 16f, 0.25f, 0, 0.5f, 0.5f, 0));

        Position arm_left_upper = new TreePosition(torso, 0.25f, 5f / 8f, 1f / 16f, 1f / 16f, 1f / 8f, 3f / 8f, 135f) {//105f
            @Override
            public float getRotation() {
                return super.getRotation() - body.getPoseTime() * 60;
            }
        };
        positions.put("arm_left_upper", arm_left_upper);
        positions.put("arm_left_lower", new TreePosition(arm_left_upper, 0, 4f / 16f, 1f / 16f, 0, 1f / 8f, 3f / 8f, 15f) {//15f
            @Override
            public float getRotation() {
                return super.getRotation() - body.getPoseTime() * 10;
            }
        });

        Position arm_right_upper = new TreePosition(torso, 0.25f, 5f / 8f, 1f / 16f, 1f / 16f, 1f / 8f, 3f / 8f, 225f) {//255f
            @Override
            public float getRotation() {
                return super.getRotation() + body.getPoseTime() * 60;
            }
        };
        positions.put("arm_right_upper", arm_right_upper);
        positions.put("arm_right_lower", new TreePosition(arm_right_upper, 0, 4f / 16f, 1f / 16f, 0, 1f / 8f, 3f / 8f, 15f) {//-15f
            @Override
            public float getRotation() {
                return super.getRotation() + body.getPoseTime() * 10;
            }
        });

        Position leg_left_upper = new TreePosition(torso, 0.25f, 2f / 8f, 1f / 16f, 7f / 16f, 1f / 8f, 3f / 8f, 45f) {//45, forward
            @Override
            public float getRotation() {
                return super.getRotation() + body.getPoseTime() * 10;
            }
        };
        positions.put("leg_left_upper", leg_left_upper);
        positions.put("leg_left_lower", new TreePosition(leg_left_upper, 0, -3f / 8f, 1f / 16f, 7f / 16f, 1f / 8f, 3f / 8f, -30f) {
            @Override
            public float getRotation() {
                return super.getRotation() - body.getPoseTime() * 10;
            }
        });

        Position leg_right_upper = new TreePosition(torso, 0.25f, 2f / 8f, 1f / 16f, 7f / 16f, 1f / 8f, 3f / 8f, -20f) {//-30, back
            @Override
            public float getRotation() {
                return super.getRotation() - body.getPoseTime() * 10;
            }
        };
        positions.put("leg_right_upper", leg_right_upper);
        positions.put("leg_right_lower", new TreePosition(leg_right_upper, 0, -3f / 8f, 1f / 16f, 7f / 16f, 1f / 8f, 3f / 8f, -15f) {
            @Override
            public float getRotation() {
                return super.getRotation() - body.getPoseTime() * 10;
            }
        });
    }

    @Override
    public Position getPosition(String id) {
        return positions.get(id);
    }

}
