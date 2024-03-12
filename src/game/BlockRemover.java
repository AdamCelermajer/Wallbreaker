// 332638592 Adam Celermajer
package game;

import geometry.Ball;
import interfaces.HitListener;

/**
 * The game.BlockRemover class is responsible for removing blocks from the game and keeping count
 * of the number of blocks that remain.
 */
public class BlockRemover implements HitListener {
    private GameLevel game;
    private Counter remainingBlocks;

    /**
     * Constructs a new game.BlockRemover object.
     *
     * @param game          the game.Game object
     * @param removedBlocks the game.Counter object to keep track of the remaining blocks
     */
    public BlockRemover(GameLevel game, Counter removedBlocks) {
        this.game = game;
        this.remainingBlocks = removedBlocks;
    }

    /**
     * Returns the game.Game object associated with this game.BlockRemover.
     *
     * @return the game.Game object
     */
    public GameLevel getGame() {
        return game;
    }

    /**
     * Returns the game.Counter object representing the remaining blocks.
     *
     * @return the game.Counter object for the remaining blocks
     */
    public Counter getRemainingBlocks() {
        return remainingBlocks;
    }

    /**
     * Sets the game.Game object associated with this game.BlockRemover.
     *
     * @param game the game.Game object to set
     */
    public void setGame(GameLevel game) {
        this.game = game;
    }

    /**
     * Sets the game.Counter object representing the remaining blocks.
     *
     * @param remainingBlocks the game.Counter object to set for the remaining blocks
     */
    public void setRemainingBlocks(Counter remainingBlocks) {
        this.remainingBlocks = remainingBlocks;
    }

    /**
     * Handles the hit event when a block is hit by a ball.
     * Removes the block from the game and decreases the remaining blocks count by 1.
     * Also removes this listener from the block being removed from the game.
     *
     * @param beingHit the game.Block object being hit
     * @param hitter   the geometry.Ball object that hit the block
     */
    public void hitEvent(Block beingHit, Ball hitter) {
        beingHit.removeFromGame(this.game);
        this.remainingBlocks.decrease(1);
    }
}
