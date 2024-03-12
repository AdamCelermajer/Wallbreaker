// 332638592 Adam Celermajer
package game;

import biuoop.DrawSurface;
import interfaces.Animation;

import java.awt.Color;

/**
 * The PauseScreen class represents a pause screen animation.
 * It implements the Animation interface and provides methods
 * for displaying the pause screen and determining when it should stop.
 */
public class PauseScreen implements Animation {
    /**
     * Displays the pause screen on the given draw surface.
     *
     * @param d the draw surface on which to display the pause screen
     */
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLACK);
        d.drawText(10, d.getHeight() / 2, "paused -- press space to continue", 32);
    }

    /**
     * Determines whether the pause screen animation should stop.
     *
     * @return always returns false, as the pause screen should not stop until space key is pressed
     */
    public boolean shouldStop() {
        return false;
    }
}
