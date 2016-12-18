package ninja.amp.engine;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ninja.amp.engine.graphics.gui.screens.Screen;
import ninja.amp.engine.persistence.Options;
import ninja.amp.engine.resources.ResourceHandler;
import ninja.amp.engine.resources.audio.MusicManager;

public class Game extends com.badlogic.gdx.Game {

    public Options options;
    public MusicManager music;
    public Batch batch;
    public ResourceHandler resources;

    @Override
    public void create() {
        options = new Options();
        music = new MusicManager(options);
        batch = new SpriteBatch();
        resources = new ResourceHandler();
    }

    public void setScreen(Screen screen) {
        if (getScreen() != null) {
            getScreen().dispose();
        }
        super.setScreen(screen);
    }

    @Override
    public void pause() {
        options.save();
    }

    @Override
    public void resume() {
        options.load();
    }

    @Override
    public void dispose() {
        resources.dispose();
        batch.dispose();
        music = null;
        options = null;
    }

}
