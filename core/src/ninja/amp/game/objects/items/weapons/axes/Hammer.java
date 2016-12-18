package ninja.amp.game.objects.items.weapons.axes;

import ninja.amp.engine.graphics.gui.screens.Screen;
import ninja.amp.engine.graphics.textures.RegionTexture;
import ninja.amp.engine.graphics.textures.atlas.Atlas;
import ninja.amp.engine.graphics.textures.atlas.GameAtlas;
import ninja.amp.engine.objects.body.pose.position.StaticPosition;
import ninja.amp.engine.objects.entities.stats.Stat;
import ninja.amp.engine.physics.mass.Mass;

public class Hammer extends Axe {

    public Hammer(String name, Mass mass, Screen screen) {
        super(name, mass, new RegionTexture(new Atlas(GameAtlas.ITEMS, screen).findRegion("weapons/axes/hammer"), screen), new StaticPosition(0, 0, 7f / 24f, 5.5f / 24f, 14f / 24f, 30f / 24f, 90f));
    }

    @Override
    public int contribute(Stat stat) {
        return 0;
    }

}
