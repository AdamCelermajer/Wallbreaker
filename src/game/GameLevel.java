// 332638592 Adam Celermajer
package game;

import biuoop.DrawSurface;
import biuoop.GUI;
import biuoop.KeyboardSensor;
import geometry.Ball;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import interfaces.Animation;
import interfaces.Collidable;
import interfaces.LevelInformation;
import interfaces.Sprite;

import java.awt.Color;
import java.util.List;

/**
 * The GameLevel class represents a game level. It implements the Animation interface to control the game flow.
 * It handles the initialization of the level, including creating and adding game objects, as well as running the game.
 */
public class GameLevel implements Animation {
    private AnimationRunner runner;
    private boolean running;
    private KeyboardSensor keyboard;
    private SpriteCollection sprites;
    private GameEnvironment environment;
    private GUI gui;
    private LevelInformation lI;
    private BlockRemover mBlock;
    private BallRemover mBall;
    private ScoreTrackingListener sTL;
    private Counter remainingBlock;
    private Counter scoreCounter;
    private Counter remainingBalls;

    /**
     * Constructs a GameLevel object with the specified level information, keyboard sensor, animation runner, GUI,
     * and score counter.
     *
     * @param lI     the level information for the game level.
     * @param kb     the keyboard sensor for user input.
     * @param runner the animation runner for running the game animations.
     * @param gui    the GUI for displaying the game.
     * @param score  the score counter for tracking the player's score.
     */
    public GameLevel(LevelInformation lI, KeyboardSensor kb, AnimationRunner runner, GUI gui, Counter score) {
        this.lI = lI;
        this.gui = gui;
        this.keyboard = kb;
        this.runner = runner;
        this.scoreCounter = score;
    }

    /**
     * Adds a collidable object to the game environment.
     *
     * @param c the collidable object to add.
     */
    public void addCollidable(Collidable c) {
        environment.addCollidable(c);
    }

    /**
     * Adds a sprite object to the sprite collection.
     *
     * @param s the sprite object to add.
     */
    public void addSprite(Sprite s) {
        sprites.addSprite(s);
    }

    /**
     * Removes a collidable object from the game environment.
     *
     * @param c the collidable object to remove.
     */
    public void removeCollidable(Collidable c) {
        environment.getMyCollid().remove(c);
    }

    /**
     * Removes a sprite object from the sprite collection.
     *
     * @param s the sprite object to remove.
     */
    public void removeSprite(Sprite s) {
        sprites.getMySprite().remove(s);
    }

    /**
     * Initializes a row of blocks.
     *
     * @param blocks a list of Block objects representing the row of blocks.
     */
    public void initRow(List<Block> blocks) {
        for (Block b : blocks) {
            b.addToGame(this);
            b.addHitListener(this.mBlock);
            b.addHitListener(this.sTL);
        }
    }

    /**
     * Initializes a new game level by creating and adding the blocks, ball, and paddle to the game.
     */
    public void initialize() {
        this.running = true;
        environment = new GameEnvironment();
        sprites = new SpriteCollection();

        if (lI.getBackground() != null) {
            sprites.addSprite(lI.getBackground());
        }

        this.remainingBlock = new Counter(lI.numberOfBlocksToRemove());
        this.remainingBalls = new Counter(lI.numberOfBalls());
        this.sTL = new ScoreTrackingListener(scoreCounter);
        this.mBlock = new BlockRemover(this, this.remainingBlock);
        this.mBall = new BallRemover(this, remainingBalls);

        Block upperBlock = new Block(new Rectangle(new Point(0, 20), 800, 10, Color.GRAY), "border");
        Block bottomBlock = new Block(new Rectangle(new Point(0, 570), 800, 30, Color.GRAY), "Bottom_border");
        Block leftBlock = new Block(new Rectangle(new Point(0, 30), 30, 540, Color.GRAY), "border");
        Block rightBlock = new Block(new Rectangle(new Point(770, 30), 30, 540, Color.GRAY), "border");

        ScoreIndicator scoreIndi = new ScoreIndicator(new Rectangle(new Point(0, 0), 800, 20, Color.WHITE),
                this.scoreCounter, lI.levelName());
        Block deathRegion = new Block(new Rectangle(new Point(0, 567), 800, 10, Color.GRAY), "Death_region");

        this.addSprite(scoreIndi);
        deathRegion.addHitListener(mBall);
        this.addCollidable(deathRegion);

        initRow(lI.blocks());

        Paddle pad = new Paddle(keyboard,
                new Rectangle(new Point(400 - (lI.paddleWidth() / 2.0), 555), lI.paddleWidth(), 15,
                        this.lI.getPaddleColor()),
                lI.paddleSpeed());

        upperBlock.addToGame(this);
        bottomBlock.addToGame(this);
        leftBlock.addToGame(this);
        rightBlock.addToGame(this);
        pad.addToGame(this);

        Point place = new Point(pad.getRect().getUpperLeft().getX() + ((double) lI.paddleWidth() / 2),
                pad.getRect().getUpperLeft().getY() - 10);

        this.createBallsOnTopOfPaddle(place, lI.numberOfBalls(), lI.initialBallVelocities());
    }

    /**
     * Creates and adds balls on top of the paddle at the specified location with the given velocities.
     *
     * @param place      the location of the balls.
     * @param numOfBalls the number of balls to create and add.
     * @param velocities the velocities of the balls.
     */
    public void createBallsOnTopOfPaddle(Point place, int numOfBalls, List<Velocity> velocities) {
        for (int i = 0; i < numOfBalls; i++) {
            Ball ball = new Ball(place, 7, Color.WHITE, environment, velocities.get(i));
            ball.addToGame(this);
        }
    }

    /**
     * Runs the game animation.
     *
     * @return 1 if the player wins the game, 0 otherwise.
     */
    public int run() {
        this.runner.run(new CountdownAnimation(1, 3, this.sprites));
        this.runner.run(this);
        if (this.remainingBalls.getValue() == 0) {
            return 1;
        }
        return 0;
    }

    /**
     * Checks if the game animation should stop.
     *
     * @return true if the game should stop, false otherwise.
     */
    public boolean shouldStop() {
        return !this.running;
    }

    /**
     * Performs one frame of the game animation.
     *
     * @param d the DrawSurface to draw on.
     */
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        this.sprites.notifyAllTimePassed();

        if (this.keyboard.isPressed("p")) {
            Animation pauseScreen = new PauseScreen();
            Animation pauseScreenWithKeyStop = new KeyPressStoppableAnimation(keyboard, "space", pauseScreen);
            this.runner.run(pauseScreenWithKeyStop);
        }

        if (this.remainingBlock.getValue() == 0) {
            this.scoreCounter.increase(100);
            this.running = false;
        }

        if (this.remainingBalls.getValue() == 0) {
            this.running = false;
        }
    }
}
