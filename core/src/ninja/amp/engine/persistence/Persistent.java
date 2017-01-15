package ninja.amp.engine.persistence;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import ninja.amp.engine.resources.ResourceHandler;
import ninja.amp.engine.resources.Savable;

public class Persistent implements Savable {

    private String name;
    protected Preferences persistent;

    public Persistent(String name, ResourceHandler handler) {
        this.name = "ninja.amp.mobilegame." + name;
        load();

        handler.attachResource(this);
    }

    public String getName() {
        return name;
    }

    @Override
    public void load() {
        persistent = Gdx.app.getPreferences(name);
    }

    @Override
    public void save() {
        persistent.flush();
    }

    @Override
    public void dispose() {
        save();
        name = null;
        persistent = null;
    }

}
