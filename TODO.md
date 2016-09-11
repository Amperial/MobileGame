Menu out transitions
Settings menu
Play menu
Particle system
Variable jump height

CHARACTER MODEL AND ANIMATION SYSTEM
-character interpolates between various positions
-positions can be different depending on weapon held
-idle, running, jumping, falling, charging weapon/attacking

WEAPON CHARGING:
-every attack starts with the same windup
-attack windup starts immediately upon pressing attack button
-attack used is determined by double pressing or holding attack button during windup

TILES:
Varied Tile
Dynamic surfaces

ABSTRACT THE SCALE SYSTEM

Save game state in pause, restore game state in create

TEXT RENDERING SYSTEM
DIALOG BOXES
SCROLLING TEXT

resume progress on item and stats systems for use with character menu and other gameplay

CHARACTER MENU
==============
character view shows character in idle animation wielding currently equipped weapon
item view shows selected item
equipping an item updates the character view, as well as playing an attack animation with the weapon before returning to idle
ADD "EQUIPPED" BUTTON TO GO ALONG WITH UNLOCK AND EQUIP BUTTONS
==============

work on currency system and unlock system

work on character system itself, different state animations, weapon wielding/usage - can prototype in character menu

work on tile map system and collision handling with entities
-give entities custom hitboxes with tiles

work on map editor and saving/loading

later:
persistence with google play
achievements
in-app purchases store

-npcs have white eyes when peaceful/neutral and red eyes when hostile
    -have a certain enemy that triggers in groups at the same time