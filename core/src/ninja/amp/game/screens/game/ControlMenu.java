package ninja.amp.game.screens.game;

import com.badlogic.gdx.Gdx;
import ninja.amp.engine.graphics.gui.Object;
import ninja.amp.engine.graphics.gui.Origin;
import ninja.amp.engine.graphics.gui.StaticOffset;
import ninja.amp.engine.graphics.gui.bars.StretchStatusBar;
import ninja.amp.engine.graphics.gui.buttons.Button;
import ninja.amp.engine.graphics.gui.buttons.HoverableButton;
import ninja.amp.engine.graphics.gui.buttons.PressableButton;
import ninja.amp.engine.graphics.gui.input.ButtonHoverInput;
import ninja.amp.engine.graphics.gui.input.ButtonPressInput;
import ninja.amp.engine.graphics.gui.input.Input;
import ninja.amp.engine.graphics.gui.input.RisingInput;
import ninja.amp.engine.graphics.gui.input.XorInput;
import ninja.amp.engine.graphics.gui.menus.Menu;
import ninja.amp.engine.graphics.gui.screens.ScreenAnchor;
import ninja.amp.engine.graphics.textures.RegionTexture;
import ninja.amp.engine.graphics.textures.Texture;
import ninja.amp.engine.graphics.textures.atlas.Atlas;

public class ControlMenu extends Menu {

    private Input left;
    private Input right;
    private Input jump;
    private Input control;

    public ControlMenu(final GameScreen screen, Atlas gui) {
        super(screen);

        Button leftButton = new HoverableButton(new RegionTexture(gui.findRegion("controls/left"), screen), new RegionTexture(gui.findRegion("controls/left_pressed"), screen), new ScreenAnchor(0, 0), Origin.BOTTOM_LEFT, new StaticOffset(1, 1));
        Button rightButton = new HoverableButton(new RegionTexture(gui.findRegion("controls/right"), screen), new RegionTexture(gui.findRegion("controls/right_pressed"), screen), leftButton, Origin.BOTTOM_LEFT, new StaticOffset(leftButton.getWidth(), 0));
        Button upButton = new PressableButton(new RegionTexture(gui.findRegion("controls/up"), screen), new RegionTexture(gui.findRegion("controls/up_pressed"), screen), new ScreenAnchor(1, 0), Origin.BOTTOM_RIGHT, new StaticOffset(-1, 1));
        Button controlButton = new PressableButton(new RegionTexture(gui.findRegion("controls/control"), screen), new RegionTexture(gui.findRegion("controls/control_pressed"), screen), upButton, Origin.BOTTOM_RIGHT, new StaticOffset(-upButton.getWidth(), 0));
        Button pauseButton = new Button(new RegionTexture(gui.findRegion("pause"), screen), new ScreenAnchor(1, 1), Origin.TOP_RIGHT, new StaticOffset(-1, -1)) {
            @Override
            public void click() {
                screen.setPaused(true);
                screen.transitionMenu(screen.getMenu("pause"), 0.2f, 0.3f);
            }
        };
        Texture stat_health = new RegionTexture(gui.findRegion("status_bar"), screen);
        Texture stat_health_fill = new RegionTexture(gui.findRegion("status_fill_red"), screen);
        Object health = new StretchStatusBar(stat_health, stat_health_fill, new ScreenAnchor(0, 1), Origin.TOP_LEFT, new StaticOffset(5, -1)) {
            float health = 0;
            float increment = 1.7f;
            int direction = 1;

            @Override
            public float fillPercent() {
                // TODO: get this from character stats
                health += (direction * increment * Gdx.graphics.getDeltaTime());
                if (health > 1) {
                    direction = -1;
                    health = 1;
                } else if (health < 0) {
                    direction = 1;
                    health = 0;
                }
                return 1f;
            }
        };
        Texture stat_stamina = new RegionTexture(gui.findRegion("status_bar"), screen);
        Texture stat_stamina_fill = new RegionTexture(gui.findRegion("status_fill_green"), screen);
        Object stamina = new StretchStatusBar(stat_stamina, stat_stamina_fill, health, Origin.TOP_LEFT, new StaticOffset(0, -health.getHeight())) {
            float stamina = 0;
            float increment = 5f;
            int direction = 1;

            @Override
            public float fillPercent() {
                // TODO: get this from character stats
                stamina += (direction * increment * Gdx.graphics.getDeltaTime());
                if (stamina > 1) {
                    direction = -1;
                    stamina = 1;
                } else if (stamina < 0) {
                    direction = 1;
                    stamina = 0;
                }
                return 0.5f;
            }
        };

        addButtons(leftButton, rightButton, upButton, controlButton, pauseButton);
        addObjects(health, stamina);

        Input left = new ButtonHoverInput(leftButton);
        Input right = new ButtonHoverInput(rightButton);

        this.left = new XorInput(left, right);
        this.right = new XorInput(right, left);
        this.jump = new RisingInput(new ButtonPressInput(upButton));
        this.control = new ButtonPressInput(controlButton);
    }

    public Input getLeftInput() {
        return left;
    }

    public Input getRightInput() {
        return right;
    }

    public Input getJumpInput() {
        return jump;
    }

    public Input getControlInput() {
        return control;
    }

}
