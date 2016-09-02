package ninja.amp.mobilegame.engine.gui.menus;

import com.badlogic.gdx.graphics.g2d.Batch;
import ninja.amp.mobilegame.engine.graphics.Texture;
import ninja.amp.mobilegame.engine.gui.Anchor;
import ninja.amp.mobilegame.engine.gui.Offset;
import ninja.amp.mobilegame.engine.gui.Origin;
import ninja.amp.mobilegame.engine.gui.buttons.Button;

public class TabButton extends Button {

    private TabMenu tabMenu;
    private Menu menu;
    private Texture active;

    public TabButton(TabMenu tabMenu, Menu menu, Texture tab, Texture active, Anchor anchor, Origin origin, Offset offset) {
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
    public void draw(Batch batch, float delta) {
        super.draw(batch, delta);
        if (this.equals(tabMenu.getActive())) {
            batch.draw(active.getRegion(), getScreenX(), getScreenY(), getScreenWidth(), getScreenHeight());
            menu.draw(batch, delta);
        }
    }

    @Override
    public void setScale(float scale) {
        super.setScale(scale);
        menu.setScale(scale);
    }

}
