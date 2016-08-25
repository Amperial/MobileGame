BODY SYSTEM
===============
        Current position, rotation, offset, etc. that moves towards a target position, rotation, offset, etc.
        This data should be represented by a current Position object and a target Position object
        By controlling the current target Position, the body can transition between various positions smoothly independent of current position
        The speed at which the body part approaches the target position should be controlled with an elasticity parameter, like the world camera
        The target position should be based off of a float stateTime like Animation
        Through overriding the Position object, various animations should become possible.
        The body part is rendered based off of current position relative to parent current position
        Allow body to be FLIPPED by switching sign of offset, rotation, etc. - this should affect the entire body (a part is flipped if its parent is flipped)

        Allow body parts to have HITBOXES and the entire body to function as a hitbox group.
        Update the hitbox to match the position of the body part when the hitbox is checked for collision

        Target position should be determined by mapping states to positions.
        If the current state is not mapped to a position, a default position should be used
        new BodyPart(..., default position);
        Map<BodyState, Position> states
        part.setPosition(state, position);
        if (states.contains(state)) {
            target = states.get(state);
        } else {
            target = default position;
        }

        POSSIBILITY FOR EXPERIMENTATION:
        When target position is changed, allow body to assume the new position at stateTime=0 before incrementing the new target position's stateTime.
===============

Menu out transitions
Settings menu
Play menu
Particle system
Variable jump height

CHARACTER MODEL AND ANIMATION SYSTEM
-character interpolates between various positions
-positions can be different depending on weapon held
-idle, running, jumping, falling, charging weapon/attacking

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