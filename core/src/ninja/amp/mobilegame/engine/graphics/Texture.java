package ninja.amp.mobilegame.engine.graphics;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ninja.amp.mobilegame.engine.resources.Resource;

public interface Texture extends Resource {

    TextureRegion getRegion();

}
