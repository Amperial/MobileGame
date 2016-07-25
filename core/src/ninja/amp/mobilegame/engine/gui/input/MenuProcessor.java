package ninja.amp.mobilegame.engine.gui.input;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import ninja.amp.mobilegame.engine.gui.buttons.Button;

import java.util.Collection;

public abstract class MenuProcessor implements InputProcessor {

    public abstract Collection<Button> buttons();

    @Override
    public boolean keyDown(int keycode) {
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        boolean processed = false;
        for (Button b : buttons()) {
            if (b.contains(screenX, Gdx.graphics.getHeight() - screenY)) {
                if (!b.isPressed()) {
                    b.setPressed(pointer);
                    processed = true;
                }
                if (!b.isHovered()) {
                    b.setHovered(pointer);
                    processed = true;
                }
            }
        }
        return processed;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        boolean processed = false;
        for (Button b : buttons()) {
            if (b.isPressed(pointer)) {
                b.setPressed(-1);
                if (b.contains(screenX, Gdx.graphics.getHeight() - screenY)) {
                    b.click();
                    processed = true;
                }
            }
            if (b.isHovered(pointer)) {
                b.setHovered(-1);
                processed = true;
            }
        }
        return processed;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        boolean processed = false;
        for (Button b : buttons()) {
            if (b.contains(screenX, Gdx.graphics.getHeight() - screenY)) {
                if (!b.isHovered()) {
                    b.setHovered(pointer);
                    processed = true;
                }
            } else if (b.isHovered(pointer)) {
                b.setHovered(-1);
                processed = true;
            }
        }
        return processed;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(int amount) {
        return false;
    }

}
