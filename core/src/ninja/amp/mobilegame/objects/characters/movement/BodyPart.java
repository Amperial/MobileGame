package ninja.amp.mobilegame.objects.characters.movement;

import com.badlogic.gdx.graphics.g2d.Batch;
import ninja.amp.mobilegame.engine.graphics.Texture;

public class BodyPart {

    private Body body;
    private BodyPart parent;
    private Texture texture;
    private int depth;
    private int flippedDepth;
    private Position position;
    private float scale;

    public BodyPart(Body body, BodyPart parent, Texture texture, int depth, int flippedDepth, Position position) {
        this.body = body;
        this.parent = parent;
        this.texture = texture;
        this.depth = depth;
        this.flippedDepth = flippedDepth;
        this.position = position;

        body.addBodyPart(this);
    }
    public BodyPart(Body body, BodyPart parent, Texture texture, int depth, Position position) {
        this(body, parent, texture, depth, depth, position);
    }

    public Body getBody() {
        return body;
    }

    public BodyPart getParent() {
        return parent;
    }

    public Texture getTexture() {
        return texture;
    }

    public int getDepth() {
        return depth;
    }

    public int getFlippedDepth() {
        return flippedDepth;
    }

    public Position getPosition() {
        return position;
    }

    public void draw(Batch batch, float delta, boolean flipped) {
        getPosition().draw(batch, delta, getTexture().getRegion(), scale, flipped);
    }

    public float getScale() {
        return scale;
    }

    public void setScale(float scale) {
        this.scale = scale;
    }

}
