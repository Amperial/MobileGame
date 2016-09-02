package ninja.amp.mobilegame.objects.characters;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import ninja.amp.mobilegame.engine.graphics.Atlas;
import ninja.amp.mobilegame.engine.graphics.RegionTexture;
import ninja.amp.mobilegame.engine.physics.collision.EntityHitbox;
import ninja.amp.mobilegame.engine.physics.forces.Force;
import ninja.amp.mobilegame.engine.physics.forces.FrictionForce;
import ninja.amp.mobilegame.engine.physics.forces.Impulse;
import ninja.amp.mobilegame.engine.physics.forces.MultiForce;
import ninja.amp.mobilegame.engine.physics.forces.SimpleForce;
import ninja.amp.mobilegame.engine.physics.forces.StoppingForce;
import ninja.amp.mobilegame.engine.physics.mass.StaticMass;
import ninja.amp.mobilegame.engine.physics.vectors.limits.Limit;
import ninja.amp.mobilegame.engine.physics.vectors.limits.RectangleLimit;
import ninja.amp.mobilegame.map.World;
import ninja.amp.mobilegame.objects.Entity;
import ninja.amp.mobilegame.engine.physics.vectors.LVector2;
import ninja.amp.mobilegame.objects.body.Body;
import ninja.amp.mobilegame.objects.body.BodyPart;
import ninja.amp.mobilegame.objects.body.WeaponPart;
import ninja.amp.mobilegame.objects.body.pose.Pose;
import ninja.amp.mobilegame.objects.characters.movement.Move;
import ninja.amp.mobilegame.objects.characters.movement.MoveController;
import ninja.amp.mobilegame.objects.characters.movement.PoseController;
import ninja.amp.mobilegame.objects.characters.movement.input.Input;
import ninja.amp.mobilegame.objects.characters.movement.poses.Idle;
import ninja.amp.mobilegame.objects.characters.movement.poses.Jumping;
import ninja.amp.mobilegame.objects.characters.movement.poses.Running;
import ninja.amp.mobilegame.objects.characters.movement.poses.attacks.Attack;
import ninja.amp.mobilegame.objects.characters.movement.poses.attacks.AttackController;
import ninja.amp.mobilegame.objects.characters.movement.poses.attacks.StandardSwordAttack;
import ninja.amp.mobilegame.objects.items.weapons.swords.Longsword;
import ninja.amp.mobilegame.screens.game.GameScreen;

public class Character extends Entity {

    private Body body;
    private AttackController attack;
    private MoveController move;
    private PoseController pose;
    private boolean flip = false;

