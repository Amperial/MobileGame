package ninja.amp.game.objects.items.weapons.swords;

import ninja.amp.engine.graphics.gui.screens.Screen;
import ninja.amp.engine.graphics.textures.RegionTexture;
import ninja.amp.engine.graphics.textures.atlas.Atlas;
import ninja.amp.engine.graphics.textures.atlas.GameAtlas;
import ninja.amp.engine.objects.body.pose.position.StaticPosition;
import ninja.amp.engine.physics.mass.Mass;

public class Longsword extends Sword {

    public Longsword(String name, Mass mass, Screen screen) {
        super(name, mass, new RegionTexture(new Atlas(GameAtlas.ITEMS, screen).findRegion("weapons/swords/longsword"), screen), new StaticPosition(0, 0, 4f / 24f, 4.5f / 24f, 7f / 24f, 32f / 24f, 90f));
    }

}
