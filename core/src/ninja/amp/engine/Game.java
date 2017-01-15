package ninja.amp.engine;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ninja.amp.engine.graphics.gui.screens.Screen;
import ninja.amp.engine.persistence.Options;
import ninja.amp.engine.resources.ResourceHandler;
import ninja.amp.engine.resources.Savable;
import ninja.amp.engine.resources.audio.MusicManager;

public class Game extends com.badlogic.gdx.Game {

    public ResourceHandler resources;
    public Options options;
    public MusicManager music;
    public Batch batch;

    @Override
    public void create() {
        resources = new ResourceHandler();
        options = new Options(resources);
        music = new MusicManager(options);
        batch = new SpriteBatch();
    }

    public void setScreen(Screen screen) {
        if (getScreen() != null) {
            getScreen().dispose();
        }
        super.setScreen(screen);
    }

    @Override
    public void pause() {
        resources.save();
        if (getScreen() instanceof Savable) {
            ((Savable) getScreen()).save();
        }
    }

    @Override
    public void resume() {
        resources.load();
        if (getScreen() instanceof Savable) {
            ((Savable) getScreen()).load();
        }
    }

    @Override
    public void dispose() {
        batch.dispose();
        batch = null;
        music = null;
        options = null;
        resources.dispose();
        resources = null;
    }

}
