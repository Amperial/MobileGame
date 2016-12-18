package ninja.amp.game.screens.game;

import com.badlogic.gdx.Gdx;
import ninja.amp.engine.graphics.gui.Object;
import ninja.amp.engine.graphics.gui.Origin;
import ninja.amp.engine.graphics.gui.StaticOffset;
import ninja.amp.engine.graphics.gui.bars.TileStatusBar;
import ninja.amp.engine.graphics.gui.buttons.Button;
import ninja.amp.engine.graphics.gui.buttons.PressableButton;
import ninja.amp.engine.graphics.gui.menus.Menu;
import ninja.amp.engine.graphics.gui.menus.TabButton;
import ninja.amp.engine.graphics.gui.menus.TabMenu;
import ninja.amp.engine.graphics.gui.screens.Screen;
import ninja.amp.engine.graphics.gui.screens.ScreenAnchor;
import ninja.amp.engine.graphics.textures.RegionTexture;
import ninja.amp.engine.graphics.textures.Texture;
import ninja.amp.engine.graphics.textures.atlas.Atlas;
import ninja.amp.engine.graphics.textures.atlas.GameAtlas;
import ninja.amp.engine.resources.audio.Sound;
import ninja.amp.game.MobileGame;

public class CharacterMenu extends TabMenu {

