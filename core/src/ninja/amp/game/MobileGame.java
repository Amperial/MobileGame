package ninja.amp.game;

import ninja.amp.engine.Game;
import ninja.amp.game.screens.home.HomeScreen;

public class MobileGame extends Game {

    @Override
    public void create() {
        super.create();

        setScreen(new HomeScreen(this));
    }

    @Override
    public void render() {
        super.render();
    }

}
