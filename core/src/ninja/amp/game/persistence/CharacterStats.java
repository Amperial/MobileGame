package ninja.amp.game.persistence;

import ninja.amp.engine.persistence.Persistent;
import ninja.amp.engine.resources.ResourceHandler;

public class CharacterStats extends Persistent {

    public CharacterStats(ResourceHandler handler) {
        super("stats", handler);
    }

    public int getHealth() {
        return persistent.getInteger("health");
    }

    public void setHealth(int health) {
        persistent.putInteger("health", health);
    }

    public int getProtection() {
        return persistent.getInteger("protection");
    }

    public void setProtection(int protection) {
        persistent.putInteger("protection", protection);
    }

    public int getStrength() {
        return persistent.getInteger("strength");
    }

    public void setStrength(int strength) {
        persistent.putInteger("strength", strength);
    }

}
