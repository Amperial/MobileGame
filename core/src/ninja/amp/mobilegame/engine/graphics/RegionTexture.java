package ninja.amp.mobilegame.engine.graphics;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ninja.amp.mobilegame.engine.resources.ResourceHandler;

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
