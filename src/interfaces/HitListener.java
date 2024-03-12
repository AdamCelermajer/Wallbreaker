// 332638592 Adam Celermajer
package interfaces;

import geometry.Ball;
import game.Block;
/**
 * The interfaces.HitListener interface represents an object that listens for hit events.
 */
public interface HitListener {
    /**
     * This method is called whenever the beingHit object is hit.
     *
     * @param beingHit the game.Block object that is being hit
     * @param hitter   the geometry.Ball object that is doing the hitting
     */
    void hitEvent(Block beingHit, Ball hitter);
}