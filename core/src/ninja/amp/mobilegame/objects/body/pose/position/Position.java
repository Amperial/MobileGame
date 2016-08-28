package ninja.amp.mobilegame.objects.body.pose.position;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

public interface Position {

    float getOffsetX();

    float getOffsetY();

    float getOriginX();

    float getOriginY();

    float getWidth();

    float getHeight();

    float getRotation();

    float getX();

    float getY();

    float getFlippedX();

    void draw(Batch batch, float delta, TextureRegion region, float scale, boolean flipped);

}
