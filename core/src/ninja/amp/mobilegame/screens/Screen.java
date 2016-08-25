package ninja.amp.mobilegame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Batch;
import ninja.amp.mobilegame.MobileGame;
import ninja.amp.mobilegame.engine.gui.menus.Menu;
import ninja.amp.mobilegame.engine.gui.popups.Popup;
import ninja.amp.mobilegame.engine.physics.vectors.limits.Limit;
import ninja.amp.mobilegame.engine.resources.ResourceHandler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

public abstract class Screen extends ResourceHandler implements com.badlogic.gdx.Screen {

    protected MobileGame game;
    protected ScreenCamera camera;

    protected Menu activeMenu;
    protected HashMap<String, Menu> menus = new HashMap<String, Menu>();
    protected List<Popup> popups = new ArrayList<Popup>();

    public Screen(MobileGame game) {
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
        //transition out activeMenu
        activeMenu = menu;
        //transition in activeMenu
        if (!menu.hasTransition()) {
            Gdx.input.setInputProcessor(menu.getProcessor());
        }
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
        camera.apply(batch);
        batch.begin();

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

        batch.end();
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
