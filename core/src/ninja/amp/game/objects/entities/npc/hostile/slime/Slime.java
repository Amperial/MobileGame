package ninja.amp.game.objects.entities.npc.hostile.slime;

import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import ninja.amp.engine.graphics.gui.screens.Screen;
import ninja.amp.engine.graphics.textures.RegionTexture;
import ninja.amp.engine.graphics.textures.atlas.Atlas;
import ninja.amp.engine.graphics.textures.atlas.GameAtlas;
import ninja.amp.engine.map.World;
import ninja.amp.engine.objects.body.Body;
import ninja.amp.engine.objects.body.BodyPart;
import ninja.amp.engine.objects.body.pose.Pose;
import ninja.amp.engine.objects.body.pose.position.Position;
import ninja.amp.engine.objects.body.pose.position.StaticPosition;
import ninja.amp.engine.objects.entities.npc.NPC;
import ninja.amp.engine.objects.entities.npc.State;
import ninja.amp.engine.objects.entities.npc.ai.actions.Action;
import ninja.amp.engine.objects.entities.npc.ai.actions.ActionCycle;
import ninja.amp.engine.objects.entities.npc.ai.actions.ActionList;
import ninja.amp.engine.objects.entities.npc.ai.actions.RestRandom;
import ninja.amp.engine.objects.entities.npc.ai.actions.movement.Jump;
import ninja.amp.engine.objects.entities.npc.ai.actions.movement.Stop;
import ninja.amp.engine.objects.entities.npc.ai.actions.movement.Wander;
import ninja.amp.engine.objects.entities.npc.ai.actions.range.Range;
import ninja.amp.engine.objects.entities.stats.BaseStat;
import ninja.amp.engine.objects.entities.stats.BaseType;
import ninja.amp.engine.physics.collision.EntityHitbox;
import ninja.amp.engine.physics.forces.SimpleForce;
import ninja.amp.engine.physics.mass.StaticMass;
import ninja.amp.engine.physics.vectors.LVector2;
import ninja.amp.engine.physics.vectors.limits.LengthLimit;
import ninja.amp.engine.physics.vectors.limits.Limit;

public class Slime extends NPC {

    private Screen screen;
    private Body body;
    private Range range;
    private Action action;
    private int size;

    public Slime(Screen screen, World world, LVector2 position, LVector2 velocity, final int size) {
        super(
                world,
                position,
                velocity,
                new LVector2(Limit.VEC2),
                new StaticMass(size),
                State.HOSTILE,
                new BaseStat(BaseType.HEALTH, size),
                new BaseStat(BaseType.PROTECTION, 0),
                new BaseStat(BaseType.STRENGTH, 1));

        this.screen = screen;
        this.size = size;
        this.range = Range.NONE;

        setHitbox(new EntityHitbox(this, new Rectangle(0, 0, size / 2f, size / 2f)));

        Atlas entities = new Atlas(GameAtlas.ENTITIES, screen);

        body = new Body() {
            Vector2 temp = new Vector2();

            @Override
            public Vector2 position() {
                return temp.set(getPosition());
            }

            @Override
            public boolean flip() {
                return false;
            }
        };

        body.setPose(new Pose() {
            Position torso = new StaticPosition(0, 0, 0, 0, size / 2f, size / 2f, 0) {
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
            @Override
            public Position getPosition(String id) {
                return torso;
            }
        });

        BodyPart torso = new BodyPart(body, "torso", new RegionTexture(entities.findRegion("hostile/slime/slime"), screen), 0);
    }

    @Override
    public Body getBody() {
        return body;
    }

    @Override
    public void update(float delta) {
        applyForce(SimpleForce.GRAVITY.calculate(this, delta).scl(getMass()));

        super.update(delta);
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
        /*
            List:
            - Cycle (can perform if in range of character)
                -Jump in direction of character
                -Rest random
            - Wander (can always perform)
         */
        setAction(new ActionList(
                new ActionCycle(
                        new Jump(this, 3 * size, 3 + (size / 2f), Range.NONE) {
                            @Override
                            public Vector2 getTarget() {
                                return getWorld().getCharacter().getPosition();
                            }
                        },
                        new Stop(this, 10 * size, false),
                        new RestRandom((size - 1) / 4f, size - 0.5f)
                ),
                new Wander(this, 3, Range.NONE)
        ));
    }

    @Override
    public void die() {
        super.die();

        int size = (int)getMass();
        size--;
        if (size > 0) {
            for (int i = 0; i < 3; i++) {
                Slime baby = new Slime(screen, getWorld(), new LVector2(getPosition(), Limit.VEC2), new LVector2(getVelocity().cpy().add((float)Math.random(), (float)Math.random()), new LengthLimit<Vector2>(10)), size);
                getWorld().addEntity(baby);
            }
        }
    }

}
