package ninja.amp.mobilegame.objects.characters.movement.positions;

import com.badlogic.gdx.math.MathUtils;
import ninja.amp.mobilegame.objects.characters.movement.Body;
import ninja.amp.mobilegame.objects.characters.movement.Position;

public class Running {

    public static Body BODY;
    public static Position TORSO = new Position(null, 0, 0, 0, 0, 0.5f, 7f/8f, 0) {
        @Override
        public float getX(boolean flipped) {
            if (flipped) {
                return BODY.position().x + getWidth();
            } else {
                return BODY.position().x;
            }
        }
        @Override
        public float getY(boolean flipped) {
            return BODY.position().y;
        }
        @Override
        public float getRotation() {
            return 0;
        }
    };
    public static Position HEAD = new Position(TORSO, 0.5f, 13f/16f, 0.5f, 0, 0.5f, 0.5f, 0) {
        @Override
        public float getY(boolean flipped) {
            return super.getY(flipped) + (float)Math.abs(Math.sin((getTotalTime()*2.5f + 0.25f)*Math.PI))*0.03125f;
        }
    };
    public static Position ARM_LEFT_UPPER = new Position(TORSO, 0.25f, 5f/8f, 1f/16f, 1f/16f, 1f/8f, 3f/8f, 180f) {
        @Override
        public float getRotation() {
            return super.getRotation() + (float)Math.sin((getTotalTime()*2.5f + 1)*Math.PI)*45;
        }
    };
    public static Position ARM_LEFT_LOWER = new Position(ARM_LEFT_UPPER, 0, 4f/16f, 1f/16f, 0, 1f/8f, 3f/8f, 0f) {
        int[] angles = {30, 45, 55, 60, 50, 30, 0, 15};
        @Override
        public float getRotation() {
            float current = getTotalTime()*10 + 4.5f;
            int frame = (int)(current)%8;
            return super.getRotation() + MathUtils.lerp(frame == 0 ? angles[7] : angles[frame - 1], angles[frame], current - (int)current);
        }
    };
    public static Position ARM_RIGHT_UPPER = new Position(TORSO, 0.25f, 5f/8f, 1f/16f, 1f/16f, 1f/8f, 3f/8f, 180f) {
        @Override
        public float getRotation() {
            return super.getRotation() + (float)Math.sin(getTotalTime()*2.5f*Math.PI)*45;
        }
    };
    public static Position ARM_RIGHT_LOWER = new Position(ARM_RIGHT_UPPER, 0, 4f/16f, 1f/16f, 0, 1f/8f, 3f/8f, 0f) {
        int[] angles = {30, 45, 55, 60, 50, 30, 0, 15};
        @Override
        public float getRotation() {
            float current = getTotalTime()*10 + 0.5f;
            int frame = (int)(current)%8;
            return super.getRotation() + MathUtils.lerp(frame == 0 ? angles[7] : angles[frame - 1], angles[frame], current - (int)current);
        }
    };
    public static Position LEG_LEFT_UPPER = new Position(TORSO, 0.25f, 2f/8f, 1f/16f, 7f/16f, 1f/8f, 3f/8f, 0f) {
        @Override
        public float getRotation() {
            return super.getRotation() + (float)Math.sin((getTotalTime()*2.5f + 0.25f)*Math.PI)*35 + 5;
        }
    };
    public static Position LEG_LEFT_LOWER = new Position(LEG_LEFT_UPPER, 0, -3f/8f, 1f/16f, 7f/16f, 1f/8f, 3f/8f, 0f) {
        int[] angles = {30, 15, 5, 0, 10, 30, 60, 45};
        @Override
        public float getRotation() {
            float current = getTotalTime()*10 + 0.5f;
            int frame = (int)(current)%8;
            return super.getRotation() - MathUtils.lerp(frame == 0 ? angles[7] : angles[frame - 1], angles[frame], current - (int)current);
        }
    };
    public static Position LEG_RIGHT_UPPER = new Position(TORSO, 0.25f, 2f/8f, 1f/16f, 7f/16f, 1f/8f, 3f/8f, 0f) {
        @Override
        public float getRotation() {
            return super.getRotation() + (float)Math.sin((getTotalTime()*2.5f + 1.25f)*Math.PI)*35 + 5;
        }
    };
    public static Position LEG_RIGHT_LOWER = new Position(LEG_RIGHT_UPPER, 0, -3f/8f, 1f/16f, 7f/16f, 1f/8f, 3f/8f, 0f) {
        int[] angles = {30, 15, 5, 0, 10, 30, 60, 45};
        @Override
        public float getRotation() {
            float current = getTotalTime()*10 + 4.5f;
            int frame = (int)(current)%8;
            return super.getRotation() - MathUtils.lerp(frame == 0 ? angles[7] : angles[frame - 1], angles[frame], current - (int)current);
        }
    };

    public static void resetTotalTime() {
        TORSO.resetTotalTime();
        HEAD.resetTotalTime();
        ARM_LEFT_UPPER.resetTotalTime();
        ARM_LEFT_LOWER.resetTotalTime();
        ARM_RIGHT_UPPER.resetTotalTime();
        ARM_RIGHT_LOWER.resetTotalTime();
        LEG_LEFT_UPPER.resetTotalTime();
        LEG_LEFT_LOWER.resetTotalTime();
        LEG_RIGHT_UPPER.resetTotalTime();
        LEG_RIGHT_LOWER.resetTotalTime();
    }

}
