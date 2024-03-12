//332638592 Adam Celermajer
package level;

import game.Block;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import interfaces.LevelInformation;
import interfaces.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

import level.Back.WideEasyBack;

/**
 * The DirectHit class implements the LevelInformation interface and represents a specific level called "Direct Hit".
 * It provides information about the number of balls, paddle properties, level name, background, blocks, and more.
 */
public class DirectHit implements LevelInformation {

    private int numOfBalls;
    private Color paddleColor;
    private List<Velocity> initialBallVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite background;
    private List<Block> blocks;
    private int numberOfBlocksToRemove;

    /**
     * Constructs a DirectHit object.
     * It initializes the properties of the level, including the number of balls, paddle properties,
     * level name, background, blocks, and more.
     */
    public DirectHit() {
        // Initialize the background
        this.background = new WideEasyBack();

        // Set the number of blocks to remove to win the level
        this.numberOfBlocksToRemove = 1;

        // Set the number of balls
        this.numOfBalls = 1;

        // Set the paddle speed and width
        this.paddleSpeed = 10;
        this.paddleWidth = 85;

        // Set the level name
        this.levelName = "Direct Hit";

        // Initialize the list of blocks
        this.blocks = new ArrayList<>();

        // Initialize the list of initial ball velocities
        this.initialBallVelocities = new ArrayList<>(numOfBalls);
        this.initialBallVelocities.add(new Velocity(0, -2));

        // Set the paddle color
        this.paddleColor = Color.ORANGE;

        // Create the target block
        int targetX = 375;
        int targetY = 275;
        int targetWidth = 50;
        int targetHeight = 30;
        Color targetColor = Color.WHITE;
        Block targetBlock = new Block(new Rectangle(
                new Point(targetX, targetY), targetWidth, targetHeight, targetColor), "null");

        // Add the target block to the list of blocks
        this.blocks.add(targetBlock);
    }

    /**
     * Initializes a row of blocks.
     *
     * @param row   an array of Block objects representing the row of blocks.
     * @param x     the x-coordinate of the leftmost block in the row.
     * @param y     the y-coordinate of the row of blocks.
     * @param color the color of the blocks in the row.
     */
    public void initRow(Block[] row, int x, int y, Color color) {
        // Method code
    }

    /**
     * Returns the number of balls in the level.
     *
     * @return the number of balls in the level.
     */
    public int numberOfBalls() {
        return numOfBalls;
    }

    /**
     * Returns the initial velocities of the balls in the level.
     *
     * @return a list of Velocity objects representing the initial velocities of the balls.
     */
    public List<Velocity> initialBallVelocities() {
        return this.initialBallVelocities;
    }

    /**
     * Returns the speed of the paddle in the level.
     *
     * @return the speed of the paddle.
     */
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    /**
     * Returns the width of the paddle in the level.
     *
     * @return the width of the paddle.
     */
    public int paddleWidth() {
        return this.paddleWidth;
    }

    /**
     * Returns the name of the level.
     *
     * @return the name of the level.
     */
    public String levelName() {
        return this.levelName;
    }

    /**
     * Returns the background sprite of the level.
     *
     * @return the background sprite.
     */
    public Sprite getBackground() {
        return this.background;
    }

    /**
     * Returns the list of blocks in the level.
     *
     * @return a list of Block objects representing the blocks in the level.
     */
    public List<Block> blocks() {
        return this.blocks;
    }

    /**
     * Returns the number of blocks that need to be removed to clear the level.
     *
     * @return the number of blocks to be removed.
     */
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }

    /**
     * Returns the color of the paddle in the level.
     *
     * @return the color of the paddle.
     */
    public Color getPaddleColor() {
        return this.paddleColor;
    }
}
