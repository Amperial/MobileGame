package ninja.amp.mobilegame.engine.gui.menus;

import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Batch;
import ninja.amp.mobilegame.engine.gui.Object;
import ninja.amp.mobilegame.engine.gui.buttons.Button;
import ninja.amp.mobilegame.engine.gui.input.MenuProcessor;
import ninja.amp.mobilegame.engine.transitions.Transition;
import ninja.amp.mobilegame.engine.transitions.Transitionable;
import ninja.amp.mobilegame.screens.Screen;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class Menu implements Transitionable {

    private Screen screen;
    private List<Object> objects = new ArrayList<Object>();
    private List<Button> buttons = new ArrayList<Button>();

    private MenuProcessor processor = new MenuProcessor() {
        @Override
        public Collection<Button> buttons() {
            return buttons;
        }
    };

    private Transition transition = null;

    public Menu(Screen screen) {
        this.screen = screen;
    }

    public void addObjects(Object... objects) {
        Collections.addAll(this.objects, objects);
    }

    public void addButtons(Button... buttons) {
        Collections.addAll(this.buttons, buttons);
    }

    public void draw(Batch batch, float delta) {
        if (hasTransition()) {
            transition.update(delta);
        }
        for (Object object : objects) {
            object.draw(batch, delta);
        }
        for (Button button : buttons) {
            button.draw(batch, delta);
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

    @Override
    public boolean hasTransition() {
        return transition != null;
    }

    public Transition getTransition() {
        return transition;
    }

    public void setTransition(Transition transition) {
        if (hasTransition() && this.transition.inTransition()) {
            this.transition.end();
        }
        this.transition = transition;
    }

}
