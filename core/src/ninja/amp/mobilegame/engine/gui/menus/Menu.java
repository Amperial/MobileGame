package ninja.amp.mobilegame.engine.gui.menus;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Batch;
import ninja.amp.mobilegame.engine.gui.Object;
import ninja.amp.mobilegame.engine.gui.buttons.Button;
import ninja.amp.mobilegame.engine.gui.input.MenuProcessor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Menu {

    private List<Object> objects = new ArrayList<Object>();
    private List<Button> buttons = new ArrayList<Button>();

    private MenuProcessor processor = new MenuProcessor() {
        @Override
        public Collection<Button> buttons() {
            return buttons;
        }
    };

    public void addObjects(Object... objects) {
        Collections.addAll(this.objects, objects);
    }

    public void addButtons(Button... buttons) {
        Collections.addAll(this.buttons, buttons);
    }

    public void draw(Batch batch) {
        for (Object object : objects) {
            object.draw(batch);
        }
        for (Button button : buttons) {
            button.draw(batch);
        }
    }

    public void setScale(float scale) {
        for (Object object : objects) {
            object.setScale(scale);
        }
        for (Button button : buttons) {
            button.setScale(scale);
        }
    }

    public InputProcessor getProcessor() {
        return processor;
    }

}
