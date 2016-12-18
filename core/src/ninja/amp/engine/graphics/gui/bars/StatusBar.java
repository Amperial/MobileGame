package ninja.amp.engine.graphics.gui.bars;

import ninja.amp.engine.graphics.gui.Anchor;
import ninja.amp.engine.graphics.gui.Object;
import ninja.amp.engine.graphics.gui.Offset;
import ninja.amp.engine.graphics.gui.Origin;
import ninja.amp.engine.graphics.textures.Texture;

public abstract class StatusBar extends Object {

    public StatusBar(Texture texture, Anchor anchor, Origin origin, Offset offset) {
        super(texture, anchor, origin, offset);
    }

    public abstract float fillPercent();

}
