package ninja.amp.engine.graphics.gui.menus;

import ninja.amp.engine.graphics.gui.screens.Screen;
import ninja.amp.engine.transitions.Transition;

public class MenuOutTransition extends Transition {

    private Screen screen;
    private Menu menu;

    public MenuOutTransition(Screen screen, Menu menu, Menu newMenu, float time) {
        super(menu, time);

        this.screen = screen;
        this.menu = newMenu;
    }

    @Override
    public void end() {
        super.end();

        screen.setActiveMenu(menu);
    }

}
