package ninja.amp.engine.graphics.textures;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ninja.amp.engine.resources.ResourceHandler;

public class ParticleTexture implements Texture {

    private TextureRegion region;

    public ParticleTexture(Texture particles, int x, int y, ResourceHandler handler) {
        region = new TextureRegion(particles.getRegion(), x, y, 1, 1);

        handler.attachResource(this);
    }

    @Override
    public TextureRegion getRegion() {
        return region;
    }

    @Override
    public void dispose() {
        region = null;
    }

}
