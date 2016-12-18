package ninja.amp.engine.graphics.gui.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import ninja.amp.engine.Game;
import ninja.amp.engine.graphics.gui.menus.Menu;
import ninja.amp.engine.graphics.gui.menus.MenuInTransition;
import ninja.amp.engine.graphics.gui.menus.MenuOutTransition;
import ninja.amp.engine.graphics.gui.menus.popups.Popup;
import ninja.amp.engine.graphics.shaders.FadeShader;
import ninja.amp.engine.physics.vectors.limits.Limit;
import ninja.amp.engine.resources.ResourceHandler;
import ninja.amp.engine.transitions.Transition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public abstract class Screen extends ResourceHandler implements com.badlogic.gdx.Screen {

    protected Game game;
    protected ScreenCamera camera;

    protected Menu activeMenu;
    protected HashMap<String, Menu> menus = new HashMap<String, Menu>();
    protected List<Popup> popups = new ArrayList<Popup>();

    private FadeShader fade = new FadeShader(this);

    public Screen(Game game) {
        this.game = game;

        camera = new ScreenCamera(this, Limit.VEC3);
    }

    public void addMenu(String name, Menu menu) {
        menus.put(name, menu);
    }

    public Menu getMenu(String name) {
        return menus.get(name);
    }

    public void setActiveMenu(Menu menu) {
        activeMenu = menu;
        menu.getProcessor().touchUp(-1, -1, 0, -1);
    }

    public void transitionMenu(Menu menu, float timeOut, float timeIn) {
        Gdx.input.setInputProcessor(null);
        activeMenu.setTransition(new MenuOutTransition(this, activeMenu, menu, timeOut));

        menu.setTransition(new MenuInTransition(menu, timeIn));
    }

    public boolean hasPopup() {
        return !popups.isEmpty();
    }

    public void openPopup(Menu menu) {
        popups.add(new Popup(menu));
    }

    public void closePopup() {
        if (hasPopup()) {
            popups.get(popups.size() - 1).close();
            popups.remove(popups.size() - 1);
        }
    }

    public void closePopups() {
        while (hasPopup()) {
            closePopup();
        }
    }

    public void updateCamera() {
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public void draw(Batch batch, float delta) {
        // Apply transition fading
        boolean usingShader = false;
        if (activeMenu.hasTransition()) {
            Transition transition = activeMenu.getTransition();
            if (transition instanceof MenuInTransition) {
                fade.setFade(transition.current() / transition.getTime());
            } else if (transition instanceof MenuOutTransition) {
                fade.setFade(1 - (transition.current() / transition.getTime()));
            }
            fade.apply(batch);
            usingShader = true;
        }

        // Begin drawing to batch
        camera.apply(batch);
        batch.begin();

        // Draw active menu and popups to batch
        activeMenu.draw(batch, delta);
        Iterator<Popup> popupIterator = popups.iterator();
        while (popupIterator.hasNext()) {
            Popup popup = popupIterator.next();
            if (popup.isClosed()) {
                popupIterator.remove();
            } else {
                popup.draw(batch, delta);
            }
        }

        // End drawing to batch
        batch.end();

        // Remove transition fading
        if (usingShader) {
            batch.setShader(null);
        }
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
    }

    @Override
    public void dispose() {
        super.dispose();

        closePopups();
        popups = null;
        activeMenu = null;
        menus.clear();
        menus = null;
        camera = null;
        game = null;

        Gdx.input.setInputProcessor(null);
    }

}
