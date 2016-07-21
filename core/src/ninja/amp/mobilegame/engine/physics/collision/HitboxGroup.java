package ninja.amp.mobilegame.engine.physics.collision;

import java.util.Arrays;
import java.util.List;

public class HitboxGroup implements Hitbox {

    private List<Hitbox> hitboxes;

    public HitboxGroup(Hitbox... hitboxes) {
        this.hitboxes = Arrays.asList(hitboxes);
    }

    @Override
    public boolean intersects(Hitbox hitbox) {
        for (Hitbox h : hitboxes) {
            if (h.intersects(hitbox)) {
                return true;
            }
        }
        return false;
    }

}
