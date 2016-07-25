package ninja.amp.mobilegame.screens.home;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import ninja.amp.mobilegame.MobileGame;
import ninja.amp.mobilegame.engine.background.Background;
import ninja.amp.mobilegame.engine.background.BackgroundGroup;
import ninja.amp.mobilegame.engine.background.BackgroundLayer;
import ninja.amp.mobilegame.engine.background.TileMode;
import ninja.amp.mobilegame.engine.gui.buttons.Button;
import ninja.amp.mobilegame.engine.gui.menus.Menu;
import ninja.amp.mobilegame.engine.gui.ScreenAnchor;
import ninja.amp.mobilegame.engine.gui.Origin;
import ninja.amp.mobilegame.engine.gui.buttons.PressableButton;
import ninja.amp.mobilegame.engine.resources.audio.Music;
import ninja.amp.mobilegame.engine.resources.audio.Sound;
import ninja.amp.mobilegame.engine.resources.texture.Atlas;
import ninja.amp.mobilegame.engine.resources.texture.RegionTexture;
import ninja.amp.mobilegame.engine.resources.texture.SingleTexture;
import ninja.amp.mobilegame.engine.resources.texture.Texture;
import ninja.amp.mobilegame.screens.Screen;
import ninja.amp.mobilegame.screens.game.GameScreen;

public class HomeScreen extends Screen {
    
    private Texture background0 = new SingleTexture(Gdx.files.internal("background/Ocean_0.png"), this);
    private Texture background1 = new SingleTexture(Gdx.files.internal("background/Ocean_1.png"), this);
    private Texture background2 = new SingleTexture(Gdx.files.internal("background/Ocean_2.png"), this);

    private Background background;

    private Music music = new Music(Gdx.files.internal("music/mainmenu.mp3"), this);
    private Sound sound = new Sound(Gdx.files.internal("sound/button_click.wav"), this);

    public HomeScreen(final MobileGame game) {
        super(game);

        Background sky = new BackgroundLayer(background0, new Vector2(1f, 0f), TileMode.REPEAT_X);
        Background land = new BackgroundLayer(background1, new Vector2(5f, 0f),TileMode.REPEAT_X);
        Background water = new BackgroundLayer(background2, new Vector2(10f, 0f), TileMode.REPEAT_X);
        background = new BackgroundGroup(sky, land, water);

        Atlas gui = new Atlas(Gdx.files.internal("gui.pack"), this);

        Texture small_pressed = new RegionTexture(gui.findRegion("buttons/small_pressed"), this);
        Texture large_pressed = new RegionTexture(gui.findRegion("buttons/large_pressed"), this);
        Texture exit = new RegionTexture(gui.findRegion("buttons/exit"), this);
        Button back = new PressableButton(exit, small_pressed, new ScreenAnchor(1, 1), Origin.TOP_RIGHT) {
            @Override
            public void click() {
                sound.play();
                setActiveMenu(menus.get("main"));
            }
        };

        // TODO: Create and move play menu to separate class
        Menu playMenu = new Menu();

        // TODO: Move credits menu to separate class
        Menu creditsMenu = new Menu();
        creditsMenu.addButtons(back);

        // TODO: Move settings menu to separate class
        Menu settingsMenu = new Menu();
        settingsMenu.addButtons(back);

        // TODO: Move achievements menu to separate class
        Menu achievementsMenu = new Menu();
        achievementsMenu.addButtons(back);

        // TODO: Move main menu to separate class
        Menu mainMenu = new Menu();
        Button settingsButton = new PressableButton(new RegionTexture(gui.findRegion("buttons/gear"), this), small_pressed, new ScreenAnchor(0, 0), Origin.BOTTOM_LEFT, new Vector2(1, 1)) {
            @Override
            public void click() {
                sound.play();
                setActiveMenu(menus.get("settings"));

            }
        };
        Button achievementsButton = new PressableButton(new RegionTexture(gui.findRegion("buttons/trophy"), this), small_pressed, settingsButton, Origin.BOTTOM_LEFT, new Vector2(settingsButton.getWidth(), 0)) {
            @Override
            public void click() {
                sound.play();
                setActiveMenu(menus.get("achievements"));
            }
        };
        Button twitterButton = new Button(new RegionTexture(gui.findRegion("buttons/twitter"), this), new ScreenAnchor(1, 0), Origin.BOTTOM_RIGHT, new Vector2(-1, 1));
        Button facebookButton = new Button(new RegionTexture(gui.findRegion("buttons/facebook"), this), twitterButton, Origin.BOTTOM_RIGHT, new Vector2(-twitterButton.getWidth(), 0));
        Button creditsButton = new PressableButton(new RegionTexture(gui.findRegion("buttons/credits"), this), large_pressed, new ScreenAnchor(0.5f, 0), Origin.BOTTOM, new Vector2(0, 1)) {
            @Override
            public void click() {
                sound.play();
                setActiveMenu(menus.get("credits"));
            }
        };
        Button playButton = new PressableButton(new RegionTexture(gui.findRegion("buttons/play"), this), large_pressed, creditsButton, Origin.BOTTOM, new Vector2(0, creditsButton.getHeight())) {
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
        addMenu("settings", settingsMenu);
        addMenu("achievements", achievementsMenu);

        setActiveMenu(mainMenu);

        updateCamera();

        music.setVolume(0.1f);
        //music.play();
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        
        background.draw(game.batch, camera.position.x, camera.position.y, camera.viewportWidth, camera.viewportHeight);

        super.draw(game.batch);
        
        game.batch.end();
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
        //music.pause();
    }

    @Override
    public void resume() {
        //music.play();
    }

    @Override
    public void hide() {
    }

}
