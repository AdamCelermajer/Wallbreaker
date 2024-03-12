// 332638592 Adam Celermajer
package interfaces;
import biuoop.DrawSurface;

/**
 * The Animation interface represents an animation that can be displayed on a DrawSurface.
 * It defines methods for updating the animation state and determining when the animation should stop.
 */
public interface Animation {

    /**
     * Performs one frame of the animation.
     *
     * @param d The DrawSurface on which the animation is drawn.
     */
    void doOneFrame(DrawSurface d);

    /**
     * Checks if the animation should stop.
     *
     * @return {@code true} if the animation should stop, {@code false} otherwise.
     */
    boolean shouldStop();
}
