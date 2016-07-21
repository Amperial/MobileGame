package ninja.amp.mobilegame.engine.gui;

/**
 * Represents something which objects can be bound to for rendering.
 *
 * @author Austin Payne
 */
public interface Anchor {

    /**
     * Gets the absolute x coordinate of the anchor.
     *
     * @return The x coordinate
     */
    float getX();

    /**
     * Gets the absolute y coordinate of the anchor.
     *
     * @return The y coordinate
     */
    float getY();

}
