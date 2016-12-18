package ninja.amp.engine.graphics.textures.atlas;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

public enum GameAtlas {
    ENTITIES("entities"),
    GUI("gui"),
    ITEMS("items"),
    PARTICLES("particles"),
    TILES("tiles");

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
