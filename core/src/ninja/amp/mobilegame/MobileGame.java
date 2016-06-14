package ninja.amp.mobilegame;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import ninja.amp.mobilegame.screens.MainMenuScreen;

public class MobileGame extends Game {

    public SpriteBatch batch;
    public BitmapFont font;

	@Override
	public void create () {
        batch = new SpriteBatch();
        font = new BitmapFont();

        Color menu = new Color(85f / 255f, 98f / 255f, 112f / 255f, 1f);
        Color outline = new Color(113f / 255f, 129f / 255f, 147f / 255f, 1f);
        
        

        this.setScreen(new MainMenuScreen(this));
        
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
