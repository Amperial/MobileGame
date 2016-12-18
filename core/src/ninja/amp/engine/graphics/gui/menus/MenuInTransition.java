package ninja.amp.engine.graphics.gui.menus;

import com.badlogic.gdx.Gdx;
import ninja.amp.engine.transitions.Transition;

public class MenuInTransition extends Transition {

    private Menu menu;

    public MenuInTransition(Menu menu, float time) {
        super(menu, time);

        this.menu = menu;
    }

    @Override
    public void end() {
        super.end();

        Gdx.input.setInputProcessor(menu.getProcessor());
    }

}
