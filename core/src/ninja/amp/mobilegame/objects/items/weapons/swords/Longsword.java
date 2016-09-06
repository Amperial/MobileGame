package ninja.amp.mobilegame.objects.items.weapons.swords;

import ninja.amp.mobilegame.engine.graphics.atlas.Atlas;
import ninja.amp.mobilegame.engine.graphics.RegionTexture;
import ninja.amp.mobilegame.engine.graphics.atlas.GameAtlas;
import ninja.amp.mobilegame.engine.physics.mass.Mass;
import ninja.amp.mobilegame.objects.body.pose.position.StaticPosition;
import ninja.amp.mobilegame.objects.characters.stats.Stat;
import ninja.amp.mobilegame.screens.Screen;

public class Longsword extends Sword {

    public Longsword(String name, Mass mass, Screen screen) {
        super(name, mass, new RegionTexture(new Atlas(GameAtlas.ITEMS, screen).findRegion("weapons/swords/longsword"), screen), new StaticPosition(0, 0, 4f / 24f, 4.5f / 24f, 7f / 24f, 32f / 24f, 90f));
    }

    @Override
    public int contribute(Stat stat) {
        return 0;
    }

}
