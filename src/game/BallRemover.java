// 332638592 Adam Celermajer
package game;

import geometry.Ball;
import interfaces.HitListener;

/**
 * The game.BallRemover class is responsible for removing blocks from the game and keeping count
 * of the number of blocks that remain.
 */
public class BallRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBalls;

    /**
     * Constructs a new game.BallRemover object.
     *
     * @param game         the game.Game object
     * @param removedBalls the game.Counter object to keep track of the remaining balls
     */
    public BallRemover(GameLevel game, Counter removedBalls) {
        this.game = game;
        this.remainingBalls = removedBalls;
    }

    /**
     * Returns the game.Game object associated with this game.BallRemover.
     *
     * @return the game.Game object
     */
    public GameLevel getGame() {
        return game;
    }

    /**
     * Sets the game.Game object associated with this game.BallRemover.
     *
     * @param game the game.Game object to set
     */
    public void setGame(GameLevel game) {
        this.game = game;
    }

    /**
     * Returns the game.Counter object representing the remaining balls.
     *
     * @return the game.Counter object for the remaining balls
     */
    public Counter getRemainingBalls() {
        return remainingBalls;
    }

    /**
     * Sets the game.Counter object representing the remaining balls.
     *
     * @param remainingBalls the game.Counter object to set for the remaining balls
     */
    public void setRemainingBalls(Counter remainingBalls) {
        this.remainingBalls = remainingBalls;
    }

    @Override
    public void hitEvent(Block beingHit, Ball hitter) {
        hitter.removeFromGame(this.game);
        this.remainingBalls.decrease(1);
    }
}
