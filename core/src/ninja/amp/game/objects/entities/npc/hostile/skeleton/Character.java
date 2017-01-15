package ninja.amp.game.objects.entities.npc.hostile.skeleton;

import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import ninja.amp.engine.graphics.gui.input.AndInput;
import ninja.amp.engine.graphics.gui.input.Input;
import ninja.amp.engine.graphics.gui.input.RisingInput;
import ninja.amp.engine.graphics.textures.RegionTexture;
import ninja.amp.engine.graphics.textures.atlas.Atlas;
import ninja.amp.engine.graphics.textures.atlas.GameAtlas;
import ninja.amp.engine.map.World;
import ninja.amp.engine.map.collision.CollisionState;
import ninja.amp.engine.objects.body.Body;
import ninja.amp.engine.objects.body.BodyPart;
import ninja.amp.engine.objects.body.pose.Pose;
import ninja.amp.engine.objects.entities.Entity;
import ninja.amp.engine.objects.entities.character.movement.Move;
import ninja.amp.engine.objects.entities.character.movement.MoveController;
import ninja.amp.engine.objects.entities.character.movement.PoseController;
import ninja.amp.engine.objects.entities.character.movement.attack.Attack;
import ninja.amp.engine.objects.entities.character.movement.attack.AttackController;
import ninja.amp.engine.objects.entities.stats.Stat;
import ninja.amp.engine.physics.collision.EntityHitbox;
import ninja.amp.engine.physics.collision.Hitbox;
import ninja.amp.engine.physics.forces.Force;
import ninja.amp.engine.physics.forces.FrictionForce;
import ninja.amp.engine.physics.forces.Impulse;
import ninja.amp.engine.physics.forces.MultiForce;
import ninja.amp.engine.physics.forces.SimpleForce;
import ninja.amp.engine.physics.forces.StoppingForce;
import ninja.amp.engine.physics.mass.StaticMass;
import ninja.amp.engine.physics.vectors.LVector2;
import ninja.amp.engine.physics.vectors.limits.Limit;
import ninja.amp.engine.physics.vectors.limits.RectangleLimit;
import ninja.amp.game.objects.entities.character.movement.attack.StandardSwordAttack;
import ninja.amp.game.objects.entities.character.movement.poses.Idle;
import ninja.amp.game.objects.entities.character.movement.poses.Jumping;
import ninja.amp.game.objects.items.weapons.WeaponPart;
import ninja.amp.game.objects.items.weapons.swords.Longsword;
import ninja.amp.game.screens.game.GameScreen;

public class Character extends Entity {

    private Body body;
    private AttackController attacker;
    private MoveController move;
    private PoseController pose;
    private boolean flip = false;

