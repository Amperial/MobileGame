package ninja.amp.mobilegame.objects.characters.movement.positions;

import ninja.amp.mobilegame.engine.graphics.Texture;
import ninja.amp.mobilegame.objects.characters.movement.Body;
import ninja.amp.mobilegame.objects.characters.movement.BodyPart;
import ninja.amp.mobilegame.objects.characters.movement.Position;
import ninja.amp.mobilegame.objects.items.weapons.Weapon;

public class WeaponPart extends BodyPart {

    private Weapon weapon;
    private Position position;

    public WeaponPart(Body body, BodyPart arm, final Weapon weapon, int depth, int flippedDepth, float offsetX, float offsetY, float rotation) {
        super(body, arm, null, depth, flippedDepth, null);

        this.weapon = weapon;
        this.position = new Position(arm.getPosition(), offsetX, offsetY, 0, 0, 0, 0, rotation) {
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

    public WeaponPart(Body body, BodyPart arm, final Weapon weapon, int depth, float offsetX, float offsetY, float rotation) {
        this(body, arm, weapon, depth, depth, offsetX, offsetY, rotation);
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
