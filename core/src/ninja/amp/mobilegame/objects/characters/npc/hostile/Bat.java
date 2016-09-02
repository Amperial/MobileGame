package ninja.amp.mobilegame.objects.characters.npc.hostile;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;
import ninja.amp.mobilegame.engine.graphics.Atlas;
import ninja.amp.mobilegame.engine.graphics.RegionTexture;
import ninja.amp.mobilegame.engine.physics.collision.Hitbox;
import ninja.amp.mobilegame.engine.physics.mass.Mass;
import ninja.amp.mobilegame.engine.physics.vectors.LVector2;
import ninja.amp.mobilegame.map.World;
import ninja.amp.mobilegame.objects.body.Body;
import ninja.amp.mobilegame.objects.body.BodyPart;
import ninja.amp.mobilegame.objects.body.pose.Pose;
import ninja.amp.mobilegame.objects.body.pose.position.Position;
import ninja.amp.mobilegame.objects.body.pose.position.StaticPosition;
import ninja.amp.mobilegame.objects.body.pose.position.TreePosition;
import ninja.amp.mobilegame.objects.characters.npc.NPC;
import ninja.amp.mobilegame.objects.characters.npc.State;
import ninja.amp.mobilegame.objects.characters.npc.ai.actions.Action;
import ninja.amp.mobilegame.objects.characters.npc.ai.actions.movement.Stop;
import ninja.amp.mobilegame.screens.Screen;

public class Bat extends NPC {

    private Body body;
    private Action action;

    public Bat(Screen screen, World world, LVector2 position, LVector2 velocity, LVector2 acceleration, Mass mass, State state, Hitbox hitbox) {
        super(world, position, velocity, acceleration, mass, state);

        Atlas hostile = new Atlas(Gdx.files.internal("entities/hostile.pack"), screen);

        body = new Body() {
            Vector2 temp = new Vector2();

            @Override
            public Vector2 position() {
                return temp.set(getPosition()).add(-6f / 32f, 9f / 32f);
            }

            @Override
            public boolean flip() {
                return getVelocity().x > 0;
            }
        };

        body.setPose(new Pose() {
            final float offset = (float) Math.random();
            Position torso = new StaticPosition(0, 0, 0, 0, 18f / 32f, 13f / 32f, 0) {
                @Override
                public float getX() {
                    return body.position().x;
                }

                @Override
                public float getY() {
                    return body.position().y;
                }

                @Override
                public float getFlippedX() {
                    return body.position().x + getWidth();
                }
            };
            Position wing_right = new TreePosition(torso, 12f / 32f, 3f / 23f, 0, 0, 18f / 32f, 21f / 32f, -45f) {
                @Override
                public float getRotation() {
                    return super.getRotation() - MathUtils.sin((body.getPoseTime() + offset) * 6 * MathUtils.PI) * 45f;
                }
            };
            Position wing_left = new TreePosition(torso, 9f / 32f, 3f / 32f, 16f / 32f, 0, 16f / 32f, 21f / 32f, 45f) {
                @Override
                public float getRotation() {
                    return super.getRotation() + MathUtils.sin((body.getPoseTime() + offset) * 6 * MathUtils.PI) * 45f;
                }
            };

            @Override
            public Position getPosition(String id) {
                if (id.equals("torso")) {
                    return torso;
                } else if (id.equals("wing_right")) {
                    return wing_right;
                } else if (id.equals("wing_left")) {
                    return wing_left;
                }
                return null;
            }
        });

        BodyPart torso = new BodyPart(body, "torso", new RegionTexture(hostile.findRegion("bat/head"), screen), 0);
        BodyPart wing_right = new BodyPart(body, "wing_right", new RegionTexture(hostile.findRegion("bat/wing_right"), screen), 1);
        BodyPart wing_left = new BodyPart(body, "wing_left", new RegionTexture(hostile.findRegion("bat/wing_left"), screen), -1);
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
        setAction(new Stop(this, 1, true));
    }

}
