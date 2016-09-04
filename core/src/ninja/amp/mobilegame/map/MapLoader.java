package ninja.amp.mobilegame.map;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import ninja.amp.mobilegame.engine.resources.ResourceHandler;

public class MapLoader {

    private int level;
    private String name;
    private int width;
    private int height;
    private JsonValue tileset;
    private int[][] foreground;
    private int[][] midground;
    private int[][] background;
    private int[][] collision;

    public MapLoader(FileHandle handle) {
        JsonValue map = new JsonReader().parse(handle);

        level = map.getInt("level");
        name = map.getString("name");

        width = map.getInt("width");
        height = map.getInt("height");

        tileset = map.get("tiles");

        foreground = parseTiles(map.getString("foreground"));
        midground = parseTiles(map.getString("midground"));
        background = parseTiles(map.getString("background"));

        collision = parseTiles(map.getString("collision"));
    }

    public int[][] parseTiles(String tiles) {
        int[][] array = new int[width][height];
        int y = height - 1;
        for (String row : tiles.split("\\r?\\n")) {
            int x = 0;
            for (String tile : row.split(",")) {
                array[x][y] = Integer.parseInt(tile);
                x++;
            }
            y--;
        }
        return array;
    }

    public Map loadMap(ResourceHandler handler) {
        Map map = new Map(level, name, width, height);

        map.getTileset().loadTileset(tileset, handler);

        map.getForeground().loadTiles(foreground, width, height);
        map.getMidground().loadTiles(midground, width, height);
        map.getBackground().loadTiles(background, width, height);

        map.getCollision().loadTiles(collision, width, height);

        return map;
    }

}
