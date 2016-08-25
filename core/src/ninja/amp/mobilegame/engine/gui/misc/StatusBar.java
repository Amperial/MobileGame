package ninja.amp.mobilegame.engine.gui.misc;

import ninja.amp.mobilegame.engine.gui.Anchor;
import ninja.amp.mobilegame.engine.gui.Object;
import ninja.amp.mobilegame.engine.gui.Offset;
import ninja.amp.mobilegame.engine.gui.Origin;
import ninja.amp.mobilegame.engine.graphics.Texture;

public abstract class StatusBar extends Object {

    public StatusBar(Texture texture, Anchor anchor, Origin origin, Offset offset) {
        super(texture, anchor, origin, offset);
    }

    public abstract float fillPercent();

}
