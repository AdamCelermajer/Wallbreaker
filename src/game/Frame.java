// 332638592 Adam Celermajer
package game;

import geometry.Ball;
import geometry.Point;
import geometry.Rectangle;
import geometry.Velocity;
import interfaces.Collidable;

/**
 * A class representing a frame defined by a top-left point, width, and height.
 */
public class Frame implements Collidable {

    // The maximum difference allowed between two double values to consider them equal
    private static final double EPSILON = 0.00000001;
    private Rectangle rect;

    /**
     * Constructs a new frame with the specified top-left point, width, and height.
     *
     * @param topLeft the top-left point of the frame
     * @param width   the width of the frame
     * @param height  the height of the frame
     */
    public Frame(Point topLeft, int width, int height) {
        this.rect = new Rectangle(topLeft, width, height, null);
    }

    /**
     * Constructs a new frame with the specified rectangle.
     *
     * @param rect the rectangle representing the frame
     */
    public Frame(Rectangle rect) {
        this.rect = rect;
    }

    /**
     * Sets the height of the frame to the specified value.
     *
     * @param height the new height of the frame
     */
    public void setHeight(int height) {
        this.rect.setHeight(height);
    }


    /**
     * Adds this frame to the specified game as a collidable object.
     *
     * @param g the game to add this frame to
     */
    public void addToGame(GameLevel g) {
        g.addCollidable(this);
    }

    /**
     * Checks if a point is inside the frame.
     *
     * @param point the point to check
     * @return true if the point is inside the frame, false otherwise
     */
    public boolean contains(Point point) {
        return (this.rect.getUpperLeft().getX() <= point.getX()
                &&
                point.getX() < this.rect.getUpperLeft().getX() + this.rect.getWidth()
                && this.rect.getUpperLeft().getY() <= point.getY()
                &&
                point.getY() < this.rect.getUpperLeft().getY() + this.rect.getHeight());
    }

    /**
     * Returns the point closest to the specified point within the boundaries of the frame.
     *
     * @param point the point to check
     * @return the point closest to the specified point within the boundaries of the frame
     */
    public Point containsWithinDistance(Point point) {

        if (point.getX() <= this.rect.getUpperLeft().getX()) {
            return new Point(point.getX(), point.getY());
        }
        if (point.getX() >= this.rect.getUpperLeft().getX()) {
            return new Point(point.getX(), point.getY());
        }

        if (point.getY() >= this.rect.getUpperLeft().getY() + this.rect.getHeight()) {
            return new Point(point.getX(), point.getY());
        }

        if (point.getY() <= this.rect.getUpperLeft().getY()) {
            return new Point(point.getX(), point.getY());
        }

        return null;

    }

    /**
     * Returns the rectangle representing the frame as a collidable object.
     *
     * @return the rectangle representing the frame as a collidable object
     */
    public Rectangle getCollisionRectangle() {
        return this.rect;
    }

    /**
     * Prints a message identifying the object.
     */
    @Override
    public void whoRU() {
        System.out.println("im the frame");
    }

    /**
     * Calculates the new velocity after a collision with the frame.
     *
     * @param collisionPoint  the point of collision
     * @param currentVelocity the current velocity of the object
     * @return the new velocity after the collision
     */
    @Override
    public Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity) {


        double dx = currentVelocity.getDx();
        double dy = currentVelocity.getDy();


        if (Math.abs(collisionPoint.getX() - this.rect.getUpperLeft().getX()) < EPSILON
                ||
                Math.abs(collisionPoint.getX() - (this.rect.getUpperLeft().getX() + this.rect.getWidth())) < EPSILON) {
            dx = -dx;
        }

        if (Math.abs(collisionPoint.getY() - this.rect.getUpperLeft().getY()) < EPSILON
                ||
                Math.abs(collisionPoint.getY() - (this.rect.getUpperLeft().getY() + this.rect.getHeight())) < EPSILON) {
            dy = -dy;
        }

        return new Velocity(dx, dy);
    }
}