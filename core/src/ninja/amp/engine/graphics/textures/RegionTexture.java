package ninja.amp.engine.graphics.textures;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ninja.amp.engine.resources.ResourceHandler;

public class RegionTexture implements Texture {

    private TextureRegion region;

    public RegionTexture(TextureRegion region, ResourceHandler handler) {
        this.region = region;

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
