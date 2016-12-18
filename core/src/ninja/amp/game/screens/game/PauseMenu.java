package ninja.amp.game.screens.game;

import ninja.amp.engine.graphics.gui.Origin;
import ninja.amp.engine.graphics.gui.StaticOffset;
import ninja.amp.engine.graphics.gui.buttons.Button;
import ninja.amp.engine.graphics.gui.buttons.PressableButton;
import ninja.amp.engine.graphics.gui.menus.Menu;
import ninja.amp.engine.graphics.gui.screens.ScreenAnchor;
import ninja.amp.engine.graphics.textures.RegionTexture;
import ninja.amp.engine.graphics.textures.Texture;
import ninja.amp.engine.graphics.textures.atlas.Atlas;
import ninja.amp.game.MobileGame;
import ninja.amp.game.screens.home.HomeScreen;

public class PauseMenu extends Menu {

    private Button playButton;

    public PauseMenu(final MobileGame game, final GameScreen screen, Atlas gui) {
        super(screen);

        playButton = new Button(new RegionTexture(gui.findRegion("play"), screen), new ScreenAnchor(1, 1), Origin.TOP_RIGHT, new StaticOffset(-1, -1)) {
            @Override
            public void click() {
                screen.setPaused(false);
                screen.transitionMenu(screen.getMenu("control"), 0f, 0f);
            }
        };
        Texture larger_pressed = new RegionTexture(gui.findRegion("buttons/larger_pressed"), screen);
        Button settingsButton = new PressableButton(new RegionTexture(gui.findRegion("buttons/settings"), screen), larger_pressed, new ScreenAnchor(0.5f, 0.5f), Origin.CENTER) {
            @Override
            public void click() {
                screen.transitionMenu(screen.getMenu("settings"), 0.2f, 0.3f);
            }
        };
        Button resumeButton = new PressableButton(new RegionTexture(gui.findRegion("buttons/resume"), screen), larger_pressed, settingsButton, Origin.CENTER, new StaticOffset(0, settingsButton.getHeight())) {
            @Override
            public void click() {
                screen.setPaused(false);
                screen.transitionMenu(screen.getMenu("control"), 0f, 0f);
            }
        };
        Button quitButton = new PressableButton(new RegionTexture(gui.findRegion("buttons/quit"), screen), larger_pressed, settingsButton, Origin.CENTER, new StaticOffset(0, -settingsButton.getHeight())) {
            @Override
            public void click() {
                game.setScreen(new HomeScreen(game));
            }
        };

        addButtons(playButton, settingsButton, resumeButton, quitButton);
    }

    @Override
    public void setScale(float scale) {
        super.setScale(scale);

        playButton.setScale(scale * 0.625f);
    }

}