    public Character(GameScreen screen, World world, LVector2 position, final Input left, final Input right, final Input jump, final Input control, Stat health, Stat protection, Stat strength) {
        super(
                world,
                position,
                new LVector2(new RectangleLimit(new Rectangle(-6, -20, 12, 40))),
                new LVector2(Limit.VEC2),
                new StaticMass(1),
                health,
                protection,
                strength
        );

        setHitbox(new EntityHitbox(this, new Rectangle(3f / 16f, 0, 10f / 16f, 30f / 16f)));

        Atlas entities = new Atlas(GameAtlas.ENTITIES, screen);
        body = new Body() {
            private Vector2 vector = new Vector2();
            private float max_up_velocity = 12.5f;
            private float max_down_velocity = -20;
            private float min_down_velocity = -2;

            @Override
            public Vector2 position() {
                vector.set(getPosition()).add(4f / 16f, 12f / 16f);
                if (pose.getMove() == Move.RUNNING) {
                    vector.add(0, Math.abs(MathUtils.sin((body.getPoseTime() * 2.5f + 0.25f) * MathUtils.PI)) * 0.0625f - 0.025f);
                }
                return vector;
            }

            @Override
            public boolean flip() {
                return flip;
            }

            @Override
            public float getPoseTime() {
                if (attacker.isAttacking()) {
                    return attacker.getAttack().getPoseTime();
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
        BodyPart torso = new BodyPart(body, "torso", new RegionTexture(entities.findRegion("hostile/skeleton/body"), screen), 0, 1);
        BodyPart head = new BodyPart(body, "head", new RegionTexture(entities.findRegion("hostile/skeleton/head"), screen), 1, 2);
        BodyPart arm_left_upper = new BodyPart(body, "arm_left_upper", new RegionTexture(entities.findRegion("hostile/skeleton/arm_upper"), screen), -5, 7, 40);
        BodyPart arm_left_lower = new BodyPart(body, "arm_left_lower", new RegionTexture(entities.findRegion("hostile/skeleton/arm_lower"), screen), -4, 6, 20);
        BodyPart arm_right_upper = new BodyPart(body, "arm_right_upper", new RegionTexture(entities.findRegion("hostile/skeleton/arm_upper"), screen), 6, -4, 40);
        BodyPart arm_right_lower = new BodyPart(body, "arm_right_lower", new RegionTexture(entities.findRegion("hostile/skeleton/arm_lower"), screen), 5, -3, 20);
        BodyPart leg_left_upper = new BodyPart(body, "leg_left_upper", new RegionTexture(entities.findRegion("hostile/skeleton/leg_upper"), screen), -2, 4, 40);
        BodyPart leg_left_lower = new BodyPart(body, "leg_left_lower", new RegionTexture(entities.findRegion("hostile/skeleton/leg_lower"), screen), -1, 3, 30);
        BodyPart leg_right_upper = new BodyPart(body, "leg_right_upper", new RegionTexture(entities.findRegion("hostile/skeleton/leg_upper"), screen), 3, -1, 40);
        BodyPart leg_right_lower = new BodyPart(body, "leg_right_lower", new RegionTexture(entities.findRegion("hostile/skeleton/leg_lower"), screen), 2, 0, 30);
        BodyPart weapon_right = new WeaponPart(body, arm_right_lower, new Longsword("Longsword", new StaticMass(1), screen), "weapon_right", 4, -2, 0, 5f / 16f, 0);
        //BodyPart weapon_left = new WeaponPart(body, arm_left_lower, new Hammer("Hammer", new StaticMass(1), screen), "character/weapon_left", -3, 5, 0, 4f/16f, 0);

        final Input fall = new AndInput(new RisingInput(control), new Input() {
            @Override
            public boolean getInput() {
                return isOnPlatform() && !attacker.isAttacking();
            }
        });
        final Input attack = new Input() {
            private Input attack = new RisingInput(control);

            @Override
            public boolean getInput() {
                return ((attack.getInput() && getCollisionState() != CollisionState.PLATFORM && !isOnPlatform()) || attacker.isAttacking()) && control.getInput();
            }
        };

        Attack standard = new Attack(new StandardSwordAttack(body), Attack.Type.STANDARD, 0.5f, 0.15f, 0.15f);
        attacker = new AttackController(standard) {
            @Override
            public boolean hasAttackHitbox() {
                return false;
            }

            @Override
            public Hitbox getAttackHitbox() {
                return null;
            }

            @Override
            public boolean isPressed() {
                return attack.getInput();
            }
        };

        move = new MoveController() {
            private Force move_left = new SimpleForce(new Vector2(-100, 0));
            private Force move_right = new SimpleForce(new Vector2(100, 0));
            private Force move_jump = new MultiForce(new StoppingForce(new Vector2(0, 1)), new Impulse(new Vector2(0, 12.5f)));
            private Force friction_air = new FrictionForce(new Vector2(5, 0));
            private Force friction_ground = new FrictionForce(new Vector2(20, 0));
            private Force direction_change = new StoppingForce(new Vector2(1, 0));
            private boolean falling_lastframe = false;
            private boolean has_jumpchance = false;
            private boolean airjump = true;

            @Override
            public void update(Entity entity, float delta) {
                if (getCollisionState() == CollisionState.PLATFORM && !isInPlatform()) {
                    setCollisionState(CollisionState.NORMAL);
                }

                boolean onGround = isOnGround();

                // Horizontal movement
                if (left.getInput()) {
                    if (entity.getVelocity().x > 0 && onGround) {
                        entity.applyForce(direction_change.calculate(entity, delta));
                    }
                    entity.applyForce(move_left.calculate(entity, delta));
                    flip = true;
                } else if (right.getInput()) {
                    if (entity.getVelocity().x < 0 && onGround) {
                        entity.applyForce(direction_change.calculate(entity, delta));
                    }
                    entity.applyForce(move_right.calculate(entity, delta));
                    flip = false;
                } else {
                    if (onGround) {
                        entity.applyForce(friction_ground.calculate(entity, delta));
                    } else {
                        entity.applyForce(friction_air.calculate(entity, delta));
                    }
                }

                // Vertical movement
                if (onGround) {
                    airjump = true;
                    falling_lastframe = false;
                    has_jumpchance = true;
                } else {
                    if (!falling_lastframe) {
                        falling_lastframe = true;
                    } else {
                        has_jumpchance = false;
                    }
                }
                entity.applyForce(SimpleForce.GRAVITY.calculate(entity, delta));
                if (jump.getInput() && (onGround || airjump)) {
                    entity.applyForce(move_jump.calculate(entity, delta));
                    if (!onGround && !has_jumpchance) {
                        airjump = false;
                    }
                } else if (fall.getInput()) {
                    setCollisionState(CollisionState.PLATFORM);
                }
            }
        };

        pose = new PoseController(body) {
            private Pose idle = new Idle(body);
            private Pose running = new ninja.amp.game.objects.entities.character.movement.poses.Running(body);
            private Pose jumping = new Jumping(body);
            private float running_min = 1f;

            @Override
            public void update(Entity entity, float delta) {
                attacker.update(delta);
                super.update(entity, delta);
            }

            @Override
            public Move getMove() {
                if (isImmune()) {
                    return Move.IMMUNE;
                } else if (attacker.isAttacking()) {
                    return Move.ATTACKING;
                } else if (!isOnGround()) {
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
                        return attacker.getAttack().getPose();
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

}
