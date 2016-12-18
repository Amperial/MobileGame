package ninja.amp.engine.graphics.gui.menus.popups;

import ninja.amp.engine.graphics.gui.Anchor;
import ninja.amp.engine.graphics.gui.Offset;
import ninja.amp.engine.graphics.gui.Origin;
import ninja.amp.engine.graphics.gui.buttons.Button;
import ninja.amp.engine.graphics.gui.screens.Screen;
import ninja.amp.engine.graphics.textures.Texture;

public class PopupCloseButton extends Button {

    private Screen screen;

    public PopupCloseButton(Screen screen, Texture texture, Anchor anchor, Origin origin, Offset offset) {
        super(texture, anchor, origin, offset);
        this.screen = screen;
    }

    @Override
    public void click() {
        screen.closePopup();
    }

}
