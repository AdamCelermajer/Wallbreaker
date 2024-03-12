// 332638592 Adam Celermajer

package geometry;

import biuoop.DrawSurface;

import interfaces.Sprite;

import java.awt.Color;
import java.util.Random;

import game.GameEnvironment;
import game.CollisionInfo;
import game.GameLevel;

/**
 * A class representing a ball with a center point, radius, color, and velocity.
 * The ball can be drawn on a given DrawSurface, and its movement can be updated by calling moveOneStep().
 * The ball also provides methods for accessing and setting its center point, velocity, radius, and color.
 * The checkBoundInScreen() method checks whether the ball is still within the bounds of the screen.
 */

public class Ball implements Sprite {


    private int r;
    private Color color;
    private Velocity vel;
    private Point center;

    private GameEnvironment gE;

    /**
     * Constructs a Ball object with a given center point, radius, color, and game environment.
     * If the "color" parameter is null, a random color will be generated and assigned to the ball.
     *
     * @param center the center point of the ball.
     * @param r      the radius of the ball.
     * @param color  the color of the ball, or null if a random color should be assigned.
     * @param gE     the game environment to add the ball to.
     * @param vel    the velocity of the ball.
     */
    public Ball(Point center, int r, Color color, GameEnvironment gE, Velocity vel) {
        if (color == null) {
            Random random = new Random();
            int red = random.nextInt(256);
            int green = random.nextInt(256);
            int blue = random.nextInt(256);

            this.color = new Color(red, green, blue);
        } else {
            this.color = color;
        }
        this.vel = vel == null ? randomVel(r) : vel;
        this.center = center;
        this.r = r;

        this.gE = gE;
    }

    /**
     * Constructs a geometry.Ball object with the specified coordinates of the center and radius,
     * and assigns a random color to the ball.
     * The frame is set to a default frame with (0, 0) as its top-left point and width and height of 200.
     *
     * @param x     the x-coordinate of the center point of the ball.
     * @param y     the y-coordinate of the center point of the ball.
     * @param r     the radius of the ball.
     * @param color the color of the ball, or null if a random color should be assigned.
     * @param gE    the game.GameEnvironment to add the ball to.
     */
    public Ball(double x, double y, int r, Color color, GameEnvironment gE) {

        if (color == null) {
            Random random = new Random();
            int red = random.nextInt(256);
            int green = random.nextInt(256);
            int blue = random.nextInt(256);

            this.color = new Color(red, green, blue);
        } else {
            this.color = color;
        }
        this.vel = randomVel(r);
        this.center = new Point(x, y);
        this.r = r;
        this.gE = gE;
    }


    /**
     * Generates a random geometry.Velocity object with the given ball radius, such that
     * larger balls have slower speeds. The speed is randomly chosen between 1 and 2
     * for balls with radius greater than 50, and between 4 and 7 for smaller balls.
     *
     * @param r the radius of the ball
     * @return a new geometry.Velocity object with a random x and y component
     */
    public static Velocity randomVel(int r) {
        double angle = Math.random() * 60 + 300;
        double speed = (r > 50) ? Math.random() + 2 : Math.random() * 3 + 4;
        return Velocity.fromAngleAndSpeed(angle, speed);
    }

    /**
     * Gets the x coordinate of the center of the ball.
     *
     * @return the x coordinate of the center of the ball.
     */
    public int getX() {
        return (int) center.getX();
    }

    /**
     * Gets the y coordinate of the center of the ball.
     *
     * @return the y coordinate of the center of the ball.
     */
    public int getY() {
        return (int) center.getY();
    }

    /**
     * Sets the radius of this ball to the specified value.
     *
     * @param r the new radius to set for this ball
     */
    public void setR(int r) {
        this.r = r;
    }

    /**
     * Gets the radius of the ball.
     *
     * @return the radius of the ball.
     */
    public int getSize() {
        return r;
    }

    /**
     * Gets the color of the ball.
     *
     * @return the color of the ball.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Sets the color of this object to the specified color.
     *
     * @param color the new color to set for this object
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * Draws the ball on the given DrawSurface.
     *
     * @param surface the DrawSurface on which to draw the ball.
     */
    public void drawOn(DrawSurface surface) {
        surface.setColor(color);
        surface.fillCircle((int) center.getX(), (int) center.getY(), r);
        surface.setColor(Color.BLACK);
        surface.drawCircle((int) center.getX(), (int) center.getY(), r);
    }

    /**
     * Sets the velocity of the ball using dx and dy values.
     *
     * @param dx the change in x per time unit.
     * @param dy the change in y per time unit.
     */
    public void setVelocity(double dx, double dy) {
        this.vel.setVel(dx, dy);
    }

    /**
     * Gets the velocity of the ball.
     *
     * @return the velocity of the ball.
     */
    public Velocity getVelocity() {
        return vel;
    }

    /**
     * Sets the velocity of the ball using a geometry.Velocity object.
     *
     * @param v the geometry.Velocity object to set the ball's velocity to.
     */
    public void setVelocity(Velocity v) {
        this.vel = v;
    }

    /**
     * Gets the game.GameEnvironment of the ball.
     *
     * @return the game.GameEnvironment of the ball.
     */
    public GameEnvironment getgE() {
        return this.gE;
    }

    /**
     * Adds the ball to the given Ass3Game object as a sprite.
     *
     * @param g the Ass3Game object to add the ball to.
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
    }


    /**
     * Implements the timePassed method from the interfaces.Sprite interface.
     * This method simply calls moveOneStep(), as the ball's movement is updated over time.
     */
    public void timePassed() {
        moveOneStep();
    }

    /**
     * Moves the ball one step according to its velocity, and handles collisions with the game.GameEnvironment.
     */
    public void moveOneStep() {

        // Update the ball's position based on its velocity
        Point dest = this.vel.applyToPoint(this.center);
        Line trajectory = new Line(this.center, dest);

        CollisionInfo tst = gE.getClosestCollision(trajectory);

        if (tst != null) {
            this.vel.setVelocity(tst.collisionObject().hit(this, tst.collisionPoint(), vel));
        }

        dest = this.vel.applyToPoint(this.center);
        trajectory = new Line(this.center, dest);
        tst = gE.getClosestCollision(trajectory);

        if (tst != null) {
            this.vel.setVelocity(tst.collisionObject().hit(this, tst.collisionPoint(), vel));

        }
        this.center = this.vel.applyToPoint(this.center);


    }

    /**
     * Removes the ball from the game.
     *
     * @param game the game.Game object from which the sprite will be removed
     */
    public void removeFromGame(GameLevel game) {
        game.removeSprite(this);
    }

}
