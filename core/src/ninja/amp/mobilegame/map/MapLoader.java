package ninja.amp.mobilegame.map;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

public class MapLoader {

    private int width;
    private int height;
    private int[][] foreground;
    private int[][] midground;
    private int[][] background;
    private int[][] collision;

    public MapLoader(FileHandle handle) {
        JsonValue value = new JsonReader().parse(handle);

        String tiles = value.getString("tiles");

        width = value.getInt("width");
        height = value.getInt("height");

        foreground = new int[width][height];
        midground = new int[width][height];
        background = new int[width][height];
        collision = new int[width][height];
        parseTiles(value.getString("foreground"), foreground);
        parseTiles(value.getString("midground"), midground);
        parseTiles(value.getString("background"), background);
        parseTiles(value.getString("collision"), collision);
    }

    public void parseTiles(String tiles, int[][] array) {
        int y = height - 1;
        for (String row : tiles.split("\\r?\\n")) {
            int x = 0;
            for (String tile : row.split(",")) {
                array[x][y] = Integer.parseInt(tile);
                x++;
            }
            y--;
        }
    }

    public Map loadMap() {
        Map map = new Map(width, height);

        map.getForeground().loadTiles(foreground, width, height);
        map.getMidground().loadTiles(midground, width, height);
        map.getBackground().loadTiles(background, width, height);
        map.getCollision().loadTiles(collision, width, height);

        return map;
    }

}
