package ninja.amp.mobilegame.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
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
import ninja.amp.mobilegame.engine.gui.buttons.HoverableButton;
import ninja.amp.mobilegame.engine.gui.buttons.PressableButton;
import ninja.amp.mobilegame.engine.gui.input.MultiProcessor;
import ninja.amp.mobilegame.engine.gui.menus.Menu;
import ninja.amp.mobilegame.engine.physics.vectors.LVector2;
import ninja.amp.mobilegame.engine.physics.vectors.limits.Limit;
import ninja.amp.mobilegame.engine.transitions.MenuInTransition;
import ninja.amp.mobilegame.map.World;
import ninja.amp.mobilegame.objects.characters.Character;
import ninja.amp.mobilegame.objects.characters.movement.input.ButtonHoverInput;
import ninja.amp.mobilegame.objects.characters.movement.input.ButtonPressInput;
import ninja.amp.mobilegame.objects.characters.movement.input.Input;
import ninja.amp.mobilegame.objects.characters.movement.input.RisingInput;
import ninja.amp.mobilegame.objects.characters.movement.input.XorInput;
import ninja.amp.mobilegame.screens.Screen;
import ninja.amp.mobilegame.screens.home.HomeScreen;
import ninja.amp.mobilegame.screens.home.SettingsMenu;

public class GameScreen extends Screen {

    private Button playButton;
    private boolean paused = false;

    private World world;
    private Character character;

    private Background background;

    private FadeShader fade = new FadeShader(this);

    private FPSLogger log;

    public GameScreen(final MobileGame game) {
        super(game);

        log = new FPSLogger();

        Background sky = new BackgroundLayer(new SingleTexture(Gdx.files.internal("background/Ocean_0.png"), this), new Vector2(0.1f, 0f), TileMode.REPEAT_X);
        Background land = new BackgroundLayer(new SingleTexture(Gdx.files.internal("background/Ocean_1.png"), this), new Vector2(0.5f, 0.5f), new Vector2(0, -12f), TileMode.REPEAT_X);
        //Background water = new BackgroundLayer(new SingleTexture(Gdx.files.internal("background/Ocean_2.png"), this), new Vector2(1f, 0.25f), TileMode.REPEAT_X);
        background = new BackgroundGroup(sky, land);

        world = new World(game, this);

        Atlas gui = new Atlas(GameAtlas.GUI, this);

        // TODO: Move control menu to separate class
        Menu controlMenu = new Menu(this);
        final Button leftButton = new HoverableButton(new RegionTexture(gui.findRegion("controls/left"), this), new RegionTexture(gui.findRegion("controls/left_pressed"), this), new ScreenAnchor(0, 0), Origin.BOTTOM_LEFT, new StaticOffset(1, 1));
        final Button rightButton = new HoverableButton(new RegionTexture(gui.findRegion("controls/right"), this), new RegionTexture(gui.findRegion("controls/right_pressed"), this), leftButton, Origin.BOTTOM_LEFT, new StaticOffset(leftButton.getWidth(), 0));
        final Button upButton = new PressableButton(new RegionTexture(gui.findRegion("controls/up"), this), new RegionTexture(gui.findRegion("controls/up_pressed"), this), new ScreenAnchor(1, 0), Origin.BOTTOM_RIGHT, new StaticOffset(-1, 1));
        final Button controlButton = new PressableButton(new RegionTexture(gui.findRegion("controls/control"), this), new RegionTexture(gui.findRegion("controls/control_pressed"), this), upButton, Origin.BOTTOM_RIGHT, new StaticOffset(-upButton.getWidth(), 0));
        Button pauseButton = new Button(new RegionTexture(gui.findRegion("pause"), this), new ScreenAnchor(1, 1), Origin.TOP_RIGHT, new StaticOffset(-1, -1)) {
            @Override
            public void click() {
                paused = true;
                setActiveMenu(menus.get("pause"));
            }
        };
        controlMenu.addButtons(leftButton, rightButton, upButton, controlButton, pauseButton);

        // TODO: Move pause menu to separate class
        Menu pauseMenu = new Menu(this);
        playButton = new Button(new RegionTexture(gui.findRegion("play"), this), new ScreenAnchor(1, 1), Origin.TOP_RIGHT, new StaticOffset(-1, -1)) {
            @Override
            public void click() {
                paused = false;
                setActiveMenu(menus.get("control"));
            }
        };
        Texture larger_pressed = new RegionTexture(gui.findRegion("buttons/larger_pressed"), this);
        Button settingsButton = new PressableButton(new RegionTexture(gui.findRegion("buttons/settings"), this), larger_pressed, new ScreenAnchor(0.5f, 0.5f), Origin.CENTER) {
            @Override
            public void click() {
                Menu settings = getMenu("settings");
                settings.setTransition(new MenuInTransition(settings, 0.3f));
                setActiveMenu(settings);
            }
        };
        Button resumeButton = new PressableButton(new RegionTexture(gui.findRegion("buttons/resume"), this), larger_pressed, settingsButton, Origin.CENTER, new StaticOffset(0, settingsButton.getHeight())) {
            @Override
            public void click() {
                paused = false;
                setActiveMenu(menus.get("control"));
            }
        };
        Button quitButton = new PressableButton(new RegionTexture(gui.findRegion("buttons/quit"), this), larger_pressed, settingsButton, Origin.CENTER, new StaticOffset(0, -settingsButton.getHeight())) {
            @Override
            public void click() {
                game.setScreen(new HomeScreen(game));
            }
        };
        pauseMenu.addButtons(playButton, settingsButton, resumeButton, quitButton);

        addMenu("control", controlMenu);
        addMenu("pause", pauseMenu);
        addMenu("settings", new SettingsMenu(this, pauseMenu));
        addMenu("character", new CharacterMenu(this)); // TEMP
        setActiveMenu(controlMenu);

        Input left = new ButtonHoverInput(leftButton);
        Input right = new ButtonHoverInput(rightButton);
        character = new Character(
                this,
                world,
                new LVector2(2, 29, Limit.VEC2),
                new XorInput(left, right),
                new XorInput(right, left),
                new RisingInput(new ButtonPressInput(upButton)),
                new ButtonPressInput(controlButton)
        );
        world.setCharacter(character);
    }

    private boolean isPaused() { // TODO: Improve this... game states?
        return paused || hasPopup();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(new MultiProcessor(activeMenu.getProcessor(), world.getCharacterProcessor(this)));
    }

    @Override
    public void render(float delta) {
        log.log();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.apply(game.batch);
        game.batch.begin();
        background.draw(game.batch, world.getbackgroundx(), world.getbackgroundy(), camera.viewportWidth, camera.viewportHeight);
        game.batch.end();

        if (!isPaused()) {
            world.update(delta);
            world.render(delta);
        } else {
            world.render(0);
        }

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

        world.resize(width, height);

        // TODO: Improve this
        menus.get("control").setScale(width / 256f);
        menus.get("pause").setScale(width / 160f);
        menus.get("settings").setScale(width / 160f);
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
