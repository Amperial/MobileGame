package ninja.amp.mobilegame.map.tiles;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ninja.amp.mobilegame.engine.graphics.Texture;

public class StaticTile implements Tile {

    private Texture texture;

    public StaticTile(Texture texture) {
        this.texture = texture;
    }

    @Override
    public TextureRegion getTexture() {
        return texture.getRegion();
    }

}
