package ninja.amp.mobilegame.engine.gui.menus;

import com.badlogic.gdx.InputProcessor;
import ninja.amp.mobilegame.engine.gui.input.MultiProcessor;
import ninja.amp.mobilegame.screens.Screen;

public class TabMenu extends Menu {

    private TabButton defaultTab;
    private TabButton activeTab;

    private MultiProcessor processor = new MultiProcessor();

    public TabMenu(Screen screen) {
        super(screen);
    }

    public TabButton getDefault() {
        return defaultTab;
    }

    public void setDefault(TabButton tab) {
        defaultTab = tab;
    }

    public TabButton getActive() {
        return activeTab;
    }

    public void setActive(TabButton tab) {
        activeTab = tab;
        processor.setProcessors(super.getProcessor(), tab.getMenu().getProcessor());
    }

    @Override
    public InputProcessor getProcessor() {
        return processor;
    }

}
