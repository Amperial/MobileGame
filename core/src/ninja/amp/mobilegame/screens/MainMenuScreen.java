package ninja.amp.mobilegame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Vector2;
import ninja.amp.mobilegame.MobileGame;
import ninja.amp.mobilegame.background.Background;
import ninja.amp.mobilegame.background.BackgroundGroup;
import ninja.amp.mobilegame.background.BackgroundLayer;
import ninja.amp.mobilegame.background.TileMode;
import ninja.amp.mobilegame.menus.Button;
import ninja.amp.mobilegame.menus.Menu;
import ninja.amp.mobilegame.menus.ScreenAnchor;
import ninja.amp.mobilegame.menus.Origin;

import java.util.HashMap;

public class MainMenuScreen implements Screen {

    private MobileGame game;
    private OrthographicCamera camera;

    private Background background;

    private Menu activeMenu;
    private HashMap<String, Menu> menus = new HashMap<String, Menu>();
    
    private Texture background0 = new Texture(Gdx.files.internal("background/Ocean_0.png"));
    private Texture background1 = new Texture(Gdx.files.internal("background/Ocean_1.png"));
    private Texture background2 = new Texture(Gdx.files.internal("background/Ocean_2.png"));
    private Texture play = new Texture(Gdx.files.internal("gui/buttons/play.png"));
    private Texture credits = new Texture(Gdx.files.internal("gui/buttons/credits.png"));
    private Texture gear = new Texture(Gdx.files.internal("gui/buttons/gear.png"));
    private Texture trophy = new Texture(Gdx.files.internal("gui/buttons/trophy.png"));
    private Texture facebook = new Texture(Gdx.files.internal("gui/buttons/facebook.png"));
    private Texture twitter = new Texture(Gdx.files.internal("gui/buttons/twitter.png"));

    float x = 0;
    float y = 0;
    FPSLogger log;

    public MainMenuScreen(MobileGame game) {
        this.game = game;
        
        // TODO: Replace textures with texture atlas
        
        Background sky = new BackgroundLayer(new TextureRegion(background0), new Vector2(1f, 0f), TileMode.REPEAT_X);
        Background land = new BackgroundLayer(new TextureRegion(background1), new Vector2(5f, 0f),TileMode.REPEAT_X);
        Background water = new BackgroundLayer(new TextureRegion(background2), new Vector2(10f, 0f), TileMode.REPEAT_X);
        
        background = new BackgroundGroup(sky, land, water);
        
        Menu playMenu = new Menu();
        Menu creditsMenu = new Menu();
        Menu settingsMenu = new Menu();
        Menu achievementsMenu = new Menu();
        
        Button settingsButton = new Button(new TextureRegion(gear), new ScreenAnchor(0, 0), Origin.BOTTOM_LEFT, new Vector2(1, 1)) {
            @Override
            public void click() {
                //setActiveMenu(menus.get("settings"));
            }
        };
        Button achievementsButton = new Button(new TextureRegion(trophy), settingsButton, Origin.BOTTOM_LEFT, new Vector2(gear.getWidth(), 0)) {
            @Override
            public void click() {
                //setActiveMenu(menus.get("achievements"));
            }
        };
        Button twitterButton = new Button(new TextureRegion(twitter), new ScreenAnchor(1, 0), Origin.BOTTOM_RIGHT, new Vector2(-1, 1));
        Button facebookButton = new Button(new TextureRegion(facebook), twitterButton, Origin.BOTTOM_RIGHT, new Vector2(-twitter.getWidth(), 0));
        Button creditsButton = new Button(new TextureRegion(credits), new ScreenAnchor(0.5f, 0), Origin.BOTTOM, new Vector2(0, 1)) {
            @Override
            public void click() {
                //setActiveMenu(menus.get("credits"));
            }
        };
        Button playButton = new Button(new TextureRegion(play), creditsButton, Origin.BOTTOM, new Vector2(0, credits.getHeight())) {
            @Override
            public void click() {
                //setActiveMenu(menus.get("play"));
            }
        };
        Menu mainMenu = new Menu(settingsButton, achievementsButton, twitterButton, facebookButton, creditsButton, playButton);
        
        menus.put("play", playMenu);
        menus.put("credits", creditsMenu);
        menus.put("settings", settingsMenu);
        menus.put("achievements", achievementsMenu);
        menus.put("main", mainMenu);
        
        setActiveMenu(mainMenu);
        
        camera = new OrthographicCamera();
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        
        log = new FPSLogger();
    }

    public void setActiveMenu(Menu menu) {
        activeMenu = menu;
        Gdx.input.setInputProcessor(menu);
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        log.log();
        x += delta * 10;
        
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        
        background.draw(game.batch, x, y, camera.viewportWidth, camera.viewportHeight);
        
        activeMenu.draw(game.batch);
        
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);

        for (Menu menu : menus.values()) {
            menu.setScale(width / 160f);
        }
        background.setScale(height / 128f);
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
    }

    @Override
    public void dispose() {
        background0.dispose();
        background1.dispose();
        background2.dispose();
        play.dispose();
        credits.dispose();
        gear.dispose();
        trophy.dispose();
        facebook.dispose();
        twitter.dispose();
    }

}
