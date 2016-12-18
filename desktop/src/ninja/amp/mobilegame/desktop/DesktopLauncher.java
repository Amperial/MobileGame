package ninja.amp.mobilegame.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
import ninja.amp.game.MobileGame;
import ninja.amp.engine.graphics.textures.atlas.GameAtlas;

public class DesktopLauncher {

	public static void main (String[] arg) {
        TexturePacker.Settings settings = new TexturePacker.Settings();
        settings.duplicatePadding = true;
        settings.combineSubdirectories = true;
        for (GameAtlas atlas : GameAtlas.values()) {
            TexturePacker.process(settings, atlas.getFileName(), "./", atlas.getFileName());
        }

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.width = 1280;
		config.height = 720;
		config.vSyncEnabled = false;
		config.foregroundFPS = 0;
		config.backgroundFPS = 0;
		new LwjglApplication(new MobileGame(), config);
	}

}
