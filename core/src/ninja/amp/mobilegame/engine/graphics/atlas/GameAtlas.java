package ninja.amp.mobilegame.engine.graphics.atlas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public enum GameAtlas {
    GUI("gui"),
    TILES("tiles"),
    ENTITIES("entities"),
    ITEMS("items");

    private String fileName;

    GameAtlas(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }

    public FileHandle getFile() {
        return Gdx.files.internal(fileName + ".atlas");
    }

}
