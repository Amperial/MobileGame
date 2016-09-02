package ninja.amp.mobilegame.map.tiles;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

public class NeighborTile implements Tile {

    private Tile[] tiles = new Tile[16];

    public NeighborTile() {

    }

    @Override
    public TextureRegion getTexture() {
        return null;
    }

    private int tileIndex(boolean above, boolean below, boolean left, boolean right) {
        int index = 0;
        if (above) index += 1;
        if (left) index += 2;
        if (below) index += 4;
        if (right) index += 8;
        return index;
    }

}
