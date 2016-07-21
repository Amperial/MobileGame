package ninja.amp.mobilegame.engine.resources.texture;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ninja.amp.mobilegame.engine.resources.ResourceHandler;

public class SingleTexture implements Texture {

    private com.badlogic.gdx.graphics.Texture texture;
    private TextureRegion region;

    public SingleTexture(FileHandle file, ResourceHandler handler) {
        this.texture = new com.badlogic.gdx.graphics.Texture(file);
        this.region = new TextureRegion(texture);

        handler.attachResource(this);
    }

    @Override
    public TextureRegion getRegion() {
        return region;
    }

    @Override
    public void dispose() {
        texture.dispose();
        texture = null;
        region = null;
    }

}
