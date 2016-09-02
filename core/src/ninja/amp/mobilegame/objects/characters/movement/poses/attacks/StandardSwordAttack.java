package ninja.amp.mobilegame.objects.characters.movement.poses.attacks;

import ninja.amp.mobilegame.objects.body.Body;
import ninja.amp.mobilegame.objects.body.pose.Pose;
import ninja.amp.mobilegame.objects.body.pose.position.Position;
import ninja.amp.mobilegame.objects.body.pose.position.StaticPosition;
import ninja.amp.mobilegame.objects.body.pose.position.TreePosition;

import java.util.HashMap;
import java.util.Map;

public class StandardSwordAttack implements Pose {

    private Map<String, Position> positions = new HashMap<String, Position>();

    public StandardSwordAttack(final Body body) {
        // Two stages:
        // windup (0.15f long) - pull back body, arms and sword
        // attack (0.4f long) - move forward and swing sword

        Position torso = new StaticPosition(0, 0, 0, 0, 0.5f, 7f / 8f, 0) {
            /*
            @Override
            public float getRotation() {
                float poseTime = body.getPoseTime();
                if (poseTime < 0.25f) {
                    return super.getRotation() + (poseTime/0.25f)*10;
                } else if (poseTime < 0.35f) {
                    return super.getRotation() + (1 - ((poseTime-0.25f) / 0.35f))*10;
                    //return super.getRotation() + (1 - ((body.getPoseTime()-0.25f) / 0.25f))*15;
                } else {
                    return 0;
                }
            }
            */
            @Override
            public float getX() {
                return body.position().x + getWindupX();
            }

            @Override
            public float getY() {
                return body.position().y;
            }

            @Override
            public float getFlippedX() {
                return body.position().x - getWindupX() + getWidth();
            }

            private float getWindupX() {
                return 0f;
                /*
                float poseTime = body.getPoseTime();
                if (poseTime < 0.25f) {
                    return (poseTime/0.25f) * -0.1f;
                } else if (poseTime < 0.35f) {
                    return (1 - ((poseTime-0.25f) / 0.35f)) * -0.1f;
                } else {
                    return 0f;
                }
                */
            }
        };
        positions.put("torso", torso);

        positions.put("head", new TreePosition(torso, 0.25f, 13f / 16f, 0.25f, 0, 0.5f, 0.5f, 0));

        Position arm_left_upper = new TreePosition(torso, 0.25f, 5f / 8f, 1f / 16f, 1f / 16f, 1f / 8f, 3f / 8f, 180f) {
            @Override
            public float getRotation() {
                float poseTime = body.getPoseTime();
                if (poseTime < 0.15f) {
                    return super.getRotation();
                } else if (poseTime < 0.35f) {
                    return super.getRotation();
                } else {
                    return super.getRotation();
                }
            }
        };
        positions.put("arm_left_upper", arm_left_upper);
        positions.put("arm_left_lower", new TreePosition(arm_left_upper, 0, 4f / 16f, 1f / 16f, 0, 1f / 8f, 3f / 8f, 0f) {
            @Override
            public float getRotation() {
                float poseTime = body.getPoseTime();
                if (poseTime < 0.15f) {
                    return super.getRotation();
                } else if (poseTime < 0.35f) {
                    return super.getRotation();
                } else {
                    return super.getRotation();
                }
            }
        });

        Position arm_right_upper = new TreePosition(torso, 0.25f, 5f / 8f, 1f / 16f, 1f / 16f, 1f / 8f, 3f / 8f, 180f) {
            @Override
            public float getRotation() {
                float poseTime = body.getPoseTime();
                if (poseTime < 0.15f) {
                    return super.getRotation() + poseTime * 400;//raise arm +100
                } else if (poseTime < 0.35f) {
                    return super.getRotation() - 50 + (0.35f - poseTime) * 750;//start with arm +100, lower to -50
                } else {
                    return super.getRotation() + (0.5f - poseTime) * -50;//start with arm -50, raise to 0
                }
            }
        };
        positions.put("arm_right_upper", arm_right_upper);
        positions.put("arm_right_lower", new TreePosition(arm_right_upper, 0, 4f / 16f, 1f / 16f, 0, 1f / 8f, 3f / 8f, 0f) {
            @Override
            public float getRotation() {
                float poseTime = body.getPoseTime();
                if (poseTime < 0.15f) {
                    return super.getRotation() + poseTime * 300;//raise arm +75
                } else if (poseTime < 0.35f) {
                    return super.getRotation() - 25 + (0.35f - poseTime) * 500;//start with arm +75, lower to 0
                } else {
                    return super.getRotation() + (0.5f - poseTime) * -25;
                }
            }
        });

        Position leg_left_upper = new TreePosition(torso, 0.25f, 2f / 8f, 1f / 16f, 7f / 16f, 1f / 8f, 3f / 8f, 0f) {
            @Override
            public float getRotation() {
                float poseTime = body.getPoseTime();
                if (poseTime < 0.15f) {
                    return super.getRotation();
                } else if (poseTime < 0.35f) {
                    return super.getRotation();
                } else {
                    return super.getRotation();
                }
            }
        };
        positions.put("leg_left_upper", leg_left_upper);
        positions.put("leg_left_lower", new TreePosition(leg_left_upper, 0, -3f / 8f, 1f / 16f, 7f / 16f, 1f / 8f, 3f / 8f, 0f) {
            @Override
            public float getRotation() {
                float poseTime = body.getPoseTime();
                if (poseTime < 0.15f) {
                    return super.getRotation();
                } else if (poseTime < 0.35f) {
                    return super.getRotation();
                } else {
                    return super.getRotation();
                }
            }
        });

        Position leg_right_upper = new TreePosition(torso, 0.25f, 2f / 8f, 1f / 16f, 7f / 16f, 1f / 8f, 3f / 8f, 0f) {
            @Override
            public float getRotation() {
                float poseTime = body.getPoseTime();
                if (poseTime < 0.15f) {
                    return super.getRotation();
                } else if (poseTime < 0.35f) {
                    return super.getRotation();
                } else {
                    return super.getRotation();
                }
            }
        };
        positions.put("leg_right_upper", leg_right_upper);
        positions.put("leg_right_lower", new TreePosition(leg_right_upper, 0, -3f / 8f, 1f / 16f, 7f / 16f, 1f / 8f, 3f / 8f, 0f) {
            @Override
            public float getRotation() {
                float poseTime = body.getPoseTime();
                if (poseTime < 0.15f) {
                    return super.getRotation();
                } else if (poseTime < 0.35f) {
                    return super.getRotation();
                } else {
                    return super.getRotation();
                }
            }
        });
    }

    @Override
    public Position getPosition(String id) {
        return positions.get(id);
    }

}
