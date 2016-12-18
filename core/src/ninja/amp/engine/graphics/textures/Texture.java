package ninja.amp.engine.graphics.textures;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ninja.amp.engine.resources.Resource;

public interface Texture extends Resource {

    TextureRegion getRegion();

}
