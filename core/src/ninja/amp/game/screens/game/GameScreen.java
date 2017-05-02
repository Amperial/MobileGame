package ninja.amp.game.screens.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.FPSLogger;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import ninja.amp.engine.graphics.gui.menus.Menu;
import ninja.amp.engine.graphics.gui.screens.Screen;
import ninja.amp.engine.graphics.textures.atlas.Atlas;
import ninja.amp.engine.graphics.textures.atlas.GameAtlas;
import ninja.amp.engine.map.MapLoader;
import ninja.amp.engine.map.World;
import ninja.amp.engine.objects.entities.Entity;
import ninja.amp.engine.objects.entities.character.Character;
import ninja.amp.engine.objects.entities.npc.ai.actions.range.AreaRange;
import ninja.amp.engine.particles.ParticleSystem;
import ninja.amp.engine.physics.vectors.LVector2;
import ninja.amp.engine.physics.vectors.limits.Limit;
import ninja.amp.engine.resources.audio.Music;
import ninja.amp.game.MobileGame;
import ninja.amp.game.objects.entities.character.GameCharacter;
import ninja.amp.game.objects.entities.npc.hostile.bat.Bat;
import ninja.amp.game.persistence.CharacterStats;
import ninja.amp.game.screens.home.SettingsMenu;

public class GameScreen extends Screen {

    private boolean paused = false;

    private World world;

    private ParticleSystem particles;

    private FPSLogger log;

    public GameScreen(final MobileGame game, MapLoader mapLoader) {
        super(game);

        log = new FPSLogger();

        CharacterStats stats = new CharacterStats(this);

        world = new World(game, this, mapLoader);

        Atlas gui = new Atlas(GameAtlas.GUI, this);
        final ControlMenu controlMenu = new ControlMenu(this, gui, world);
        final Character character = new GameCharacter(
                this,
                world,
                new LVector2(2, 29, Limit.VEC2),
                controlMenu.getLeftInput(),
                controlMenu.getRightInput(),
                controlMenu.getJumpInput(),
                controlMenu.getControlInput(),
                stats.getHealth(),
                stats.getProtection(),
                stats.getStrength()
        );
        character.initialize();
        world.setCharacter(character);

        Entity bat = new Bat(
                this,
                world,
                new LVector2(2, 29, Limit.VEC2),
                new AreaRange(new Vector2(1, 29), new Rectangle(0, 0, 30, 7))
        );

        world.addEntity(bat);

        /*
        Entity slime = new Slime(
                this,
                world,
                new LVector2(2, 29, Limit.VEC2),
                new LVector2(new LengthLimit<Vector2>(10)),
                6
        );
        world.addEntity(slime);*/

        Menu pauseMenu = new PauseMenu(game, this, gui);
        addMenu("control", controlMenu);
        addMenu("pause", pauseMenu);
        addMenu("settings", new SettingsMenu(game, this, pauseMenu));
        addMenu("character", new CharacterMenu(game, gui, this, world, stats)); // TEMP
        setActiveMenu(controlMenu);

        game.music.setPlaying(new Music(Gdx.files.internal("music/sewers.mp3"), game.music, game.resources));
    }

    public void setPaused(boolean paused) {
        this.paused = paused;
    }

    public boolean isPaused() { // TODO: Improve this... game states?
        return paused || hasPopup();
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(activeMenu.getProcessor());
    }

    @Override
    public void render(float delta) {
        log.log();
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        if (!isPaused()) {
            world.update(delta);
            world.render(delta);
        } else {
            world.render(0);
        }

        super.draw(game.batch, delta);
    }

    @Override
    public void resize(int width, int height) {
        super.resize(width, height);

        world.resize(width, height);

        getMenu("control").setScale(width / 256f);
        getMenu("pause").setScale(width / 160f);
        getMenu("settings").setScale(width / 160f);
        getMenu("character").setScale(width / 300f);
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
