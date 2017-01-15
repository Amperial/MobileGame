package ninja.amp.engine;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ninja.amp.engine.graphics.gui.screens.Screen;
import ninja.amp.engine.persistence.Options;
import ninja.amp.engine.persistence.Persistence;
import ninja.amp.engine.resources.ResourceHandler;
import ninja.amp.engine.resources.audio.MusicManager;

public class Game extends com.badlogic.gdx.Game {

    public Persistence persistence;
    public MusicManager music;
    public ResourceHandler resources;

    public Options options;

    public Batch batch;

    @Override
    public void create() {
        persistence = new Persistence();
        options = new Options();
        persistence.attachData(options);
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
        persistence.save();
    }

    @Override
    public void resume() {
        persistence.load();
    }

    @Override
    public void dispose() {
        resources.dispose();
        batch.dispose();
        music = null;
        options = null;
        persistence = null;
    }

}
