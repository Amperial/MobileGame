package ninja.amp.mobilegame.screens;

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
import ninja.amp.mobilegame.engine.gui.Menu;
import ninja.amp.mobilegame.engine.gui.Origin;
import ninja.amp.mobilegame.engine.gui.ScreenAnchor;

public class GameScreen extends Screen {
    
    private Texture background0 = new SingleTexture(Gdx.files.internal("background/Ocean_0.png"), this);
    private Texture background1 = new SingleTexture(Gdx.files.internal("background/Ocean_1.png"), this);
    private Texture background2 = new SingleTexture(Gdx.files.internal("background/Ocean_2.png"), this);

    private Background background;

    private Texture left, left_pressed, right, right_pressed, up, up_pressed, control, control_pressed, pause, play, controls, resume, quit, larger_pressed;

    private Button playButton;
    private boolean paused = false;
    
    private World world;

    public GameScreen(final MobileGame game) {
        super(game);

        Background sky = new BackgroundLayer(background0, new Vector2(1f, 0f), TileMode.REPEAT_X);
        Background land = new BackgroundLayer(background1, new Vector2(5f, 2f),TileMode.REPEAT_X);
        Background water = new BackgroundLayer(background2, new Vector2(10f, 1f), TileMode.REPEAT_X);

        Atlas gui = new Atlas(Gdx.files.internal("gui.pack"), this);
        left = new RegionTexture(gui.findRegion("controls/left"), this);
        left_pressed = new RegionTexture(gui.findRegion("controls/left_pressed"), this);
        right = new RegionTexture(gui.findRegion("controls/right"), this);
        right_pressed = new RegionTexture(gui.findRegion("controls/right_pressed"), this);
        up = new RegionTexture(gui.findRegion("controls/up"), this);
        up_pressed = new RegionTexture(gui.findRegion("controls/up_pressed"), this);
        control = new RegionTexture(gui.findRegion("controls/control"), this);
        control_pressed = new RegionTexture(gui.findRegion("controls/control_pressed"), this);
        pause = new RegionTexture(gui.findRegion("pause"), this);
        play = new RegionTexture(gui.findRegion("play"), this);
        controls = new RegionTexture(gui.findRegion("buttons/controls"), this);
        resume = new RegionTexture(gui.findRegion("buttons/resume"), this);
        quit = new RegionTexture(gui.findRegion("buttons/quit"), this);
        larger_pressed = new RegionTexture(gui.findRegion("buttons/larger_pressed"), this);
        background = new BackgroundGroup(sky, land, water);

        Button leftButton = new HoverableButton(left, left_pressed, new ScreenAnchor(0, 0), Origin.BOTTOM_LEFT, new Vector2(1, 1)) {
            @Override
            public void setHovered(int pointer) {
                super.setHovered(pointer);
                // TODO: Left
            }
        };
        Button rightButton = new HoverableButton(right, right_pressed, leftButton, Origin.BOTTOM_LEFT, new Vector2(left.getRegion().getRegionWidth(), 0)) {
            @Override
            public void setHovered(int pointer) {
                super.setHovered(pointer);
                // TODO: Right
            }
        };
        Button upButton = new PressableButton(up, up_pressed, new ScreenAnchor(1, 0), Origin.BOTTOM_RIGHT, new Vector2(-1, 1)) {
            @Override
            public void setPressed(int pressed) {
                super.setPressed(pressed);
                if (isPressed()) {
                    // TODO: Jump
                }
            }
        };
        Button controlButton = new PressableButton(control, control_pressed, upButton, Origin.BOTTOM_RIGHT, new Vector2(-up.getRegion().getRegionWidth(), 0));
        Button pauseButton = new Button(pause, new ScreenAnchor(1, 1), Origin.TOP_RIGHT, new Vector2(-1, -1)) {
            @Override
            public void click() {
                paused = true;
                setActiveMenu(menus.get("pause"));
            }
        };
        Menu controlMenu = new Menu(leftButton, rightButton, upButton, controlButton, pauseButton);
        
        playButton = new Button(play, new ScreenAnchor(1, 1), Origin.TOP_RIGHT, new Vector2(-1, -1)) {
            @Override
            public void click() {
                paused = false;
                setActiveMenu(menus.get("control"));
            }
        };
        Button controlsButton = new PressableButton(controls, larger_pressed, new ScreenAnchor(0.5f, 0.5f), Origin.CENTER);
        Button resumeButton = new PressableButton(resume, larger_pressed, controlsButton, Origin.CENTER, new Vector2(0, controls.getRegion().getRegionHeight())) {
            @Override
            public void click() {
                paused = false;
                setActiveMenu(menus.get("control"));
            }
        };
        Button quitButton = new PressableButton(quit, larger_pressed, controlsButton, Origin.CENTER, new Vector2(0, -controls.getRegion().getRegionHeight())) {
            @Override
            public void click() {
                game.setScreen(new MainMenuScreen(game));
            }
        };
        Menu pauseMenu = new Menu(playButton, controlsButton, resumeButton, quitButton);

        addMenu("control", controlMenu);
        addMenu("pause", pauseMenu);
        setActiveMenu(controlMenu);

        updateCamera();

        world = new World(game);
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
        
        if (!paused) {
            world.update(delta);
        }

        background.draw(game.batch, -world.getCharacter().getPosition().x, -world.getCharacter().getPosition().y, camera.viewportWidth, camera.viewportHeight);
        
        world.render(delta);

        activeMenu.draw(game.batch);
        
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

        menus.get("control").setScale(width / 256f);
        menus.get("pause").setScale(width / 160f);
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
