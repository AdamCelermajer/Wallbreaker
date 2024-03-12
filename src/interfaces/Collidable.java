// 332638592 Adam Celermajer


package interfaces;

import geometry.Ball;
import geometry.Rectangle;
import geometry.Point;
import geometry.Velocity;

/**
 * An interface for an object that can be collided with.
 */
public interface Collidable {

    /**
     * Returns the collision shape of the object.
     * The collision shape is represented as a geometry.Rectangle.
     *
     * @return the collision shape of the object
     */
    Rectangle getCollisionRectangle();

    /**
     * print the name of the object.
     */
    void whoRU();

    /**
     * Notifies the object that it has been collided with at the given collision point with the given velocity.
     * This method calculates the new velocity of the object after the hit.
     *
     * @param collisionPoint  the point at which the collision occurred
     * @param currentVelocity the current velocity of the colliding object
     * @param hitter          representing the ball hitting
     * @return the new velocity of the colliding object after the hit
     */
    Velocity hit(Ball hitter, Point collisionPoint, Velocity currentVelocity);

}
