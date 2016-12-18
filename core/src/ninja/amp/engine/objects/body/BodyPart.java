package ninja.amp.engine.objects.body;

import com.badlogic.gdx.graphics.g2d.Batch;
import ninja.amp.engine.graphics.gui.Scalable;
import ninja.amp.engine.graphics.textures.Texture;
import ninja.amp.engine.objects.body.pose.position.ElasticPosition;
import ninja.amp.engine.objects.body.pose.position.Position;
import ninja.amp.engine.objects.body.pose.position.Positionable;

public class BodyPart implements Positionable, Scalable {

    private Body body;
    private String id;
    private Texture texture;
    private int depth;
    private int flippedDepth;
    private Position position;
    private float scale;

    public BodyPart(Body body, String id, Texture texture, int depth, int flippedDepth, Position position) {
        this.body = body;
        this.id = id;
        this.texture = texture;
        this.depth = depth;
        this.flippedDepth = flippedDepth;
        this.position = position;

        body.addBodyPart(this);
    }

    public BodyPart(Body body, String id, Texture texture, int depth, int flippedDepth, float elasticity) {
        this(body, id, texture, depth, flippedDepth, new ElasticPosition(body.getPose().getPosition(id), elasticity));
    }

    public BodyPart(Body body, String id, Texture texture, int depth, int flippedDepth) {
        this(body, id, texture, depth, flippedDepth, body.getPose().getPosition(id));
    }

    public BodyPart(Body body, String id, Texture texture, int depth) {
        this(body, id, texture, depth, depth);
    }

    public Body getBody() {
        return body;
    }

    public String getId() {
        return id;
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

    public void draw(Batch batch, float delta, boolean flipped) {
        getPosition().draw(batch, delta, getTexture().getRegion(), scale, flipped);
    }

    @Override
    public float getScale() {
        return scale;
    }

    @Override
    public void setScale(float scale) {
        this.scale = scale;
    }

    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public Position getTargetPosition() {
        Position position = getPosition();
        if (position instanceof ElasticPosition) {
            return ((ElasticPosition) position).getTargetPosition();
        } else {
            return position;
        }
    }

    @Override
    public void setTargetPosition(Position target) {
        Position position = getPosition();
        if (position instanceof ElasticPosition) {
            ((ElasticPosition) position).setTargetPosition(target);
        } else {
            this.position = target;
        }
    }

}
