package ninja.amp.mobilegame.map;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import ninja.amp.mobilegame.MobileGame;
import ninja.amp.mobilegame.objects.characters.Character;
import ninja.amp.mobilegame.physics.forces.Force;
import ninja.amp.mobilegame.physics.forces.GravitationalForce;
import ninja.amp.mobilegame.physics.forces.SimpleForce;
import ninja.amp.mobilegame.physics.vectors.LVector2;
import ninja.amp.mobilegame.physics.vectors.limits.LengthLimit;
import ninja.amp.mobilegame.physics.vectors.limits.RectangleLimit;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class World {

    private MobileGame game;
    private Character character;
    private Map<String, Force> forces = new HashMap<String, Force>();
    private Set<Force> impulses = new HashSet<Force>();
    private Texture texture;

    Force gravity = new SimpleForce(new Vector2(0, -500f), false);
    public World(MobileGame game) {
        this.game = game;
        character = new Character(this, new LVector2(20, 20, new RectangleLimit(new Rectangle(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight()))) {
            
            @Override
            public void limit() {
                super.limit();
                
            }
        }, new LVector2(new RectangleLimit(new Rectangle(-500, -500, 1000, 1000))), new LVector2(new LengthLimit<Vector2>(10000000f)), 1f);
        texture = new Texture(Gdx.files.internal("entities/coin.png"));
    }

    public void update(float delta) {
        if (delta > 0.05f) {
            delta = 0.05f;
        }
        
        for (Force force : forces.values()) {
            character.applyForce(force.calculate(character, delta));
        }
        for (Force force : impulses) {
            character.applyForce(force.calculate(character, delta));
        }
        character.applyForce(gravity.calculate(character, delta));
        impulses.clear();
        character.update(delta);
        
        ((LVector2)character.getPosition()).limit();
        
        
    }
    public void render(float delta) {
        game.batch.draw(texture, character.getPosition().x, character.getPosition().y);
        // update all entities
        
        
    }

    public Character getCharacter() {
        return character;
    }

    public void addForce(String name, Force force) {
        forces.put(name, force);
    }

    public void removeForce(String name) {
        forces.remove(name);
    }

    public void addImpulse(Force force) {
        impulses.add(force);
    }
}
