package ninja.amp.mobilegame.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Vector2;
import ninja.amp.mobilegame.MobileGame;
import ninja.amp.mobilegame.engine.background.Background;
import ninja.amp.mobilegame.engine.background.BackgroundGroup;
import ninja.amp.mobilegame.engine.background.BackgroundLayer;
import ninja.amp.mobilegame.engine.background.TileMode;
import ninja.amp.mobilegame.engine.gui.buttons.HoverableButton;
import ninja.amp.mobilegame.engine.gui.buttons.PressableButton;
import ninja.amp.mobilegame.engine.resources.texture.*;
import ninja.amp.mobilegame.map.World;
import ninja.amp.mobilegame.engine.gui.buttons.Button;
import ninja.amp.mobilegame.engine.gui.menus.Menu;
import ninja.amp.mobilegame.engine.gui.Origin;
import ninja.amp.mobilegame.engine.gui.ScreenAnchor;
import ninja.amp.mobilegame.screens.Screen;
import ninja.amp.mobilegame.screens.home.HomeScreen;

public class GameScreen extends Screen {
    
    private Texture background0 = new SingleTexture(Gdx.files.internal("background/Ocean_0.png"), this);
    private Texture background1 = new SingleTexture(Gdx.files.internal("background/Ocean_1.png"), this);
    private Texture background2 = new SingleTexture(Gdx.files.internal("background/Ocean_2.png"), this);

    private Background background;

    private Button playButton;
    private boolean paused = false;
    
    private World world;

    public GameScreen(final MobileGame game) {
        super(game);

        Background sky = new BackgroundLayer(background0, new Vector2(1f, 0f), TileMode.REPEAT_X);
        Background land = new BackgroundLayer(background1, new Vector2(5f, 2f),TileMode.REPEAT_X);
        Background water = new BackgroundLayer(background2, new Vector2(10f, 1f), TileMode.REPEAT_X);
        background = new BackgroundGroup(sky, land, water);

        Atlas gui = new Atlas(Gdx.files.internal("gui.pack"), this);

        // TODO: Move control menu to separate class
        Menu controlMenu = new Menu();
        Button leftButton = new HoverableButton(new RegionTexture(gui.findRegion("controls/left"), this), new RegionTexture(gui.findRegion("controls/left_pressed"), this), new ScreenAnchor(0, 0), Origin.BOTTOM_LEFT, new Vector2(1, 1)) {
            @Override
            public void setHovered(int pointer) {
                super.setHovered(pointer);
                // TODO: Left
            }
        };
        Button rightButton = new HoverableButton(new RegionTexture(gui.findRegion("controls/right"), this), new RegionTexture(gui.findRegion("controls/right_pressed"), this), leftButton, Origin.BOTTOM_LEFT, new Vector2(leftButton.getWidth(), 0)) {
            @Override
            public void setHovered(int pointer) {
                super.setHovered(pointer);
                // TODO: Right
            }
        };
        Button upButton = new PressableButton(new RegionTexture(gui.findRegion("controls/up"), this), new RegionTexture(gui.findRegion("controls/up_pressed"), this), new ScreenAnchor(1, 0), Origin.BOTTOM_RIGHT, new Vector2(-1, 1)) {
            @Override
            public void setPressed(int pressed) {
                super.setPressed(pressed);
                if (isPressed()) {
                    // TODO: Jump
                }
            }
        };
        Button controlButton = new PressableButton(new RegionTexture(gui.findRegion("controls/control"), this), new RegionTexture(gui.findRegion("controls/control_pressed"), this), upButton, Origin.BOTTOM_RIGHT, new Vector2(-upButton.getWidth(), 0));
        Button pauseButton = new Button(new RegionTexture(gui.findRegion("pause"), this), new ScreenAnchor(1, 1), Origin.TOP_RIGHT, new Vector2(-1, -1)) {
            @Override
            public void click() {
                paused = true;
                setActiveMenu(menus.get("pause"));
            }
        };
        controlMenu.addButtons(leftButton, rightButton, upButton, controlButton, pauseButton);

        // TODO: Move pause menu to separate class
        Menu pauseMenu = new Menu();
        playButton = new Button(new RegionTexture(gui.findRegion("play"), this), new ScreenAnchor(1, 1), Origin.TOP_RIGHT, new Vector2(-1, -1)) {
            @Override
            public void click() {
                paused = false;
                setActiveMenu(menus.get("control"));
            }
        };
        Texture larger_pressed = new RegionTexture(gui.findRegion("buttons/larger_pressed"), this);
        Button controlsButton = new PressableButton(new RegionTexture(gui.findRegion("buttons/controls"), this), larger_pressed, new ScreenAnchor(0.5f, 0.5f), Origin.CENTER);
        Button resumeButton = new PressableButton(new RegionTexture(gui.findRegion("buttons/resume"), this), larger_pressed, controlsButton, Origin.CENTER, new Vector2(0, controlsButton.getHeight())) {
            @Override
            public void click() {
                paused = false;
                setActiveMenu(menus.get("control"));
            }
        };
        Button quitButton = new PressableButton(new RegionTexture(gui.findRegion("buttons/quit"), this), larger_pressed, controlsButton, Origin.CENTER, new Vector2(0, -controlsButton.getHeight())) {
            @Override
            public void click() {
                game.setScreen(new HomeScreen(game));
            }
        };
        pauseMenu.addButtons(playButton, controlsButton, resumeButton, quitButton);

        addMenu("control", controlMenu);
        addMenu("pause", pauseMenu);
        addMenu("character", new CharacterMenu(this)); // TEMP
        setActiveMenu(controlMenu);

        updateCamera();

        world = new World(game);

        openPopup(menus.get("character")); // TEMP
    }

    private boolean isPaused() { // TODO: Improve this... game states?
        return paused || hasPopup();
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
        
        if (!isPaused()) {
            world.update(delta);
        }

        background.draw(game.batch, -world.getCharacter().getPosition().x, -world.getCharacter().getPosition().y, camera.viewportWidth, camera.viewportHeight);
        
        world.render(delta);

        super.draw(game.batch);
        
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

        // TODO: Improve this
        menus.get("control").setScale(width / 256f);
        menus.get("pause").setScale(width / 160f);
        menus.get("character").setScale(width / 300f);
        playButton.setScale(width / 256f);

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

}
