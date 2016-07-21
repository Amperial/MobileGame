package ninja.amp.mobilegame.engine.gui;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Batch;
import ninja.amp.mobilegame.engine.gui.buttons.Button;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class Menu implements InputProcessor {

    private Set<Button> buttons = new HashSet<Button>();

    public Menu(Button... buttons) {
        this.buttons.addAll(Arrays.asList(buttons));
    }

    public void draw(Batch batch) {
        for (Button button : buttons) {
            button.draw(batch);
        }
    }

    public void setScale(float scale) {
        for (Button button : buttons) {
            button.setScale(scale);
        }
    }

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
        for (Button b : buttons) {
            if (b.contains(screenX, Gdx.graphics.getHeight() - screenY)) {
                if (!b.isPressed()) {
                    b.setPressed(pointer);
                }
                if (!b.isHovered()) {
                    b.setHovered(pointer);
                }
            }
        }
        return true;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        for (Button b : buttons) {
            if (b.isPressed(pointer)) {
                b.setPressed(-1);
                if (b.contains(screenX, Gdx.graphics.getHeight() - screenY)) {
                    b.click();
                }
            }
            if (b.isHovered(pointer)) {
                b.setHovered(-1);
            }
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        for (Button b : buttons) {
            if (b.contains(screenX, Gdx.graphics.getHeight() - screenY)) {
                if (!b.isHovered()) {
                    b.setHovered(pointer);
                }
            } else if (b.isHovered(pointer)) {
                b.setHovered(-1);
            }
        }
        return true;
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
