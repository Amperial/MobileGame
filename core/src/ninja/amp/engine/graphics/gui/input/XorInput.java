package ninja.amp.engine.graphics.gui.input;

public class XorInput implements Input {

    private Input input;
    private Input counter;

    public XorInput(Input input, Input counter) {
        this.input = input;
        this.counter = counter;
    }

    @Override
    public boolean getInput() {
        return input.getInput() && !counter.getInput();
    }

}
