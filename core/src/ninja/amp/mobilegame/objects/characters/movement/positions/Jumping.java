package ninja.amp.mobilegame.objects.characters.movement.positions;

import ninja.amp.mobilegame.objects.characters.movement.Body;
import ninja.amp.mobilegame.objects.characters.movement.Position;

public class Jumping {

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
    public static Position HEAD = new Position(TORSO, 0.5f, 13f/16f, 0.5f, 0, 0.5f, 0.5f, 0);
    public static Position ARM_LEFT_UPPER = new Position(TORSO, 0.25f, 5f/8f, 1f/16f, 1f/16f, 1f/8f, 3f/8f, 105f);
    public static Position ARM_LEFT_LOWER = new Position(ARM_LEFT_UPPER, 0, 4f/16f, 1f/16f, 0, 1f/8f, 3f/8f, 15f);
    public static Position ARM_RIGHT_UPPER = new Position(TORSO, 0.25f, 5f/8f, 1f/16f, 1f/16f, 1f/8f, 3f/8f, 255f);
    public static Position ARM_RIGHT_LOWER = new Position(ARM_RIGHT_UPPER, 0, 4f/16f, 1f/16f, 0, 1f/8f, 3f/8f, -15f);
    public static Position LEG_LEFT_UPPER = new Position(TORSO, 0.25f, 2f/8f, 1f/16f, 7f/16f, 1f/8f, 3f/8f, 60f);
    public static Position LEG_LEFT_LOWER = new Position(LEG_LEFT_UPPER, 0, -3f/8f, 1f/16f, 7f/16f, 1f/8f, 3f/8f, -60f);
    public static Position LEG_RIGHT_UPPER = new Position(TORSO, 0.25f, 2f/8f, 1f/16f, 7f/16f, 1f/8f, 3f/8f, -30f);
    public static Position LEG_RIGHT_LOWER = new Position(LEG_RIGHT_UPPER, 0, -3f/8f, 1f/16f, 7f/16f, 1f/8f, 3f/8f, -15f);

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
