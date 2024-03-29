package ninja.amp.engine.graphics.gui.menus.popups;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Batch;
import ninja.amp.engine.graphics.gui.menus.Menu;

public class Popup {

    private Menu menu;
    private boolean closed;
    private InputProcessor previous;

    public Popup(Menu menu) {
        this.menu = menu;
        this.closed = false;
        this.previous = Gdx.input.getInputProcessor();

        Gdx.input.setInputProcessor(menu.getProcessor());
    }

    public void draw(Batch batch, float delta) {
        menu.draw(batch, delta);
    }

    public boolean isClosed() {
        return closed;
    }

    public void close() {
        closed = true;
        Gdx.input.setInputProcessor(previous);
    }

}
