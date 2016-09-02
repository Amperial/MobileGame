package ninja.amp.mobilegame.objects.characters.movement.poses.attacks;

import java.util.HashMap;
import java.util.Map;

public abstract class AttackController {

    private Map<Attack.Type, Attack> attacks = new HashMap<Attack.Type, Attack>();
    private boolean attacking;
    private Attack attack;
    private boolean released;

    public AttackController(Attack... attacks) {
        for (Attack attack : attacks) {
            this.attacks.put(attack.getType(), attack);
        }
        attacking = false;
    }

    public boolean isAttacking() {
        return attacking;
    }

    public void beginAttacking() {
        setAttack(Attack.Type.STANDARD);
        for (Attack attack : attacks.values()) {
            attack.reset();
        }
        released = false;
        attacking = true;
    }

    public Attack getAttack() {
        return attack;
    }

    public void setAttack(Attack.Type type) {
        if (isAttacking()) {
            if (type == attack.getType()) {
                attack.reset();
            } else if (type == Attack.Type.FOLLOWUP) {
                attack = attacks.get(type);
            } else {
                float poseTime = attack.getPoseTime();
                attack = attacks.get(type);
                attack.update(poseTime);
            }
        } else {
            attack = attacks.get(type);
        }
    }

    public void update(float delta) {
        boolean pressed = isPressed();

        if (!isAttacking()) {
            if (pressed) {
                beginAttacking();
            } else {
                return;
            }
        }

        attack.update(delta);
        if (!pressed && !released) {
            released = true;
        }
        switch (attack.getState()) {
            case WINDUP:
                if (attack.getType() == Attack.Type.STANDARD && released && pressed) {
                    //setAttack(Attack.Type.COMBO);
                }
                break;
            case ATTACK:
                if (attack.getType() == Attack.Type.STANDARD && !released && pressed) {
                    //setAttack(Attack.Type.HEAVY);
                }
                break;
            case FOLLOWUP:
                if (attack.getType() != Attack.Type.FOLLOWUP && released && pressed) {
                    //setAttack(Attack.Type.FOLLOWUP);
                    //attack.update(delta);
                }
                break;
            case COMPLETE:
                attacking = false;
                attack = null;
                break;
        }
    }

    public abstract boolean isPressed();

}
