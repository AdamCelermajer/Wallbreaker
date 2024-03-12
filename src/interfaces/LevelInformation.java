// 332638592 Adam Celermajer
package interfaces;
import geometry.Velocity;
import game.Block;

import java.awt.Color;
import java.util.List;



/**
 * The LevelInformation interface represents the information for a game level.
 * It provides methods to retrieve various attributes of the level,
 * such as the number of balls, paddle speed, and blocks configuration.
 */
public interface LevelInformation {
    /**
     * Returns the number of balls in the level.
     *
     * @return the number of balls
     */
    int numberOfBalls();

    /**
     * Returns the initial velocities of the balls.
     * Note that the size of the returned list must be equal to the number of balls.
     *
     * @return a list of initial ball velocities
     */
    List<Velocity> initialBallVelocities();

    /**
     * Returns the speed of the paddle.
     *
     * @return the paddle speed
     */
    int paddleSpeed();

    /**
     * Returns the width of the paddle.
     *
     * @return the paddle width
     */
    int paddleWidth();

    /**
     * Returns the name of the level.
     *
     * @return the level name
     */
    String levelName();

    /**
     * Returns the background sprite of the level.
     *
     * @return the background sprite
     */
    Sprite getBackground();

    /**
     * Returns the blocks that make up the level.
     *
     * @return a list of blocks
     */
    List<Block> blocks();

    /**
     * Returns the number of blocks that should be removed before the level is considered "cleared".
     * This number should be less than or equal to the total number of blocks.
     *
     * @return the number of blocks to remove
     */
    int numberOfBlocksToRemove();

    /**
     * Returns the color of the paddle.
     *
     * @return the paddle color
     */
    Color getPaddleColor();
}
