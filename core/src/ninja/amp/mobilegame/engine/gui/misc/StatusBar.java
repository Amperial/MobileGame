package ninja.amp.mobilegame.engine.gui.misc;

import com.badlogic.gdx.math.Vector2;
import ninja.amp.mobilegame.engine.gui.Anchor;
import ninja.amp.mobilegame.engine.gui.Object;
import ninja.amp.mobilegame.engine.gui.Origin;
import ninja.amp.mobilegame.engine.resources.texture.Texture;

public abstract class StatusBar extends Object {

    public StatusBar(Texture texture, Anchor anchor, Origin origin, Vector2 offset) {
        super(texture, anchor, origin, offset);
    }

    public abstract float fillPercent();

}
