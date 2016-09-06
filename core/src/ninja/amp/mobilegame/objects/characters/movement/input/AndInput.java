package ninja.amp.mobilegame.objects.characters.movement.input;

public class AndInput implements Input {

    private Input[] inputs;

    public AndInput(Input... inputs) {
        this.inputs = inputs;
    }

    @Override
    public boolean getInput() {
        for (Input input : inputs) {
            if (!input.getInput()) {
                return false;
            }
        }
        return true;
    }

}
