package ninja.amp.game.persistence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import ninja.amp.engine.persistence.Persistent;

public class CharacterStats implements Persistent {

    private Preferences stats;

    public CharacterStats() {
        load();
    }

    public int getHealth() {
        return stats.getInteger("health");
    }

    public void setHealth(int health) {
        stats.putInteger("health", health);
    }

    public int getProtection() {
        return stats.getInteger("protection");
    }

    public void setProtection(int protection) {
        stats.putInteger("protection", protection);
    }

    public int getStrength() {
        return stats.getInteger("strength");
    }

    public void setStrength(int strength) {
        stats.putInteger("strength", strength);
    }

    @Override
    public void save() {
        stats.flush();
    }

    @Override
    public void load() {
        stats = Gdx.app.getPreferences("stats");
    }

}
