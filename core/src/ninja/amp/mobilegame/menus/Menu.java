package ninja.amp.mobilegame.menus;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.graphics.g2d.Batch;

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
    public boolean touchDown(int screenX, int screenY, int pointer, int b) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int b) {
        for (Button button : buttons) {
            if (button.contains(screenX, Gdx.graphics.getHeight() - screenY)) {
                button.click();
            }
        }
        return true;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
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
