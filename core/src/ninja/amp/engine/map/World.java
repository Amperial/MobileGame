package ninja.amp.engine.map;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import ninja.amp.engine.Game;
import ninja.amp.engine.graphics.backgrounds.Background;
import ninja.amp.engine.graphics.gui.screens.Screen;
import ninja.amp.engine.graphics.gui.screens.ScreenCamera;
import ninja.amp.engine.objects.entities.Entity;
import ninja.amp.engine.objects.entities.character.Character;
import ninja.amp.engine.particles.ParticleSystem;
import ninja.amp.engine.physics.vectors.limits.CubeLimit;
import ninja.amp.engine.physics.vectors.limits.Limit;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class World {

    private Game game;

    private ScreenCamera camera;
    private float camElasticity = 10;
    private Vector2 camOffset = new Vector2();

    private Background background;

    private Map map;

    private Character character;
    private Set<Entity> entities = new HashSet<Entity>();

    private ParticleSystem particles;

    private float scale;

    public World(Game game, Screen screen, MapLoader mapLoader) {
        this.game = game;

        // Setup camera
        camera = new ScreenCamera(screen, Limit.VEC3);

        // Load map
        map = mapLoader.loadMap(screen);
        background = mapLoader.loadBackground(screen);
        mapLoader.loadEntities(this, screen);

        // Load character
        //character = initializeCharacter(screen);

        // Setup particles
        particles = new ParticleSystem();
    }

    public void update(float delta) {
        if (delta > 0.05f) {
            delta = 0.05f;
        }

        // Update character and entities
        character.update(delta);
        for (Entity entity : entities) {
            entity.update(delta);
        }

        // Remove dead entities
        // TODO: Death animations?
        Iterator<Entity> entityIterator = entities.iterator();
        while (entityIterator.hasNext()) {
            if (entityIterator.next().isDead()) {
                entityIterator.remove();
            }
        }

        // Update particles
        particles.update(delta);

        // Update screen camera
        camera.position.add(
                ((character.getPosition().x + 0.5f + camOffset.x) * scale - camera.position.x) * delta * camElasticity,
                ((character.getPosition().y + 0.5f + camOffset.y) * scale - camera.position.y) * delta * camElasticity,
                0
        );
    }

    public void render(float delta) {
        camera.apply(game.batch);
        game.batch.begin();

        // Draw Background
        background.draw(game.batch, (map.getWidth() / 2) - camera.position.x, (camera.viewportHeight / 2) - camera.position.y, camera.viewportWidth, camera.viewportHeight);

        // Draw Map Background and Midground
        map.getBackground().draw(game.batch, 0, 64, 0, 36);
        map.getMidground().draw(game.batch, 0, 64, 0, 36);

        // Draw particles
        particles.draw(game.batch);

        // Draw Character
        character.getBody().draw(game.batch, delta);

        // Draw Entities
        for (Entity entity : entities) {
            if (entity.getBody() != null) {
                entity.getBody().draw(game.batch, delta);
            }
        }

        // Draw Map Foreground
        map.getForeground().draw(game.batch, 0, 64, 0, 36);

        game.batch.end();
    }

    public Map getMap() {
        return map;
    }

    public Character getCharacter() {
        return character;
    }

    public void setCharacter(Character character) {
        this.character = character;
    }

    public Set<Entity> getEntities() {
        return entities;
    }

    public void addEntity(Entity entity) {
        entities.add(entity);
    }

    public void resize(int width, int height) {
        scale = 16 * width / 300;

        map.setScale(scale);
        background.setScale(height / 128f);

        camera.setToOrtho(false, width, height);
        camera.setLimit(new CubeLimit(
                new Vector3(camera.viewportWidth / 2, camera.viewportHeight / 2, 0),
                new Vector3(map.getWidth() - (camera.viewportWidth / 2), map.getHeight() - (camera.viewportHeight / 2), 0)));
        camera.position.set((character.getPosition().x + 0.5f + camOffset.x) * scale, (character.getPosition().y + 0.5f + camOffset.y) * scale, 0);

        character.getBody().setScale(scale);

        for (Entity entity : entities) {
            if (entity.getBody() != null) {
                entity.getBody().setScale(scale);
            }
        }

        particles.setScale(scale);
    }

    public void dispose() {
    }

}
