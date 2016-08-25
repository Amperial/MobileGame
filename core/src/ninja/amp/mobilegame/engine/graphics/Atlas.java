package ninja.amp.mobilegame.engine.graphics;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ninja.amp.mobilegame.engine.resources.Resource;
import ninja.amp.mobilegame.engine.resources.ResourceHandler;

public class Atlas implements Resource {

    private TextureAtlas atlas;

    public Atlas(FileHandle file, ResourceHandler handler) {
        this.atlas = new TextureAtlas(file);

        handler.attachResource(this);
    }

    public TextureRegion findRegion(String name) {
        return atlas.findRegion(name);
    }

    @Override
    public void dispose() {
        atlas.dispose();
        atlas = null;
    }

}
