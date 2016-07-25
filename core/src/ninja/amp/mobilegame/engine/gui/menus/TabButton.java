package ninja.amp.mobilegame.engine.gui.menus;

import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.math.Vector2;
import ninja.amp.mobilegame.engine.gui.Anchor;
import ninja.amp.mobilegame.engine.gui.Origin;
import ninja.amp.mobilegame.engine.gui.buttons.Button;
import ninja.amp.mobilegame.engine.resources.texture.Texture;

public class TabButton extends Button {

    private TabMenu tabMenu;
    private Menu menu;
    private Texture active;

    public TabButton(TabMenu tabMenu, Menu menu, Texture tab, Texture active, Anchor anchor, Origin origin, Vector2 offset) {
        super(tab, anchor, origin, offset);
        this.tabMenu = tabMenu;
        this.menu = menu;
        this.active = active;
    }

    public Menu getMenu() {
        return menu;
    }

    @Override
    public void click() {
        tabMenu.setActive(this);
    }

    @Override
    public void draw(Batch batch) {
        super.draw(batch);
        if (this.equals(tabMenu.getActive())) {
            batch.draw(active.getRegion(), getScreenX(), getScreenY(), getScreenWidth(), getScreenHeight());
            menu.draw(batch);
        }
    }

    @Override
    public void setScale(float scale) {
        super.setScale(scale);
        menu.setScale(scale);
    }

}