    public CharacterMenu(final MobileGame game, final Screen screen) {
        super(screen);

        Atlas gui = new Atlas(GameAtlas.GUI, screen);
        final Sound sound = new Sound(Gdx.files.internal("sound/button_click.wav"), game.options, screen);

        // Popup

        Object popup = new Object(new RegionTexture(gui.findRegion("menus/popup_medium"), screen), new ScreenAnchor(0.5f, 0.5f), Origin.CENTER);
        Texture close = new RegionTexture(gui.findRegion("menus/popup_close"), screen);
        Texture small_pressed = new RegionTexture(gui.findRegion("buttons/small_pressed"), screen);
        Button popup_close = new PressableButton(close, small_pressed, popup, Origin.BOTTOM_LEFT, new StaticOffset(107.5f, 51f)) {
            @Override
            public void click() {
                screen.closePopup();
                setActive(getDefault());
            }
        };
        Object character = new Object(new RegionTexture(gui.findRegion("menus/character/character_view"), screen), popup, Origin.BOTTOM_RIGHT, new StaticOffset(-44.5f, -50));
        // TODO: Render character entity on top of "character" object
        Object bar = new Object(new RegionTexture(gui.findRegion("menus/bar_horizontal"), screen), popup, Origin.TOP_RIGHT, new StaticOffset(91.5f, 33));

        // Items

        Button previous_item = new Button(new RegionTexture(gui.findRegion("menus/character/item_previous"), screen), character, Origin.BOTTOM_LEFT, new StaticOffset(-1, 2)) {
            @Override
            public void click() {
                // TODO: Scroll viewed item to left
                sound.play();
            }
        };
        Object items = new Object(new RegionTexture(gui.findRegion("menus/character/item_list"), screen), previous_item, Origin.BOTTOM_LEFT, new StaticOffset(previous_item.getWidth() - 1, -2));
        // TODO: Render item list on top of "items" object
        // TODO: Render "menus/character/item_highlight" on top of viewed item in item list
        Button next_item = new Button(new RegionTexture(gui.findRegion("menus/character/item_next"), screen), items, Origin.BOTTOM_LEFT, new StaticOffset(items.getWidth() - 1, 2)) {
            @Override
            public void click() {
                // TODO: Scroll viewed item to right
                sound.play();
            }
        };
        Object item = new Object(new RegionTexture(gui.findRegion("menus/character/item_view"), screen), items, Origin.BOTTOM_LEFT, new StaticOffset(0, items.getHeight() + 1));
        // TODO: Render viewed item on top of "item" object
        // TODO: Unlock, equip, and equipped should all be 1 button
        Button unlock = new Button(new RegionTexture(gui.findRegion("menus/character/item_unlock"), screen), item, Origin.BOTTOM_LEFT, new StaticOffset(item.getWidth() - 1, 0)) {
            @Override
            public void click() {
                // TODO: Unlock viewed item
                sound.play();
            }
        };
        Button equip = new Button(new RegionTexture(gui.findRegion("menus/character/item_equip"), screen), item, Origin.BOTTOM_LEFT, new StaticOffset(item.getWidth() - 1, 0)) {
            @Override
            public void click() {
                // TODO: Equip viewed item
                sound.play();
            }
        };

        // Stats Tab

        Menu statsMenu = new Menu(screen);
        // TODO: Render character info text (name, level, etc.) above bar
        Texture stat_health = new RegionTexture(gui.findRegion("menus/character/stat_health"), screen);
        Texture stat_health_fill = new RegionTexture(gui.findRegion("menus/character/stat_health_fill"), screen);
        Object health = new TileStatusBar(stat_health, stat_health_fill, bar, Origin.TOP_RIGHT, new StaticOffset(-23, -16)) {
            @Override
            public float fillPercent() {
                int health = 4; // TODO: get this from character stats
                return health / 10f;
            }
        };
        Texture stat_protection = new RegionTexture(gui.findRegion("menus/character/stat_protection"), screen);
        Texture stat_protection_fill = new RegionTexture(gui.findRegion("menus/character/stat_protection_fill"), screen);
        Object protection = new TileStatusBar(stat_protection, stat_protection_fill, health, Origin.TOP_RIGHT, new StaticOffset(0, -16)) {
            @Override
            public float fillPercent() {
                int protection = 2; // TODO: get this from character stats
                return protection / 10f;
            }
        };
        Texture stat_strength = new RegionTexture(gui.findRegion("menus/character/stat_strength"), screen);
        Texture stat_strength_fill = new RegionTexture(gui.findRegion("menus/character/stat_strength_fill"), screen);
        Object strength = new TileStatusBar(stat_strength, stat_strength_fill, protection, Origin.TOP_RIGHT, new StaticOffset(0, -16)) {
            @Override
            public float fillPercent() {
                int strength = 10; // TODO: get this from character stats
                return strength / 10f;
            }
        };
        Texture stat_increase = new RegionTexture(gui.findRegion("menus/character/stat_increase"), screen);
        Button health_increase = new Button(stat_increase, health, Origin.TOP_RIGHT, new StaticOffset(16, 0)) {
            @Override
            public void click() {
                // TODO: Increase health stat
                sound.play();
            }
        };
        Button protection_increase = new Button(stat_increase, protection, Origin.TOP_RIGHT, new StaticOffset(16, 0)) {
            @Override
            public void click() {
                // TODO: Increase protection stat
                sound.play();
            }
        };
        Button strength_increase = new Button(stat_increase, strength, Origin.TOP_RIGHT, new StaticOffset(16, 0)) {
            @Override
            public void click() {
                // TODO: Increase strength stat
                sound.play();
            }
        };
        statsMenu.addObjects(health, protection, strength);
        statsMenu.addButtons(health_increase, protection_increase, strength_increase);

        // Combat Tab

        Menu combatMenu = new Menu(screen);
        // TODO: Render viewed item info text (name, type, level) above bar
        Object range = new Object(new RegionTexture(gui.findRegion("menus/character/combat_range"), screen), equip, Origin.BOTTOM_LEFT, new StaticOffset(0, equip.getHeight() - 1));
        // TODO: Render viewed item range on top of "range" object
        Object speed = new Object(new RegionTexture(gui.findRegion("menus/character/combat_speed"), screen), range, Origin.BOTTOM_LEFT, new StaticOffset(0, range.getHeight() - 1));
        // TODO: Render viewed item speed on top of "speed" object
        Object damage = new Object(new RegionTexture(gui.findRegion("menus/character/combat_damage"), screen), speed, Origin.BOTTOM_LEFT, new StaticOffset(0, speed.getHeight() - 1));
        // TODO: Render viewed item damage on top of "damage" object
        combatMenu.addObjects(items, item, range, speed, damage, bar);
        combatMenu.addButtons(previous_item, next_item, equip);

        // Armor Tab

        Menu armorMenu = new Menu(screen);
        // TODO: Render viewed item info text (name, type, level) above bar
        Object resist = new Object(new RegionTexture(gui.findRegion("menus/character/armor_resist"), screen), equip, Origin.BOTTOM_LEFT, new StaticOffset(0, equip.getHeight() - 1));
        // TODO: Render viewed item resist on top of "resist" object
        Object weight = new Object(new RegionTexture(gui.findRegion("menus/character/armor_weight"), screen), resist, Origin.BOTTOM_LEFT, new StaticOffset(0, resist.getHeight() - 1));
        // TODO: Render viewed item weight on top of "weight" object
        Object defense = new Object(new RegionTexture(gui.findRegion("menus/character/armor_defense"), screen), weight, Origin.BOTTOM_LEFT, new StaticOffset(0, weight.getHeight() - 1));
        // TODO: Render viewed item defense on top of "defense" object
        armorMenu.addObjects(items, item, resist, weight, defense, bar);
        armorMenu.addButtons(previous_item, next_item, equip);

        // Special Tab

        Menu specialMenu = new Menu(screen);
        // TODO: Render viewed item info text (name, type) above bar
        Object effects = new Object(new RegionTexture(gui.findRegion("menus/character/special_effects"), screen), equip, Origin.BOTTOM_LEFT, new StaticOffset(0, equip.getHeight() - 1));
        // TODO: Render viewed item effects on top of "effects" object
        specialMenu.addObjects(items, item, effects, bar);
        specialMenu.addButtons(previous_item, next_item, equip);

        // Final Setup

        Texture tab_highlight = new RegionTexture(gui.findRegion("buttons/large_pressed"), screen);
        TabButton stats = new TabButton(this, statsMenu, new RegionTexture(gui.findRegion("menus/character/tab_stats"), screen), tab_highlight, popup, Origin.BOTTOM_LEFT, new StaticOffset(-100.5f, 51f)) {
            @Override
            public void click() {
                sound.play();
                super.click();
            }
        };
        TabButton combat = new TabButton(this, combatMenu, new RegionTexture(gui.findRegion("menus/character/tab_combat"), screen), tab_highlight, stats, Origin.BOTTOM_LEFT, new StaticOffset(51, 0)) {
            @Override
            public void click() {
                sound.play();
                super.click();
            }
        };
        TabButton armor = new TabButton(this, armorMenu, new RegionTexture(gui.findRegion("menus/character/tab_armor"), screen), tab_highlight, combat, Origin.BOTTOM_LEFT, new StaticOffset(51, 0)) {
            @Override
            public void click() {
                sound.play();
                super.click();
            }
        };
        TabButton special = new TabButton(this, specialMenu, new RegionTexture(gui.findRegion("menus/character/tab_special"), screen), tab_highlight, armor, Origin.BOTTOM_LEFT, new StaticOffset(51, 0)) {
            @Override
            public void click() {
                sound.play();
                super.click();
            }
        };

        addObjects(popup, character, bar);
        addButtons(popup_close, stats, combat, armor, special);

        setDefault(stats);
        setActive(stats);
    }

}
