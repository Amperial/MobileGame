package ninja.amp.mobilegame.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import ninja.amp.mobilegame.MobileGame;
import ninja.amp.mobilegame.engine.gui.Menu;
import ninja.amp.mobilegame.engine.resources.ResourceHandler;

import java.util.HashMap;

public abstract class Screen extends ResourceHandler implements com.badlogic.gdx.Screen {

    protected final MobileGame game;
    protected OrthographicCamera camera;

    protected Menu activeMenu;
    protected HashMap<String, Menu> menus = new HashMap<String, Menu>();

    public Screen(MobileGame game) {
        this.game = game;

        camera = new OrthographicCamera();
    }

    public void addMenu(String name, Menu menu) {
        menus.put(name, menu);
    }

    public void setActiveMenu(Menu menu) {
        activeMenu = menu;
        Gdx.input.setInputProcessor(menu);
    }

    public void updateCamera() {
        resize(Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public void resize(int width, int height) {
        camera.setToOrtho(false, width, height);
    }

    @Override
    public void dispose() {
        super.dispose();

        camera = null;
        activeMenu = null;
        menus.clear();
        menus = null;
    }

}
