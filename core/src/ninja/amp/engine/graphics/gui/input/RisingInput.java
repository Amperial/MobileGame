package ninja.amp.engine.graphics.gui.input;

public class RisingInput implements Input {

    private Input input;
    private boolean previous = false;

    public RisingInput(Input input) {
        this.input = input;
    }

    @Override
    public boolean getInput() {
        if (input.getInput() != previous) {
            previous = !previous;
            return previous;
        }
        return false;
    }

}
