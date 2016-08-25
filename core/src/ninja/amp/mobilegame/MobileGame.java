package ninja.amp.mobilegame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ninja.amp.mobilegame.engine.persistence.Options;
import ninja.amp.mobilegame.screens.home.HomeScreen;
import ninja.amp.mobilegame.screens.Screen;

public class MobileGame extends Game {

    public static Options options;

    public Batch batch;

	@Override
	public void create () {
        options = new Options();

        batch = new SpriteBatch();

        super.setScreen(new HomeScreen(this));
	}

    public void setScreen(Screen screen) {
        getScreen().dispose();
        super.setScreen(screen);
    }

    @Override
    public void render() {
        super.render();
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
        batch.dispose();
    }

}
