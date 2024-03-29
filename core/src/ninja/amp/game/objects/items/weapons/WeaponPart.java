package ninja.amp.game.objects.items.weapons;

import ninja.amp.engine.graphics.textures.Texture;
import ninja.amp.engine.objects.body.Body;
import ninja.amp.engine.objects.body.BodyPart;
import ninja.amp.engine.objects.body.pose.position.Position;
import ninja.amp.engine.objects.body.pose.position.TreePosition;

public class WeaponPart extends BodyPart {

    private Weapon weapon;
    private Position position;

    public WeaponPart(Body body, BodyPart arm, final Weapon weapon, String id, int depth, int flippedDepth, float offsetX, float offsetY, float rotation) {
        super(body, id, null, depth, flippedDepth, null);

        this.weapon = weapon;
        this.position = new TreePosition(arm.getPosition(), offsetX, offsetY, 0, 0, 0, 0, rotation) {
            @Override
            public float getOriginX() {
                return weapon.getPosition().getOriginX();
            }

            @Override
            public float getOriginY() {
                return weapon.getPosition().getOriginY();
            }

            @Override
            public float getWidth() {
                return weapon.getPosition().getWidth();
            }

            @Override
            public float getHeight() {
                return weapon.getPosition().getHeight();
            }

            @Override
            public float getRotation() {
                return super.getRotation() + weapon.getPosition().getRotation();
            }
        };
    }

    public WeaponPart(Body body, BodyPart arm, Weapon weapon, String id, int depth, float offsetX, float offsetY, float rotation) {
        this(body, arm, weapon, id, depth, depth, offsetX, offsetY, rotation);
    }

    @Override
    public Texture getTexture() {
        return weapon.getTexture();
    }

    @Override
    public Position getPosition() {
        return position;
    }

}
