package ninja.amp.mobilegame.engine.transitions;

import com.badlogic.gdx.Gdx;
import ninja.amp.mobilegame.engine.gui.menus.Menu;

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
