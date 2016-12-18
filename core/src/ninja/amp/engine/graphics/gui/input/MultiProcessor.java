package ninja.amp.engine.graphics.gui.input;

import com.badlogic.gdx.InputProcessor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MultiProcessor implements InputProcessor {

    private List<InputProcessor> processors = new ArrayList<InputProcessor>();

    public MultiProcessor(InputProcessor... processors) {
        Collections.addAll(this.processors, processors);
    }

    public void setProcessors(InputProcessor... processors) {
        this.processors.clear();
        Collections.addAll(this.processors, processors);
    }

    @Override
    public boolean keyDown(int keycode) {
        for (InputProcessor processor : processors) {
            if (processor.keyDown(keycode)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        for (InputProcessor processor : processors) {
            if (processor.keyUp(keycode)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        for (InputProcessor processor : processors) {
            if (processor.keyTyped(character)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        for (InputProcessor processor : processors) {
            if (processor.touchDown(screenX, screenY, pointer, button)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        for (InputProcessor processor : processors) {
            if (processor.touchUp(screenX, screenY, pointer, button)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        for (InputProcessor processor : processors) {
            if (processor.touchDragged(screenX, screenY, pointer)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        for (InputProcessor processor : processors) {
            if (processor.mouseMoved(screenX, screenY)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        for (InputProcessor processor : processors) {
            if (processor.scrolled(amount)) {
                return true;
            }
        }
        return false;
    }

}
