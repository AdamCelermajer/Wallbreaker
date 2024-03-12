// 332638592 Adam Celermajer
package level;

import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import game.Block;
import interfaces.LevelInformation;
import interfaces.Sprite;
import level.Back.VanillaBack;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * The Vanilla class represents a specific level called "Vanilla".
 * It implements the LevelInformation interface and provides methods to retrieve the information about the level.
 */
public class Vanilla implements LevelInformation {
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
     * Constructs a Vanilla level with the predefined attributes.
     */
    public Vanilla() {
        this.background = new VanillaBack();
        this.numOfBalls = 2;
        this.paddleSpeed = 7;
        this.paddleWidth = 115;
        this.levelName = "Green 3";
        this.blocks = new ArrayList<>();
        this.initialBallVelocities = new ArrayList<>(numOfBalls);
        this.paddleColor = Color.ORANGE;
        double xIncrement = 10;
        double vx = -5;

        for (int i = 0; i < numOfBalls; i++) {
            // Calculate the y component (assuming it is constant)
            initialBallVelocities.add(new Velocity(vx, -4));
            // Increment the x component
            vx += xIncrement;
        }

        this.numberOfBlocksToRemove = 10 + 9 + 8 + 7 + 6;

        int maxWidth = 800 - 30;
        int maxHeight = 600 - 30;

        Block[] row1 = new Block[10];
        Block[] row2 = new Block[9];
        Block[] row3 = new Block[8];
        Block[] row4 = new Block[7];
        Block[] row5 = new Block[6];
        int y = 120;
        int x = maxWidth;
        initRow(row1, x, y, Color.GRAY);
        y += 20;
        initRow(row2, x, y, Color.RED);
        y += 20;
        initRow(row3, x, y, Color.YELLOW);
        y += 20;
        initRow(row4, x, y, Color.BLUE);
        y += 20;
        initRow(row5, x, y, Color.WHITE);
    }

    /**
     * Initializes a row of blocks.
     *
     * @param row   an array of Block objects representing the row of blocks
     * @param x     the x-coordinate of the leftmost block in the row
     * @param y     the y-coordinate of the row of blocks
     * @param color the color of the blocks in the row
     */
    public void initRow(Block[] row, int x, int y, Color color) {
        int bWidth = 50;
        int bHeight = 20;
        for (int i = 0; i < row.length; i++) {
            Point p = new Point(x - ((i + 1) * bWidth), y);
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