    public Character(GameScreen screen, World world, LVector2 position, final Input left, final Input right, final Input jump, final Input control) {
        super(
                world,
                position,
                new LVector2(new RectangleLimit(new Rectangle(-6, -20, 12, 40))),
                new LVector2(Limit.VEC2),
                new StaticMass(1)
        );

        setHitbox(new EntityHitbox(this, new Rectangle(3f / 16f, 0, 10f / 16f, 30f / 16f)));

        Atlas bodyAtlas = new Atlas(Gdx.files.internal("entities/character.pack"), screen);
        body = new Body() {
            private Vector2 vector = new Vector2();
            private float max_up_velocity = 12.5f;
            private float max_down_velocity = -20;
            private float min_down_velocity = -2;

            @Override
            public Vector2 position() {
                vector.set(getPosition()).add(4f/16f, 9f/16f);
                if (pose.getMove() == Move.RUNNING) {
                    vector.add(0, Math.abs(MathUtils.sin((body.getPoseTime()*2.5f + 0.25f)* MathUtils.PI))*0.0625f);
                }
                return vector;
            }

            @Override
            public boolean flip() {
                return flip;
            }

            @Override
            public float getPoseTime() {
                if (attack.isAttacking()) {
                    return attack.getAttack().getPoseTime();
                } else if (getVelocity().y > 0 || getVelocity().y < 0) {
                    float y_velocity = getVelocity().y;
                    if (y_velocity > max_up_velocity || y_velocity < max_down_velocity) {
                        return 1;
                    } else if (y_velocity > 0) {
                        return y_velocity / max_up_velocity;
                    } else {
                        if (y_velocity > min_down_velocity) {
                            return 0;
                        } else {
                            return (y_velocity - min_down_velocity) / (max_down_velocity - min_down_velocity);
                        }
                    }
                } else {
                    return super.getPoseTime();
                }
            }
        };
        body.setPose(new Idle(body));
        BodyPart torso = new BodyPart(body, "torso", new RegionTexture(bodyAtlas.findRegion("body"), screen), 0, 1);
        BodyPart head = new BodyPart(body, "head", new RegionTexture(bodyAtlas.findRegion("head"), screen), 1, 2);
        BodyPart arm_left_upper = new BodyPart(body, "arm_left_upper", new RegionTexture(bodyAtlas.findRegion("arm_upper"), screen), -5, 7, 50);
        BodyPart arm_left_lower = new BodyPart(body, "arm_left_lower", new RegionTexture(bodyAtlas.findRegion("arm_lower"), screen), -4, 6, 30);
        BodyPart arm_right_upper = new BodyPart(body, "arm_right_upper", new RegionTexture(bodyAtlas.findRegion("arm_upper"), screen), 6, -4, 50);
        BodyPart arm_right_lower = new BodyPart(body, "arm_right_lower", new RegionTexture(bodyAtlas.findRegion("arm_lower"), screen), 5, -3, 30);
        BodyPart leg_left_upper = new BodyPart(body, "leg_left_upper", new RegionTexture(bodyAtlas.findRegion("leg_upper"), screen), -2, 4, 50);
        BodyPart leg_left_lower = new BodyPart(body, "leg_left_lower", new RegionTexture(bodyAtlas.findRegion("leg_lower"), screen), -1, 3, 40);
        BodyPart leg_right_upper = new BodyPart(body, "leg_right_upper", new RegionTexture(bodyAtlas.findRegion("leg_upper"), screen), 3, -1, 50);
        BodyPart leg_right_lower = new BodyPart(body, "leg_right_lower", new RegionTexture(bodyAtlas.findRegion("leg_lower"), screen), 2, 0, 40);
        BodyPart weapon_right = new WeaponPart(body, arm_right_lower, new Longsword("Longsword", new StaticMass(1), screen), "weapon_right", 4, -2, 0, 4f/16f, 0);
        //BodyPart weapon_left = new WeaponPart(body, arm_left_lower, new Hammer("Hammer", new StaticMass(1), screen), "weapon_left", -3, 5, 0, 4f/16f, 0);

        Attack standard = new Attack(new StandardSwordAttack(body), Attack.Type.STANDARD, 0.5f, 0.15f, 0.15f);
        attack = new AttackController(standard) {
            @Override
            public boolean isPressed() {
                return control.getInput();
            }
        };

        move = new MoveController() {
            private Force move_left = new SimpleForce(new Vector2(-100, 0));
            private Force move_right = new SimpleForce(new Vector2(100, 0));
            private Force move_jump = new MultiForce(new StoppingForce(new Vector2(0, 1)), new Impulse(new Vector2(0, 12.5f)));
            private Force friction_air = new FrictionForce(new Vector2(5, 0));
            private Force friction_ground = new FrictionForce(new Vector2(10, 0));
            private Force direction_change = new StoppingForce(new Vector2(1, 0));

            @Override
            public void update(Entity entity, float delta) {
                // Horizontal movement
                if (left.getInput()) {
                    if (entity.getVelocity().x > 0 && !isFalling()) {
                        entity.applyForce(direction_change.calculate(entity, delta));
                    }
                    entity.applyForce(move_left.calculate(entity, delta));
                    flip = true;
                } else if (right.getInput()) {
                    if (entity.getVelocity().x < 0 && !isFalling()) {
                        entity.applyForce(direction_change.calculate(entity, delta));
                    }
                    entity.applyForce(move_right.calculate(entity, delta));
                    flip = false;
                } else {
                    if (isFalling()) {
                        entity.applyForce(friction_air.calculate(entity, delta));
                    } else {
                        entity.applyForce(friction_ground.calculate(entity, delta));
                    }
                }

                // Vertical movement
                entity.applyForce(SimpleForce.GRAVITY.calculate(entity, delta));
                if (jump.getInput()) {
                    entity.applyForce(move_jump.calculate(entity, delta));
                }
            }
        };

        pose = new PoseController(body) {
            private Pose idle = new Idle(body);
            private Pose running = new Running(body);
            private Pose jumping = new Jumping(body);
            private float running_min = 1f;

            @Override
            public void update(Entity entity, float delta) {
                attack.update(delta);
                super.update(entity, delta);
            }

            @Override
            public Move getMove() {
                if (isImmune()) {
                    return Move.IMMUNE;
                } else if (attack.isAttacking()) {
                    return Move.ATTACKING;
                } else if (isFalling()) {
                    return Move.JUMPING;
                } else if (getVelocity().x > running_min || getVelocity().x < -running_min) {
                    return Move.RUNNING;
                } else {
                    return Move.IDLE;
                }
            }

            @Override
            public Pose getPose(Move move) {
                switch (move) {
                    case IDLE:
                    case IMMUNE:
                        return idle;
                    case ATTACKING:
                        return attack.getAttack().getPose();
                    case JUMPING:
                        return jumping;
                    case RUNNING:
                        return running;
                    default:
                        return idle;
                }
            }
        };
    }

    public Body getBody() {
        return body;
    }

    public MoveController getMove() {
        return move;
    }

    public PoseController getPose() {
        return pose;
    }

    @Override
    public void update(float delta) {
        // Update movement controller
        move.update(this, delta);

        // Update entity
        super.update(delta);

        // Update pose controller
        pose.update(this, delta);
    }

    private boolean isFalling() {
        return getVelocity().y > 0 || getVelocity().y < 0; // TODO: Check if player is standing on solid tile
    }

}
