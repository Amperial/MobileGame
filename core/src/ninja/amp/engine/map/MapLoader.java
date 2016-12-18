package ninja.amp.engine.map;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;
import ninja.amp.engine.graphics.backgrounds.Background;
import ninja.amp.engine.graphics.backgrounds.BackgroundGroup;
import ninja.amp.engine.resources.ResourceHandler;

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

    public Background loadBackground(ResourceHandler handler) {
        Background background = new BackgroundGroup();

        // TODO: Load background
        //Background sky = new BackgroundLayer(new SingleTexture(Gdx.files.internal("background/Ocean_0.png"), this), new Vector2(0.1f, 0f), TileMode.REPEAT_X);
        //Background land = new BackgroundLayer(new SingleTexture(Gdx.files.internal("background/Ocean_1.png"), this), new Vector2(0.5f, 0.5f), new Vector2(0, -12f), TileMode.REPEAT_X);
        //Background water = new BackgroundLayer(new SingleTexture(Gdx.files.internal("background/Ocean_2.png"), this), new Vector2(1f, 0.25f), TileMode.REPEAT_X);
        //background = new BackgroundGroup(sky, land);

        return background;
    }

    public void loadEntities(World world, ResourceHandler handler) {
        // TODO: Load entities
        //world.addEntity(entity);
    }

}
