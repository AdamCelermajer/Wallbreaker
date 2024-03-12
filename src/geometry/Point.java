// 332638592 Adam Celermajer
package geometry;
import game.Frame;
import java.util.Random;

/**
 * Represents a point in a two-dimensional coordinate system.
 */
public class Point {

    private double x; // the x-coordinate of this point
    private double y; // the y-coordinate of this point

    /**
     * Constructs a point with the given x and y coordinates.
     *
     * @param x the x-coordinate of the point
     * @param y the y-coordinate of the point
     */
    public Point(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Returns a randomly generated geometry.Point object within the boundaries of the given game.Frame object.
     *
     * @param frame the game.Frame object representing the screen boundaries
     * @return a randomly generated geometry.Point object within the screen boundaries
     */
    public static Point randomPointInScreen(Frame frame) {

        Random random = new Random();
        int randomX = (int) (random.nextDouble(
                frame.getCollisionRectangle().getWidth() + frame.getCollisionRectangle().getUpperLeft().getX())
                + frame.getCollisionRectangle().getUpperLeft().getX());

        int randomY = (int) (random.nextDouble(
                frame.getCollisionRectangle().getHeight() + frame.getCollisionRectangle().getUpperLeft().getY())
                + frame.getCollisionRectangle().getUpperLeft().getY());
        return new Point(randomX, randomY);
    }

    /**
     * Calculates the Euclidean distance between this point and another point.
     *
     * @param other the other point
     * @return the distance between this point and the other point
     */
    public double distance(Point other) {
        double dx = x - other.getX();
        double dy = y - other.getY();
        return Math.sqrt(dx * dx + dy * dy);
    }

    /**
     * Checks if this point is equal to another point.
     *
     * @param other the other point
     * @return true if this point is equal to the other point, false otherwise
     */
    public boolean equals(Point other) {
        return x == other.getX() && y == other.getY();
    }

    /**
     * Returns the x-coordinate of this point.
     *
     * @return the x-coordinate of this point
     */
    public double getX() {
        return x;
    }

    /**
     * Sets the x-coordinate of this point to the specified value.
     *
     * @param x the new y-coordinate for this point
     */
    public void setX(double x) {
        this.x = x;
    }

    /**
     * Returns the y-coordinate of this point.
     *
     * @return the y-coordinate of this point
     */
    public double getY() {
        return y;
    }

    /**
     * Sets the y-coordinate of this point to the specified value.
     *
     * @param y the new y-coordinate for this point
     */
    public void setY(double y) {
        this.y = y;
    }

    /**
     * Sets the x and y coordinates of this point to those of the specified point.
     *
     * @param a the geometry.Point object to copy the coordinates from
     */
    public void setP(Point a) {
        this.x = a.getX();
        this.y = a.getY();
    }
}
