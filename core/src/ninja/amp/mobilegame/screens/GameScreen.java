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
import ninja.amp.mobilegame.gui.buttons.HoverableButton;
import ninja.amp.mobilegame.gui.buttons.PressableButton;
import ninja.amp.mobilegame.map.World;
import ninja.amp.mobilegame.gui.buttons.Button;
import ninja.amp.mobilegame.gui.Menu;
import ninja.amp.mobilegame.gui.Origin;
import ninja.amp.mobilegame.gui.ScreenAnchor;
import ninja.amp.mobilegame.physics.forces.Force;
import ninja.amp.mobilegame.physics.forces.Impulse;
import ninja.amp.mobilegame.physics.forces.SimpleForce;

import java.util.HashMap;

public class GameScreen implements Screen {

    private MobileGame game;
    private OrthographicCamera camera;

    private Background background;

    private Menu activeMenu;
    private HashMap<String, Menu> menus = new HashMap<String, Menu>();
    
    private Texture background0 = new Texture(Gdx.files.internal("background/Ocean_0.png"));
    private Texture background1 = new Texture(Gdx.files.internal("background/Ocean_1.png"));
    private Texture background2 = new Texture(Gdx.files.internal("background/Ocean_2.png"));
    private Texture left = new Texture(Gdx.files.internal("gui/controls/left.png"));
    private Texture left_pressed = new Texture(Gdx.files.internal("gui/controls/left_pressed.png"));
    private Texture right = new Texture(Gdx.files.internal("gui/controls/right.png"));
    private Texture right_pressed = new Texture(Gdx.files.internal("gui/controls/right_pressed.png"));
    private Texture up = new Texture(Gdx.files.internal("gui/controls/up.png"));
    private Texture up_pressed = new Texture(Gdx.files.internal("gui/controls/up_pressed.png"));
    private Texture control = new Texture(Gdx.files.internal("gui/controls/control.png"));
    private Texture control_pressed = new Texture(Gdx.files.internal("gui/controls/control_pressed.png"));
    private Texture pause = new Texture(Gdx.files.internal("gui/pause.png"));
    private Texture play = new Texture(Gdx.files.internal("gui/play.png"));
    
    private World world;

    private boolean paused = false;

    FPSLogger log;

    public GameScreen(MobileGame game) {
        this.game = game;
        
        // TODO: Replace textures with texture atlas
        
        Background sky = new BackgroundLayer(new TextureRegion(background0), new Vector2(1f, 0f), TileMode.REPEAT_X);
        Background land = new BackgroundLayer(new TextureRegion(background1), new Vector2(5f, 0f),TileMode.REPEAT_X);
        final Background water = new BackgroundLayer(new TextureRegion(background2), new Vector2(10f, 0f), TileMode.REPEAT_X);
        background = new BackgroundGroup(sky, land, water);
        
        Button leftButton = new HoverableButton(new TextureRegion(left), new TextureRegion(left_pressed), new ScreenAnchor(0, 0), Origin.BOTTOM_LEFT, new Vector2(1, 1)) {
            Force left = new SimpleForce(new Vector2(-1000, 0), false);
            @Override
            public void setHovered(int pointer) {
                super.setHovered(pointer);
                if (isHovered()) {
                    world.addForce("left", left);
                } else {
                    world.removeForce("left");
                }
            }
        };
        Button rightButton = new HoverableButton(new TextureRegion(right), new TextureRegion(right_pressed), leftButton, Origin.BOTTOM_LEFT, new Vector2(left.getWidth(), 0)) {
            Force right = new SimpleForce(new Vector2(1000, 0), false);
            @Override
            public void setHovered(int pointer) {
                super.setHovered(pointer);
                if (isHovered()) {
                    world.addForce("right", right);
                } else {
                    world.removeForce("right");
                }
            }
        };
        Button upButton = new PressableButton(new TextureRegion(up), new TextureRegion(up_pressed), new ScreenAnchor(1, 0), Origin.BOTTOM_RIGHT, new Vector2(-1, 1)) {
            Force up = new Impulse(new Vector2(0, 1000000));
            @Override
            public void setPressed(int pressed) {
                super.setPressed(pressed);
                if (isPressed() && world.getCharacter().getVelocity().y < 1000) {
                    world.getCharacter().getVelocity().y = 1000;
                }
            }
        };
        Button controlButton = new PressableButton(new TextureRegion(control), new TextureRegion(control_pressed), upButton, Origin.BOTTOM_RIGHT, new Vector2(-up.getWidth(), 0));
        Button pauseButton = new Button(new TextureRegion(pause), new ScreenAnchor(1, 1), Origin.TOP_RIGHT, new Vector2(-1, -1)) {
            @Override
            public void click() {
                paused = true;
                setActiveMenu(menus.get("pause"));
            }
        };
        Menu controlMenu = new Menu(leftButton, rightButton, upButton, controlButton, pauseButton);
        
        Button playButton = new Button(new TextureRegion(play), new ScreenAnchor(1, 1), Origin.TOP_RIGHT, new Vector2(-1, -1)) {
            @Override
            public void click() {
                paused = false;
                setActiveMenu(menus.get("control"));
            }
        };
        Menu pauseMenu = new Menu(playButton);
        
        menus.put("control", controlMenu);
        menus.put("pause", pauseMenu);
        
        setActiveMenu(controlMenu);

        camera = new OrthographicCamera();
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        
        world = new World(game);
        
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
        //log.log();
        
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.setProjectionMatrix(camera.combined);
        game.batch.begin();
        
        if (!paused) {
            world.update(delta);
        }

        background.draw(game.batch, -world.getCharacter().getPosition().x, world.getCharacter().getPosition().y, camera.viewportWidth, camera.viewportHeight);
        
        world.render(delta);

        activeMenu.draw(game.batch);
        
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);

        for (Menu menu : menus.values()) {
            menu.setScale(width / 256f);
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
        left.dispose();
        left_pressed.dispose();
        right.dispose();
        right_pressed.dispose();
        up.dispose();
        up_pressed.dispose();
        control.dispose();
        pause.dispose();
    }

}
