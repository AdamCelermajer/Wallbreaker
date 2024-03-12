// 332638592 Adam Celermajer

package game;

import biuoop.KeyboardSensor;
import biuoop.DrawSurface;
import geometry.Ball;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import interfaces.Collidable;
import interfaces.Sprite;


/**
 * class representing user controlled paddle.
 */
public class Paddle implements Sprite, Collidable {
    private static final double EPSILON = 0.00001;
    private int speed;
    private Rectangle rect;
    private KeyboardSensor keyboard;

    /**
     * Constructs a paddle with the specified keyboard sensor and rectangle.
     *
     * @param keyboard the keyboard sensor
     * @param rect     the rectangle representing the paddle
     * @param speed    representing the speed of the paddle
     */
    public Paddle(KeyboardSensor keyboard, Rectangle rect, int speed) {
        this.speed = speed;
        this.rect = rect;
        this.keyboard = keyboard;
    }

    /**
     * Prints a message identifying the object.
     */
    public void whoRU() {
        System.out.println("game.Paddle");
    }

    /**
     * Moves the paddle to the left.
     */
    public void moveLeft() {


        if (this.rect.getUpperLeft().getX() - this.speed < 30) {
            this.rect.getUpperLeft().setX(30);
        } else {
            this.rect.getUpperLeft().setX(this.rect.getUpperLeft().getX() - this.speed);

        }

    }

    /**
     * Moves the paddle to the right.
     */
    public void moveRight() {

        if (this.rect.getUpperLeft().getX() + this.rect.getWidth() + this.speed > 770) {

            this.rect.getUpperLeft().setX(770 - this.rect.getWidth());
        } else {

            this.rect.getUpperLeft().setX(this.rect.getUpperLeft().getX() + this.speed);

        }
    }

    // interfaces.Sprite

    /**
     * Called when time has passed and updates the paddle state.
     */
    public void timePassed() {

        if (keyboard.isPressed("a") || keyboard.isPressed(KeyboardSensor.LEFT_KEY)) {
            moveLeft();
        }
        if (keyboard.isPressed("d") || keyboard.isPressed(KeyboardSensor.RIGHT_KEY)) {

            moveRight();
        }

    }

    /**
     * Draws the paddle on the specified draw surface.
     *
     * @param d the draw surface
     */
    public void drawOn(DrawSurface d) {
        this.rect.drawOn(d);
    }

    // interfaces.Collidable

    /**
     * Gets the collision rectangle of the paddle.
     *
     * @return the collision rectangle
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }


    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {
        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();

        double paddleWidth = this.rect.getWidth();
        double regionWidth = paddleWidth / 5;
        double ballAngle;


        //check if collide range in x
        if (collisionPoint.getX() < this.rect.getUpperLeft().getX()
                || collisionPoint.getX() > this.rect.getUpperLeft().getX() + this.rect.getWidth()) {
            return currentVelocity;
        }
        // check if collide range in y

        if (collisionPoint.getY() < this.rect.getUpperLeft().getY()
                || collisionPoint.getY() > this.rect.getUpperLeft().getY() + this.rect.getHeight()) {
            return currentVelocity;
        }

        if (collisionPoint.getY() > this.rect.getUpperLeft().getY()) {
            return new Velocity(-dx, dy);
        }

// Calculate the region of the paddle the ball hits

        if (collisionPoint.getX() < this.rect.getUpperLeft().getX() + regionWidth) {
            //region 1
            ballAngle = 300;

        } else if (collisionPoint.getX() < this.rect.getUpperLeft().getX() + 2 * regionWidth) {
            //region 2
            ballAngle = 330;

        } else if (collisionPoint.getX() < this.rect.getUpperLeft().getX() + 3 * regionWidth) {
            //region 3
            return new Velocity(dx, -dy);
        } else if (collisionPoint.getX() < this.rect.getUpperLeft().getX() + 4 * regionWidth) {
            //region 4
            ballAngle = 30;

        } else {
            //region 5
            ballAngle = 60;

        }
        // Calculate the new velocity of the ball based on the region it hits
        double speed = Math.sqrt(dx * dx + dy * dy);
        double angle = Math.toRadians(ballAngle);
        double newDx = speed * Math.sin(angle);
        double newDy = -speed * Math.cos(angle);

        return new Velocity(newDx, newDy);
    }

    /**
     * Adds the paddle to the specified game.
     *
     * @param g the game to add the paddle to
     */
    public void addToGame(GameLevel g) {
        g.addSprite(this);
        g.addCollidable(this);
    }

    /**
     * Returns the rectangle representing the boundaries of an object.
     *
     * @return the rectangle representing the boundaries
     */
    public Rectangle getRect() {
        return this.rect;
    }
}