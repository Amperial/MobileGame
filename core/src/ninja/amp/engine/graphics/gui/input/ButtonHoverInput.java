package ninja.amp.engine.graphics.gui.input;

import ninja.amp.engine.graphics.gui.buttons.Button;

public class ButtonHoverInput implements Input {

    private Button button;

    public ButtonHoverInput(Button button) {
        this.button = button;
    }

    @Override
    public boolean getInput() {
        return button.isHovered();
    }

}
