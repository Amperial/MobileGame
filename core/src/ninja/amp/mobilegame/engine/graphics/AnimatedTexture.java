package ninja.amp.mobilegame.engine.graphics;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import ninja.amp.mobilegame.engine.resources.ResourceHandler;

public class AnimatedTexture implements Texture {

    private Animation animation;
    private float stateTime;

    public AnimatedTexture(Animation animation, ResourceHandler handler) {
        this.animation = animation;

        handler.attachResource(this);
    }

    public void update(float delta) {
        stateTime += delta;
    }

    @Override
    public TextureRegion getRegion() {
        return animation.getKeyFrame(stateTime);
    }

    @Override
    public void dispose() {
        animation = null;
    }

    public static AnimatedTexture load(FileHandle file, int tileWidth, int tileHeight, float frameDuration, Animation.PlayMode playMode, ResourceHandler handler) {
        Texture texture = new SingleTexture(file, handler);
        TextureRegion[][] temp = texture.getRegion().split(tileWidth, tileHeight);
        Array<TextureRegion> frames = new Array<TextureRegion>();
        for (TextureRegion[] row : temp) {
            for (TextureRegion frame : row) {
                frames.add(frame);
            }
        }
        return new AnimatedTexture(new Animation(frameDuration, frames, playMode), handler);
    }

}
