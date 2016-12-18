package ninja.amp.engine.graphics.gui.input;

import ninja.amp.engine.graphics.gui.buttons.Button;

public class ButtonPressInput implements Input {

    private Button button;

    public ButtonPressInput(Button button) {
        this.button = button;
    }

    @Override
    public boolean getInput() {
        return button.isPressed();
    }

}
