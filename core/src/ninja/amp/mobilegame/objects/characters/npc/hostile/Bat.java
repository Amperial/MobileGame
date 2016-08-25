package ninja.amp.mobilegame.objects.characters.npc.hostile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import ninja.amp.mobilegame.engine.graphics.Atlas;
import ninja.amp.mobilegame.engine.graphics.RegionTexture;
import ninja.amp.mobilegame.engine.physics.collision.Hitbox;
import ninja.amp.mobilegame.engine.physics.mass.Mass;
import ninja.amp.mobilegame.engine.physics.vectors.LVector2;
import ninja.amp.mobilegame.map.World;
import ninja.amp.mobilegame.objects.characters.movement.Body;
import ninja.amp.mobilegame.objects.characters.movement.BodyPart;
import ninja.amp.mobilegame.objects.characters.movement.Position;
import ninja.amp.mobilegame.objects.characters.npc.NPC;
import ninja.amp.mobilegame.objects.characters.npc.State;
import ninja.amp.mobilegame.objects.characters.npc.ai.actions.Action;
import ninja.amp.mobilegame.objects.characters.npc.ai.actions.Idle;
import ninja.amp.mobilegame.screens.Screen;

public class Bat extends NPC {

    private Body body;
    private Action action;

    public Bat(Screen screen, World world, LVector2 position, LVector2 velocity, LVector2 acceleration, Mass mass, State state, Hitbox hitbox) {
        super(world, position, velocity, acceleration, mass, state, hitbox);

        Atlas hostile = new Atlas(Gdx.files.internal("entities/hostile.pack"), screen);

        body = new Body() {
            Vector2 temp = new Vector2();
            @Override
            public Vector2 position() {
                return temp.set(getPosition()).add(-6f/32f, 9f/32f);
            }
        };

        BodyPart torso = new BodyPart(body, null, new RegionTexture(hostile.findRegion("bat/head"), screen), 0, new Position(null, 0, 0, 0, 0, 18f/32f, 13f/32f, 0) {
            @Override
            public float getX(boolean flipped) {
                if (flipped) {
                    return body.position().x + getWidth();
                } else {
                    return body.position().x;
                }
            }
            @Override
            public float getY(boolean flipped) {
                return body.position().y;
            }
            @Override
            public float getRotation() {
                return 0;
            }
        });
        final float offset = (float)Math.random();
        BodyPart wing_right = new BodyPart(body, torso, new RegionTexture(hostile.findRegion("bat/wing_right"), screen), 1, new Position(torso.getPosition(), 12f/32f, 3f/32f, 0, 0, 18f/32f, 21f/32f, -45f) {
            @Override
            public float getRotation() {
                return super.getRotation() - (float)Math.sin((getTotalTime()+offset)*6*Math.PI)*45f;
            }
        });
        BodyPart wing_left = new BodyPart(body, torso, new RegionTexture(hostile.findRegion("bat/wing_left"), screen), -1, new Position(torso.getPosition(), 9f/32f, 3f/32f, 16f/32f, 0, 16f/32f, 21f/32f, 45f) {
            @Override
            public float getRotation() {
                return super.getRotation() + (float)Math.sin((getTotalTime()+offset)*6*Math.PI)*45f;
            }
        });
    }

    public Body getBody() {
        return body;
    }

    @Override
    public boolean hasAction() {
        return action != null && !action.isComplete();
    }

    @Override
    public Action getAction() {
        return action;
    }

    @Override
    public void setAction(Action action) {
        if (hasAction()) {
            getAction().cancel();
        }
        this.action = action;
        action.begin();
    }

    @Override
    public void chooseAction() {
        setAction(new Idle(this));
    }

}
