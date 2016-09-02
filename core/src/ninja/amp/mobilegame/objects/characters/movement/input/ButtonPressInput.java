package ninja.amp.mobilegame.objects.characters.movement.input;

import ninja.amp.mobilegame.engine.gui.buttons.Button;

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
