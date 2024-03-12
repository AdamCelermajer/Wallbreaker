// 332638592 Adam Celermajer
package game;

import geometry.Ball;
import interfaces.HitListener;

/**
 * The game.ScoreTrackingListener class is responsible for tracking and updating the score when a hit event occurs.
 */
public class ScoreTrackingListener implements HitListener {
    private Counter currentScore;

    /**
     * Constructs a new game.ScoreTrackingListener object with the specified scoreCounter.
     *
     * @param scoreCounter the game.Counter object representing the current score
     */
    public ScoreTrackingListener(Counter scoreCounter) {
        this.currentScore = scoreCounter;
    }

    /**
     * Updates the score when a hit event occurs.
     *
     * @param beingHit the game.Block object that was hit
     * @param hitter   the geometry.Ball object that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        currentScore.increase(5);
    }
}
