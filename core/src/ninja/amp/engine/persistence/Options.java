package ninja.amp.engine.persistence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;

public class Options implements Persistent {

    private Preferences options;

    public Options() {
        load();
    }

    public boolean getSound() {
        return options.getBoolean("sound");
    }

    public void setSound(boolean sound) {
        options.putBoolean("sound", sound);
    }

    public boolean getMusic() {
        return options.getBoolean("music");
    }

    public void setMusic(boolean music) {
        options.putBoolean("music", music);
    }

    @Override
    public void save() {
        options.flush();
    }

    @Override
    public void load() {
        options = Gdx.app.getPreferences("options");
    }

}
