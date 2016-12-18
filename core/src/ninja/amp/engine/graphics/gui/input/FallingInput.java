package ninja.amp.engine.graphics.gui.input;

public class FallingInput implements Input {

    private Input input;
    private boolean previous = false;

    public FallingInput(Input input) {
        this.input = input;
    }

    @Override
    public boolean getInput() {
        if (input.getInput() != previous) {
            previous = !previous;
            return !previous;
        }
        return false;
    }

}
