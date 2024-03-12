// 332638592 Adam Celermajer
package interfaces;

import biuoop.DrawSurface;

/**
 An interface for a game sprite that can be drawn on a DrawSurface and updated over time.
 */
public interface Sprite {

    /**
     * Draws the sprite on the given DrawSurface.
     *
     * @param d the DrawSurface on which to draw the sprite
     */
    void drawOn(DrawSurface d);

    /**
     * Notifies the sprite that time has passed.
     * This method is used to update the state of the sprite over time.
     */
    void timePassed();
}
