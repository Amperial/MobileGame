package ninja.amp.mobilegame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import ninja.amp.mobilegame.MobileGame;
import ninja.amp.mobilegame.engine.background.Background;
import ninja.amp.mobilegame.engine.background.BackgroundGroup;
import ninja.amp.mobilegame.engine.background.BackgroundLayer;
import ninja.amp.mobilegame.engine.background.TileMode;
import ninja.amp.mobilegame.engine.gui.buttons.Button;
import ninja.amp.mobilegame.engine.gui.Menu;
import ninja.amp.mobilegame.engine.gui.ScreenAnchor;
import ninja.amp.mobilegame.engine.gui.Origin;
import ninja.amp.mobilegame.engine.gui.buttons.PressableButton;
import ninja.amp.mobilegame.engine.resources.audio.Music;
import ninja.amp.mobilegame.engine.resources.texture.Atlas;
import ninja.amp.mobilegame.engine.resources.texture.RegionTexture;
import ninja.amp.mobilegame.engine.resources.texture.SingleTexture;
import ninja.amp.mobilegame.engine.resources.texture.Texture;

public class MainMenuScreen extends Screen {
    
    private Texture background0 = new SingleTexture(Gdx.files.internal("background/Ocean_0.png"), this);
    private Texture background1 = new SingleTexture(Gdx.files.internal("background/Ocean_1.png"), this);
    private Texture background2 = new SingleTexture(Gdx.files.internal("background/Ocean_2.png"), this);

    private Background background;

    private Texture play, credits, gear, trophy, facebook, twitter, small_pressed, large_pressed;

    private Music music = new Music(Gdx.files.internal("music/mainmenu.mp3"), this);

    public MainMenuScreen(final MobileGame game) {
        super(game);

        Background sky = new BackgroundLayer(background0, new Vector2(1f, 0f), TileMode.REPEAT_X);
        Background land = new BackgroundLayer(background1, new Vector2(5f, 0f),TileMode.REPEAT_X);
        Background water = new BackgroundLayer(background2, new Vector2(10f, 0f), TileMode.REPEAT_X);
        background = new BackgroundGroup(sky, land, water);

        Atlas gui = new Atlas(Gdx.files.internal("gui.pack"), this);
        play = new RegionTexture(gui.findRegion("buttons/play"), this);
        credits = new RegionTexture(gui.findRegion("buttons/credits"), this);
        gear = new RegionTexture(gui.findRegion("buttons/gear"), this);
        trophy = new RegionTexture(gui.findRegion("buttons/trophy"), this);
        facebook = new RegionTexture(gui.findRegion("buttons/facebook"), this);
        twitter = new RegionTexture(gui.findRegion("buttons/twitter"), this);
        small_pressed = new RegionTexture(gui.findRegion("buttons/small_pressed"), this);
        large_pressed = new RegionTexture(gui.findRegion("buttons/large_pressed"), this);
        
        Menu playMenu = new Menu();
        Menu creditsMenu = new Menu();
        Menu settingsMenu = new Menu();
        Menu achievementsMenu = new Menu();

        Button settingsButton = new PressableButton(gear, small_pressed, new ScreenAnchor(0, 0), Origin.BOTTOM_LEFT, new Vector2(1, 1)) {
            @Override
            public void click() {
                setActiveMenu(menus.get("settings"));
            }
        };
        Button achievementsButton = new PressableButton(trophy, small_pressed, settingsButton, Origin.BOTTOM_LEFT, new Vector2(gear.getRegion().getRegionWidth(), 0)) {
            @Override
            public void click() {
                setActiveMenu(menus.get("achievements"));
            }
        };
        Button twitterButton = new Button(twitter, new ScreenAnchor(1, 0), Origin.BOTTOM_RIGHT, new Vector2(-1, 1));
        Button facebookButton = new Button(facebook, twitterButton, Origin.BOTTOM_RIGHT, new Vector2(-twitter.getRegion().getRegionWidth(), 0));
        Button creditsButton = new PressableButton(credits, large_pressed, new ScreenAnchor(0.5f, 0), Origin.BOTTOM, new Vector2(0, 1)) {
            @Override
            public void click() {
                setActiveMenu(menus.get("credits"));
            }
        };
        Button playButton = new PressableButton(play, large_pressed, creditsButton, Origin.BOTTOM, new Vector2(0, credits.getRegion().getRegionHeight())) {
            @Override
            public void click() {
                game.setScreen(new GameScreen(game));
            }
        };
        Menu mainMenu = new Menu(settingsButton, achievementsButton, twitterButton, facebookButton, creditsButton, playButton);

        addMenu("main", mainMenu);
        addMenu("play", playMenu);
        addMenu("credits", creditsMenu);
        addMenu("settings", settingsMenu);
        addMenu("achievements", achievementsMenu);

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

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        
        background.draw(game.batch, camera.position.x, camera.position.y, camera.viewportWidth, camera.viewportHeight);
        
        activeMenu.draw(game.batch);
        
        game.batch.end();
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
