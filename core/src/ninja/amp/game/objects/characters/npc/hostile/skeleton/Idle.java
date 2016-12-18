package ninja.amp.game.objects.characters.npc.hostile.skeleton;

import com.badlogic.gdx.math.MathUtils;
import ninja.amp.engine.objects.body.Body;
import ninja.amp.engine.objects.body.pose.Pose;
import ninja.amp.engine.objects.body.pose.position.Position;
import ninja.amp.engine.objects.body.pose.position.StaticPosition;
import ninja.amp.engine.objects.body.pose.position.TreePosition;

import java.util.HashMap;
import java.util.Map;

public class Idle implements Pose {

    private Map<String, Position> positions = new HashMap<String, Position>();

    public Idle(final Body body) {
        Position torso = new StaticPosition(0, 0, 0, 0, 0.5f, 6f / 8f, 0) {
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

        positions.put("head", new TreePosition(torso, 0.25f, 11f / 16f, 0.25f, 0, 0.5f, 0.5f, 0) {
            @Override
            public float getY() {
                return super.getY() + Math.abs(MathUtils.sin((body.getPoseTime() * 2.5f + 0.25f) * MathUtils.PI)) * 0.03125f;
            }
        });

        Position arm_left_upper = new TreePosition(torso, 0.25f, 5f / 8f, 1f / 16f, 1f / 16f, 1f / 8f, 7f / 16f, 180f) {
            @Override
            public float getY() {
                return super.getY() + Math.abs(MathUtils.sin((body.getPoseTime() * 2.5f + 0.375f) * MathUtils.PI)) * 0.03125f;
            }
        };
        positions.put("arm_left_upper", arm_left_upper);
        positions.put("arm_left_lower", new TreePosition(arm_left_upper, 0, 6f / 16f, 1f / 16f, 0, 1f / 8f, 7f / 16f, 0f));

        Position arm_right_upper = new TreePosition(torso, 0.25f, 5f / 8f, 1f / 16f, 1f / 16f, 1f / 8f, 7f / 16f, 180f) {
            @Override
            public float getY() {
                return super.getY() + Math.abs(MathUtils.sin((body.getPoseTime() * 2.5f + 0.375f) * MathUtils.PI)) * 0.03125f;
            }
        };
        positions.put("arm_right_upper", arm_right_upper);
        positions.put("arm_right_lower", new TreePosition(arm_right_upper, 0, 6f / 16f, 1f / 16f, 0, 1f / 8f, 7f / 16f, 0f));

        Position leg_left_upper = new TreePosition(torso, 0.25f, 3f / 16f, 1f / 16f, 8f / 16f, 1f / 8f, 7f / 16f, 0f);
        positions.put("leg_left_upper", leg_left_upper);
        positions.put("leg_left_lower", new TreePosition(leg_left_upper, 0, -7f / 16f, 1f / 16f, 8f / 16f, 1f / 8f, 7f / 16f, 0f));

        Position leg_right_upper = new TreePosition(torso, 0.25f, 3f / 16f, 1f / 16f, 8f / 16f, 1f / 8f, 7f / 16f, 0f);
        positions.put("leg_right_upper", leg_right_upper);
        positions.put("leg_right_lower", new TreePosition(leg_right_upper, 0, -7f / 16f, 1f / 16f, 8f / 16f, 1f / 8f, 7f / 16f, 0f));
    }

    @Override
    public Position getPosition(String id) {
        return positions.get(id);
    }

}
