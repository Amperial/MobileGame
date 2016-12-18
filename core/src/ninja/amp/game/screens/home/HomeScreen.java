package ninja.amp.game.screens.home;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import ninja.amp.engine.graphics.backgrounds.Background;
import ninja.amp.engine.graphics.backgrounds.BackgroundGroup;
import ninja.amp.engine.graphics.backgrounds.BackgroundLayer;
import ninja.amp.engine.graphics.backgrounds.TileMode;
import ninja.amp.engine.graphics.gui.Origin;
import ninja.amp.engine.graphics.gui.StaticOffset;
import ninja.amp.engine.graphics.gui.buttons.Button;
import ninja.amp.engine.graphics.gui.buttons.PressableButton;
import ninja.amp.engine.graphics.gui.menus.Menu;
import ninja.amp.engine.graphics.gui.menus.MenuInTransition;
import ninja.amp.engine.graphics.gui.screens.Screen;
import ninja.amp.engine.graphics.gui.screens.ScreenAnchor;
import ninja.amp.engine.graphics.textures.RegionTexture;
import ninja.amp.engine.graphics.textures.SingleTexture;
import ninja.amp.engine.graphics.textures.Texture;
import ninja.amp.engine.graphics.textures.atlas.Atlas;
import ninja.amp.engine.graphics.textures.atlas.GameAtlas;
import ninja.amp.engine.map.MapLoader;
import ninja.amp.engine.resources.audio.Music;
import ninja.amp.engine.resources.audio.Sound;
import ninja.amp.game.MobileGame;
import ninja.amp.game.screens.game.GameScreen;

public class HomeScreen extends Screen {

    private Background background;

    private Music music;
    private Sound sound;

    public HomeScreen(final MobileGame game) {
        super(game);

        music = new Music(Gdx.files.internal("music/mainmenu.mp3"), game.music, game.resources);
        sound = new Sound(Gdx.files.internal("sound/button_click.wav"), game.options, this);

        Background sky = new BackgroundLayer(new SingleTexture(Gdx.files.internal("background/Ocean_0.png"), this), new Vector2(1f, 0f), TileMode.REPEAT_X);
        Background land = new BackgroundLayer(new SingleTexture(Gdx.files.internal("background/Ocean_1.png"), this), new Vector2(5f, 0f), TileMode.REPEAT_X);
        Background water = new BackgroundLayer(new SingleTexture(Gdx.files.internal("background/Ocean_2.png"), this), new Vector2(10f, 0f), TileMode.REPEAT_X);
        background = new BackgroundGroup(sky, land, water);

        Atlas gui = new Atlas(GameAtlas.GUI, this);

        Texture small_pressed = new RegionTexture(gui.findRegion("buttons/small_pressed"), this);
        Texture large_pressed = new RegionTexture(gui.findRegion("buttons/large_pressed"), this);
        Button back = new PressableButton(new RegionTexture(gui.findRegion("buttons/exit"), this), small_pressed, new ScreenAnchor(1, 1), Origin.TOP_RIGHT) {
            @Override
            public void click() {
                sound.play();
                transitionMenu(menus.get("main"), 0.2f, 0.3f);
            }
        };

        // TODO: Create and move play menu to separate class
        Menu playMenu = new Menu(this);

        // TODO: Move credits menu to separate class
        Menu creditsMenu = new Menu(this) {

        };
        creditsMenu.addButtons(back);

        // TODO: Move achievements menu to separate class
        Menu achievementsMenu = new Menu(this);
        achievementsMenu.addButtons(back);

        // TODO: Move main menu to separate class
        Menu mainMenu = new Menu(this);
        Button settingsButton = new PressableButton(new RegionTexture(gui.findRegion("buttons/gear"), this), small_pressed, new ScreenAnchor(0, 0), Origin.BOTTOM_LEFT, new StaticOffset(1, 1)) {
            @Override
            public void click() {
                sound.play();
                transitionMenu(menus.get("settings"), 0.2f, 0.3f);
            }
        };
        Button achievementsButton = new PressableButton(new RegionTexture(gui.findRegion("buttons/trophy"), this), small_pressed, settingsButton, Origin.BOTTOM_LEFT, new StaticOffset(settingsButton.getWidth(), 0)) {
            @Override
            public void click() {
                sound.play();
                transitionMenu(menus.get("achievements"), 0.2f, 0.3f);
            }
        };
        Button twitterButton = new Button(new RegionTexture(gui.findRegion("buttons/twitter"), this), new ScreenAnchor(1, 0), Origin.BOTTOM_RIGHT, new StaticOffset(-1, 1));
        Button facebookButton = new Button(new RegionTexture(gui.findRegion("buttons/facebook"), this), twitterButton, Origin.BOTTOM_RIGHT, new StaticOffset(-twitterButton.getWidth(), 0));
        Button creditsButton = new PressableButton(new RegionTexture(gui.findRegion("buttons/credits"), this), large_pressed, new ScreenAnchor(0.5f, 0), Origin.BOTTOM, new StaticOffset(0, 1)) {
            @Override
            public void click() {
                sound.play();
                transitionMenu(menus.get("credits"), 0.2f, 0.3f);
            }
        };
        Button playButton = new PressableButton(new RegionTexture(gui.findRegion("buttons/play"), this), large_pressed, creditsButton, Origin.BOTTOM, new StaticOffset(0, creditsButton.getHeight())) {
            @Override
            public void click() {
                sound.play();
                MapLoader loader = new MapLoader(Gdx.files.internal("maps/1/map.txt")); // TODO: Use map loader elsewhere
                game.setScreen(new GameScreen(game, loader)); // TODO: Open play menu instead
            }
        };
        mainMenu.addButtons(settingsButton, achievementsButton, twitterButton, facebookButton, creditsButton, playButton);

        addMenu("main", mainMenu);
        addMenu("play", playMenu);
        addMenu("credits", creditsMenu);
        addMenu("settings", new SettingsMenu(game, this, mainMenu));
        addMenu("achievements", achievementsMenu);

        mainMenu.setTransition(new MenuInTransition(mainMenu, 0.3f));
        setActiveMenu(mainMenu);

        updateCamera();

        game.music.setPlaying(music);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.apply(game.batch);
        game.batch.begin();

        background.draw(game.batch, camera.position.x, camera.position.y, camera.viewportWidth, camera.viewportHeight);

        game.batch.end();

        super.draw(game.batch, delta);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

        for (Menu menu : menus.values()) {
            menu.setScale(width / 160f);
        }
        background.setScale(height / 128f);
    }

    @Override
    public void pause() {
        music.pause();
    }

    @Override
    public void resume() {
        music.play();
    }

    @Override
    public void hide() {
    }

}
