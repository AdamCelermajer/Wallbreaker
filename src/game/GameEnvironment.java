//332638592 Adam Celermajer
package game;

import geometry.Line;
import geometry.Point;
import interfaces.Collidable;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents an environment in which objects can collide with each other.
 */
public class GameEnvironment {

    private List<Collidable> myCollid;

    /**
     * Constructor that initializes an empty list of interfaces.Collidable objects.
     */
    public GameEnvironment() {
        this.myCollid = new ArrayList<Collidable>();
    }

    /**
     * Returns the list of interfaces.Collidable objects in this environment.
     *
     * @return the list of interfaces.Collidable objects
     */
    public List<Collidable> getMyCollid() {
        return this.myCollid;
    }

    /**
     * Adds the given interfaces.Collidable object to this environment.
     *
     * @param c the interfaces.Collidable object to add
     */
    public void addCollidable(Collidable c) {
        myCollid.add(c);

    }


    /**
     * Given a line representing the trajectory of an object, determines whether
     * it will collide with any of the interfaces.Collidable objects in this environment.
     * If a collision will occur, returns a game.CollisionInfo object containing
     * the geometry.Point of the closest collision and the interfaces.Collidable object that will collide.
     * If no collisions will occur, returns null.
     *
     * @param trajectory the trajectory of the object
     * @return a game.CollisionInfo object if a collision will occur, or null if not
     */
    public CollisionInfo getClosestCollision(Line trajectory) {

        Point bst = null;
        Point tmp;
        double best = Double.POSITIVE_INFINITY;
        Collidable c = null;

        int flag = 0;

        for (Collidable a : myCollid) {

            List<Point> inter = a.getCollisionRectangle().intersectionPoints(trajectory);

            if (!inter.isEmpty()) {

                flag = 1;

                tmp = getBestPoint(inter, trajectory.start());

                if (tmp.distance(trajectory.start()) < best) {

                    best = tmp.distance(trajectory.start());
                    c = a;
                    bst = tmp;

                }
            }

        }
        if (flag == 1) {
            return new CollisionInfo(bst, c);
        } else {
            return null;
        }
    }

    /**
     * Given a list of geometry.Point objects and a reference geometry.Point, returns the geometry.Point in the list
     * that is closest to the reference geometry.Point.
     *
     * @param list the list of geometry.Point objects
     * @param p    the reference geometry.Point
     * @return the geometry.Point in the list closest to p
     */
    public Point getBestPoint(List<Point> list, Point p) {
        Point best = list.get(0);
        double dist = p.distance(best);
        for (Point a : list) {
            if (a.distance(p) <= dist) {
                best = a;
                dist = a.distance(p);
            }
        }
        return best;
    }

}