package ninja.amp.mobilegame.engine.gui.popups;

import ninja.amp.mobilegame.engine.gui.Anchor;
import ninja.amp.mobilegame.engine.gui.Offset;
import ninja.amp.mobilegame.engine.gui.Origin;
import ninja.amp.mobilegame.engine.gui.buttons.Button;
import ninja.amp.mobilegame.engine.graphics.Texture;
import ninja.amp.mobilegame.screens.Screen;

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
