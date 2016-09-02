package ninja.amp.mobilegame.objects.characters.movement.input;

public class FallingInput implements Input {

    private Input input;
    private boolean previous = true;

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
