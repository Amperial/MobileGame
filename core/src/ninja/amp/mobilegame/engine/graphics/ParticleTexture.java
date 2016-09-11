package ninja.amp.mobilegame.engine.graphics;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import ninja.amp.mobilegame.engine.resources.ResourceHandler;

public class ParticleTexture implements Texture {

    private com.badlogic.gdx.graphics.Texture texture;
    private TextureRegion region;

    public ParticleTexture(Color color, ResourceHandler handler) {
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(color);
        pixmap.fill();
        texture = new com.badlogic.gdx.graphics.Texture(pixmap);
        region = new TextureRegion(texture);

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
