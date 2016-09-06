package ninja.amp.mobilegame.screens.home;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import ninja.amp.mobilegame.MobileGame;
import ninja.amp.mobilegame.engine.background.Background;
import ninja.amp.mobilegame.engine.background.BackgroundGroup;
import ninja.amp.mobilegame.engine.background.BackgroundLayer;
import ninja.amp.mobilegame.engine.background.TileMode;
import ninja.amp.mobilegame.engine.graphics.atlas.Atlas;
import ninja.amp.mobilegame.engine.graphics.RegionTexture;
import ninja.amp.mobilegame.engine.graphics.SingleTexture;
import ninja.amp.mobilegame.engine.graphics.Texture;
import ninja.amp.mobilegame.engine.graphics.atlas.GameAtlas;
import ninja.amp.mobilegame.engine.graphics.shaders.FadeShader;
import ninja.amp.mobilegame.engine.gui.Origin;
import ninja.amp.mobilegame.engine.gui.ScreenAnchor;
import ninja.amp.mobilegame.engine.gui.StaticOffset;
import ninja.amp.mobilegame.engine.gui.buttons.Button;
import ninja.amp.mobilegame.engine.gui.buttons.PressableButton;
import ninja.amp.mobilegame.engine.gui.menus.Menu;
import ninja.amp.mobilegame.engine.resources.audio.Music;
import ninja.amp.mobilegame.engine.resources.audio.Sound;
import ninja.amp.mobilegame.engine.transitions.MenuInTransition;
import ninja.amp.mobilegame.screens.Screen;
import ninja.amp.mobilegame.screens.game.GameScreen;

public class HomeScreen extends Screen {

    private Background background;

    private Music music = new Music(Gdx.files.internal("music/mainmenu.mp3"), this);
    private Sound sound = new Sound(Gdx.files.internal("sound/button_click.wav"), this);

    private FadeShader fade = new FadeShader(this);

    public HomeScreen(final MobileGame game) {
        super(game);

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
                menus.get("main").setTransition(new MenuInTransition(menus.get("main"), 0.3f));
                setActiveMenu(menus.get("main"));
            }
        };

        // TODO: Create and move play menu to separate class
        Menu playMenu = new Menu(this);

        // TODO: Move credits menu to separate class
        Menu creditsMenu = new Menu(this) {

        };
        creditsMenu.addButtons(back);

        // TODO: Move settings menu to separate class

        // TODO: Move achievements menu to separate class
        Menu achievementsMenu = new Menu(this);
        achievementsMenu.addButtons(back);

        // TODO: Move main menu to separate class
        Menu mainMenu = new Menu(this);
        Button settingsButton = new PressableButton(new RegionTexture(gui.findRegion("buttons/gear"), this), small_pressed, new ScreenAnchor(0, 0), Origin.BOTTOM_LEFT, new StaticOffset(1, 1)) {
            @Override
            public void click() {
                sound.play();
                menus.get("settings").setTransition(new MenuInTransition(menus.get("settings"), 0.3f));
                setActiveMenu(menus.get("settings"));
            }
        };
        Button achievementsButton = new PressableButton(new RegionTexture(gui.findRegion("buttons/trophy"), this), small_pressed, settingsButton, Origin.BOTTOM_LEFT, new StaticOffset(settingsButton.getWidth(), 0)) {
            @Override
            public void click() {
                sound.play();
                menus.get("achievements").setTransition(new MenuInTransition(menus.get("achievements"), 0.3f));
                setActiveMenu(menus.get("achievements"));
            }
        };
        Button twitterButton = new Button(new RegionTexture(gui.findRegion("buttons/twitter"), this), new ScreenAnchor(1, 0), Origin.BOTTOM_RIGHT, new StaticOffset(-1, 1));
        Button facebookButton = new Button(new RegionTexture(gui.findRegion("buttons/facebook"), this), twitterButton, Origin.BOTTOM_RIGHT, new StaticOffset(-twitterButton.getWidth(), 0));
        Button creditsButton = new PressableButton(new RegionTexture(gui.findRegion("buttons/credits"), this), large_pressed, new ScreenAnchor(0.5f, 0), Origin.BOTTOM, new StaticOffset(0, 1)) {
            @Override
            public void click() {
                sound.play();
                menus.get("credits").setTransition(new MenuInTransition(menus.get("credits"), 0.3f));
                setActiveMenu(menus.get("credits"));
            }
        };
        Button playButton = new PressableButton(new RegionTexture(gui.findRegion("buttons/play"), this), large_pressed, creditsButton, Origin.BOTTOM, new StaticOffset(0, creditsButton.getHeight())) {
            @Override
            public void click() {
                sound.play();
                game.setScreen(new GameScreen(game)); // TODO: Open play menu instead
            }
        };
        mainMenu.addButtons(settingsButton, achievementsButton, twitterButton, facebookButton, creditsButton, playButton);

        addMenu("main", mainMenu);
        addMenu("play", playMenu);
        addMenu("credits", creditsMenu);
        addMenu("settings", new SettingsMenu(this, mainMenu));
        addMenu("achievements", achievementsMenu);

        mainMenu.setTransition(new MenuInTransition(mainMenu, 0.3f));

        setActiveMenu(mainMenu);

        updateCamera();

        music.setVolume(0.1f);
        music.play();
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

        if (activeMenu.hasTransition() && activeMenu.getTransition() instanceof MenuInTransition) {
            fade.setFade(activeMenu.getTransition().current() / 0.3f);
            fade.apply(game.batch);
            super.draw(game.batch, delta);
            game.batch.setShader(null);
        } else {
            super.draw(game.batch, delta);
        }
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

        // TODO: Improve this
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
