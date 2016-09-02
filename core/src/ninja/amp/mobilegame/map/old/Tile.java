package ninja.amp.mobilegame.map.old;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public interface Tile {

    TextureRegion getTexture();

    TextureRegion getNormal();

    boolean isSolid();

}
