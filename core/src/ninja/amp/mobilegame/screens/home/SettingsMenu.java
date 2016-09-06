package ninja.amp.mobilegame.screens.home;

import ninja.amp.mobilegame.MobileGame;
import ninja.amp.mobilegame.engine.graphics.atlas.Atlas;
import ninja.amp.mobilegame.engine.graphics.RegionTexture;
import ninja.amp.mobilegame.engine.graphics.Texture;
import ninja.amp.mobilegame.engine.graphics.atlas.GameAtlas;
import ninja.amp.mobilegame.engine.gui.Object;
import ninja.amp.mobilegame.engine.gui.Origin;
import ninja.amp.mobilegame.engine.gui.ScreenAnchor;
import ninja.amp.mobilegame.engine.gui.StaticOffset;
import ninja.amp.mobilegame.engine.gui.buttons.Button;
import ninja.amp.mobilegame.engine.gui.buttons.PressableButton;
import ninja.amp.mobilegame.engine.gui.buttons.ToggleButton;
import ninja.amp.mobilegame.engine.gui.menus.Menu;
import ninja.amp.mobilegame.engine.transitions.MenuInTransition;
import ninja.amp.mobilegame.screens.Screen;

public class SettingsMenu extends Menu {

    public SettingsMenu(final Screen screen, final Menu parent) {
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
                MobileGame.options.setSound(toggled);
            }
        };
        soundToggle.setToggled(MobileGame.options.getSound());
        ToggleButton musicToggle = new ToggleButton(
                new RegionTexture(gui.findRegion("buttons/music_on"), screen),
                new RegionTexture(gui.findRegion("buttons/music_off"), screen),
                music, Origin.CENTER, new StaticOffset((music.getWidth() / 2) + 8, 0)) {
            @Override
            public void setToggled(boolean toggled) {
                super.setToggled(toggled);
                MobileGame.options.setMusic(toggled);
            }
        };
        musicToggle.setToggled(MobileGame.options.getMusic());
        Button controlsButton = new PressableButton(new RegionTexture(gui.findRegion("buttons/controls"), screen), larger_pressed, sound, Origin.CENTER, new StaticOffset(8, -sound.getHeight()));

        Button back = new PressableButton(new RegionTexture(gui.findRegion("buttons/exit"), screen), small_pressed, new ScreenAnchor(1, 1), Origin.TOP_RIGHT) {
            @Override
            public void click() {
                parent.setTransition(new MenuInTransition(parent, 0.3f));
                screen.setActiveMenu(parent);
            }
        };

        addObjects(sound, music);
        addButtons(soundToggle, musicToggle, controlsButton, back);

    }

}
