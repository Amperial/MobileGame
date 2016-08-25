package ninja.amp.mobilegame.objects.items.weapons.swords;

import com.badlogic.gdx.Gdx;
import ninja.amp.mobilegame.engine.graphics.Atlas;
import ninja.amp.mobilegame.engine.graphics.RegionTexture;
import ninja.amp.mobilegame.engine.physics.mass.Mass;
import ninja.amp.mobilegame.objects.characters.movement.Position;
import ninja.amp.mobilegame.objects.characters.stats.Stat;
import ninja.amp.mobilegame.screens.Screen;

public class Longsword extends Sword {

    public Longsword(String name, Mass mass, Screen screen) {
        super(name, mass, new RegionTexture(new Atlas(Gdx.files.internal("items.pack"), screen).findRegion("weapons/swords/longsword"), screen), new Position(null, 0, 0, 4f/32f, 5f/32f, 7f/32f, 1f, 0) {
            @Override
            public float getRotation() {
                return 90f;
            }
        });
    }

    @Override
    public int contribute(Stat stat) {
        return 0;
    }

}
