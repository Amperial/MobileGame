package ninja.amp.game.screens.home;

import ninja.amp.engine.graphics.gui.Object;
import ninja.amp.engine.graphics.gui.Origin;
import ninja.amp.engine.graphics.gui.StaticOffset;
import ninja.amp.engine.graphics.gui.buttons.Button;
import ninja.amp.engine.graphics.gui.buttons.PressableButton;
import ninja.amp.engine.graphics.gui.buttons.ToggleButton;
import ninja.amp.engine.graphics.gui.menus.Menu;
import ninja.amp.engine.graphics.gui.screens.Screen;
import ninja.amp.engine.graphics.gui.screens.ScreenAnchor;
import ninja.amp.engine.graphics.textures.RegionTexture;
import ninja.amp.engine.graphics.textures.Texture;
import ninja.amp.engine.graphics.textures.atlas.Atlas;
import ninja.amp.engine.graphics.textures.atlas.GameAtlas;
import ninja.amp.game.MobileGame;

public class SettingsMenu extends Menu {

    public SettingsMenu(final MobileGame game, final Screen screen, final Menu parent) {
        super(screen);

        Atlas gui = new Atlas(GameAtlas.GUI, screen);

        Texture small_pressed = new RegionTexture(gui.findRegion("buttons/small_pressed"), screen);
        Texture large_pressed = new RegionTexture(gui.findRegion("buttons/large_pressed"), screen);
        Texture larger_pressed = new RegionTexture(gui.findRegion("buttons/larger_pressed"), screen);

        Object sound = new Object(new RegionTexture(gui.findRegion("buttons/sound"), screen), new ScreenAnchor(0.5f, 0.5f), Origin.CENTER, new StaticOffset(-8, 0));
        Object music = new Object(new RegionTexture(gui.findRegion("buttons/music"), screen), sound, Origin.CENTER, new StaticOffset(0, sound.getHeight()));
        ToggleButton soundToggle = new ToggleButton(
                new RegionTexture(gui.findRegion("buttons/sound_on"), screen),
                new RegionTexture(gui.findRegion("buttons/sound_off"), screen),
                sound, Origin.CENTER, new StaticOffset((sound.getWidth() / 2) + 8, 0)) {
            @Override
            public void setToggled(boolean toggled) {
                super.setToggled(toggled);
                game.options.setSound(toggled);
            }
        };
        soundToggle.setToggled(game.options.getSound());
        ToggleButton musicToggle = new ToggleButton(
                new RegionTexture(gui.findRegion("buttons/music_on"), screen),
                new RegionTexture(gui.findRegion("buttons/music_off"), screen),
                music, Origin.CENTER, new StaticOffset((music.getWidth() / 2) + 8, 0)) {
            @Override
            public void setToggled(boolean toggled) {
                super.setToggled(toggled);
                if (toggled) {
                    game.music.enable();
                } else {
                    game.music.disable();
                }
            }
        };
        musicToggle.setToggled(game.options.getMusic());
        Button controlsButton = new PressableButton(new RegionTexture(gui.findRegion("buttons/controls"), screen), larger_pressed, sound, Origin.CENTER, new StaticOffset(8, -sound.getHeight()));

        Button back = new PressableButton(new RegionTexture(gui.findRegion("buttons/exit"), screen), small_pressed, new ScreenAnchor(1, 1), Origin.TOP_RIGHT) {
            @Override
            public void click() {
                screen.transitionMenu(parent, 0.2f, 0.3f);
            }
        };

        addObjects(sound, music);
        addButtons(soundToggle, musicToggle, controlsButton, back);

    }

}
