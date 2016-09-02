package ninja.amp.mobilegame.objects.characters.movement.poses;

import com.badlogic.gdx.math.MathUtils;
import ninja.amp.mobilegame.objects.body.Body;
import ninja.amp.mobilegame.objects.body.pose.Pose;
import ninja.amp.mobilegame.objects.body.pose.position.Position;
import ninja.amp.mobilegame.objects.body.pose.position.StaticPosition;
import ninja.amp.mobilegame.objects.body.pose.position.TreePosition;

import java.util.HashMap;
import java.util.Map;

public class Running implements Pose {

    private Map<String, Position> positions = new HashMap<String, Position>();

    public Running(final Body body) {
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

        positions.put("head", new TreePosition(torso, 0.25f, 13f / 16f, 0.25f, 0, 0.5f, 0.5f, 0) {
            @Override
            public float getY() {
                return super.getY() + Math.abs(MathUtils.sin((body.getPoseTime() * 2.5f + 0.25f) * MathUtils.PI)) * 0.015625f;
            }
        });

        Position arm_left_upper = new TreePosition(torso, 0.25f, 5f / 8f, 1f / 16f, 1f / 16f, 1f / 8f, 3f / 8f, 180f) {
            @Override
            public float getRotation() {
                return super.getRotation() + MathUtils.sin(body.getPoseTime() * 2.5f * MathUtils.PI) * 45;
            }
        };
        positions.put("arm_left_upper", arm_left_upper);
        positions.put("arm_left_lower", new TreePosition(arm_left_upper, 0, 4f / 16f, 1f / 16f, 0, 1f / 8f, 3f / 8f, 0f) {
            int[] angles = {30, 45, 55, 60, 50, 30, 0, 15};

            @Override
            public float getRotation() {
                float current = body.getPoseTime() * 10 + 0.5f;
                int frame = (int) (current) % 8;
                return super.getRotation() + MathUtils.lerp(frame == 0 ? angles[7] : angles[frame - 1], angles[frame], current - (int) current);
            }
        });

        Position arm_right_upper = new TreePosition(torso, 0.25f, 5f / 8f, 1f / 16f, 1f / 16f, 1f / 8f, 3f / 8f, 180f) {
            @Override
            public float getRotation() {
                return super.getRotation() + MathUtils.sin((body.getPoseTime() * 2.5f + 1) * MathUtils.PI) * 45;
            }
        };
        positions.put("arm_right_upper", arm_right_upper);
        positions.put("arm_right_lower", new TreePosition(arm_right_upper, 0, 4f / 16f, 1f / 16f, 0, 1f / 8f, 3f / 8f, 0f) {
            int[] angles = {30, 45, 55, 60, 50, 30, 0, 15};

            @Override
            public float getRotation() {
                float current = body.getPoseTime() * 10 + 4.5f;
                int frame = (int) (current) % 8;
                return super.getRotation() + MathUtils.lerp(frame == 0 ? angles[7] : angles[frame - 1], angles[frame], current - (int) current);
            }
        });

        Position leg_left_upper = new TreePosition(torso, 0.25f, 2f / 8f, 1f / 16f, 7f / 16f, 1f / 8f, 3f / 8f, 0f) {
            @Override
            public float getRotation() {
                return super.getRotation() + MathUtils.sin((body.getPoseTime() * 2.5f + 0.25f) * MathUtils.PI) * 35 + 5;
            }
        };
        positions.put("leg_left_upper", leg_left_upper);
        positions.put("leg_left_lower", new TreePosition(leg_left_upper, 0, -3f / 8f, 1f / 16f, 7f / 16f, 1f / 8f, 3f / 8f, 0f) {
            int[] angles = {30, 15, 5, 0, 10, 30, 60, 45};

            @Override
            public float getRotation() {
                float current = body.getPoseTime() * 10 + 0.5f;
                int frame = (int) (current) % 8;
                return super.getRotation() - MathUtils.lerp(frame == 0 ? angles[7] : angles[frame - 1], angles[frame], current - (int) current);
            }
        });

        Position leg_right_upper = new TreePosition(torso, 0.25f, 2f / 8f, 1f / 16f, 7f / 16f, 1f / 8f, 3f / 8f, 0f) {
            @Override
            public float getRotation() {
                return super.getRotation() + MathUtils.sin((body.getPoseTime() * 2.5f + 1.25f) * MathUtils.PI) * 35 + 5;
            }
        };
        positions.put("leg_right_upper", leg_right_upper);
        positions.put("leg_right_lower", new TreePosition(leg_right_upper, 0, -3f / 8f, 1f / 16f, 7f / 16f, 1f / 8f, 3f / 8f, 0f) {
            int[] angles = {30, 15, 5, 0, 10, 30, 60, 45};

            @Override
            public float getRotation() {
                float current = body.getPoseTime() * 10 + 4.5f;
                int frame = (int) (current) % 8;
                return super.getRotation() - MathUtils.lerp(frame == 0 ? angles[7] : angles[frame - 1], angles[frame], current - (int) current);
            }
        });
    }

    @Override
    public Position getPosition(String id) {
        return positions.get(id);
    }

}
