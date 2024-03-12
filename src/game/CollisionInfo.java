// 332638592 Adam Celermajer
package game;

import geometry.Point;
import interfaces.Collidable;

/**
 * A class representing information about a collision between two objects.
 * The class stores the point at which the collision occurs, and the collidable object involved in the collision.
 */
public class CollisionInfo {

    private Point collision;
    private Collidable collid;
    // the point at which the collision occurs.

    /**
     * Constructs a game.CollisionInfo object with the given collision point and collidable object.
     *
     * @param collision the point at which the collision occurs.
     * @param collid    the collidable object involved in the collision.
     */
    public CollisionInfo(Point collision, Collidable collid) {
        this.collision = collision;
        this.collid = collid;
    }

    /**
     * Gets the collision point of this game.CollisionInfo object.
     *
     * @return the collision point of this game.CollisionInfo object.
     */
    public Point collisionPoint() {
        return collision;
    }

    /**
     * Gets the collidable object involved in the collision.
     *
     * @return the collidable object involved in the collision.
     */
    public Collidable collisionObject() {
        return collid;
    }
}