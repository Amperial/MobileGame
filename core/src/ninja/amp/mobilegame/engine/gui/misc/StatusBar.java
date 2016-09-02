package ninja.amp.mobilegame.engine.gui.misc;

import ninja.amp.mobilegame.engine.graphics.Texture;
import ninja.amp.mobilegame.engine.gui.Anchor;
import ninja.amp.mobilegame.engine.gui.Object;
import ninja.amp.mobilegame.engine.gui.Offset;
import ninja.amp.mobilegame.engine.gui.Origin;

public abstract class StatusBar extends Object {

    public StatusBar(Texture texture, Anchor anchor, Origin origin, Offset offset) {
        super(texture, anchor, origin, offset);
    }

    public abstract float fillPercent();

}
