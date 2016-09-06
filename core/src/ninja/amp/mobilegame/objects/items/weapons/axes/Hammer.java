package ninja.amp.mobilegame.objects.items.weapons.axes;

import ninja.amp.mobilegame.engine.graphics.atlas.Atlas;
import ninja.amp.mobilegame.engine.graphics.RegionTexture;
import ninja.amp.mobilegame.engine.graphics.atlas.GameAtlas;
import ninja.amp.mobilegame.engine.physics.mass.Mass;
import ninja.amp.mobilegame.objects.body.pose.position.StaticPosition;
import ninja.amp.mobilegame.objects.characters.stats.Stat;
import ninja.amp.mobilegame.screens.Screen;

public class Hammer extends Axe {

    public Hammer(String name, Mass mass, Screen screen) {
        super(name, mass, new RegionTexture(new Atlas(GameAtlas.ITEMS, screen).findRegion("weapons/axes/hammer"), screen), new StaticPosition(0, 0, 7f / 24f, 5.5f / 24f, 14f / 24f, 30f / 24f, 90f));
    }

    @Override
    public int contribute(Stat stat) {
        return 0;
    }

}
