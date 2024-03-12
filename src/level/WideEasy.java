// 332638592 Adam Celermajer
package level;

import game.Block;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import interfaces.LevelInformation;
import interfaces.Sprite;
import level.Back.DirectHitBack;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;

/**
 * The WideEasy class represents a specific level called "Wide Easy".
 * It implements the LevelInformation interface and provides methods to retrieve the information about the level.
 */
public class WideEasy implements LevelInformation {
    private Color paddleColor;
    private int numOfBalls;
    private List<Velocity> initialBallVelocities;
    private int paddleSpeed;
    private int paddleWidth;
    private String levelName;
    private Sprite background;
    private List<Block> blocks;
    private int numberOfBlocksToRemove;

    /**
     * Constructs a WideEasy level with the predefined attributes.
     */
    public WideEasy() {
        this.background = new DirectHitBack();
        this.paddleColor = Color.ORANGE;
        this.numOfBalls = 10;
        this.paddleSpeed = 5;
        this.paddleWidth = 400;
        this.levelName = "Wide Easy";
        this.blocks = new ArrayList<>();
        this.initialBallVelocities = new ArrayList<>(numOfBalls);

        // Calculate the x component increment
        double xIncrement = 1;
        // Set the initial x component
        double x = -5;

        for (int i = 0; i < numOfBalls; i++) {
            // Calculate the y component (assuming it is constant)
            initialBallVelocities.add(new Velocity(x, -4));
            // Increment the x component
            x += xIncrement;
        }
        this.numberOfBlocksToRemove = 15;

        int maxWidth = 800 - 30;
        int maxHeight = 600 - 30;

        Block[] row1 = new Block[15];
        int y = 220;
        x = maxWidth;
        initRow(row1, (int) x, y);
    }

    /**
     * Initializes a row of blocks.
     *
     * @param row an array of Block objects representing the row of blocks
     * @param x   the x-coordinate of the leftmost block in the row
     * @param y   the y-coordinate of the row of blocks
     */
    public void initRow(Block[] row, int x, int y) {
        int bWidth = 50;
        int bHeight = 20;

        Map<Integer, Color> colorMap = new HashMap<>();
        colorMap.put(2, Color.CYAN);
        colorMap.put(4, Color.PINK);
        colorMap.put(6, Color.YELLOW);
        colorMap.put(8, Color.ORANGE);
        colorMap.put(10, Color.GREEN);
        Color color = Color.red;
        for (int i = 0; i < row.length; i++) {
            Point p = new Point(x - ((i + 1) * bWidth), y);

            if (colorMap.containsKey(i)) {
                color = colorMap.get(i);
            }
            row[i] = new Block(new Rectangle(p, bWidth, bHeight, color), null);
            this.blocks.add(row[i]);
        }
    }

    @Override
    public int numberOfBalls() {
        return numOfBalls;
    }

    @Override
    public List<Velocity> initialBallVelocities() {
        return this.initialBallVelocities;
    }

    @Override
    public int paddleSpeed() {
        return this.paddleSpeed;
    }

    @Override
    public int paddleWidth() {
        return this.paddleWidth;
    }

    @Override
    public String levelName() {
        return this.levelName;
    }

    @Override
    public Sprite getBackground() {
        return this.background;
    }

    @Override
    public List<Block> blocks() {
        return this.blocks;
    }

    @Override
    public int numberOfBlocksToRemove() {
        return this.numberOfBlocksToRemove;
    }

    @Override
    public Color getPaddleColor() {
        return this.paddleColor;
    }
}
