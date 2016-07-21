package ninja.amp.mobilegame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ninja.amp.mobilegame.screens.MainMenuScreen;
import ninja.amp.mobilegame.screens.Screen;

public class MobileGame extends Game {

    public SpriteBatch batch;
    public BitmapFont font;

	@Override
	public void create () {
        batch = new SpriteBatch();
        font = new BitmapFont();

        super.setScreen(new MainMenuScreen(this));
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
    public void dispose() {
        batch.dispose();
        font.dispose();
    }

}
