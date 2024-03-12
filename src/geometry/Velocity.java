// 332638592 Adam Celermajer
package geometry;

/**
 * Represents a velocity in a 2-dimensional coordinate system,
 * defined by its horizontal and vertical components (dx, dy).
 */
public class Velocity {

    // The horizontal component of the velocity
    private double dx;

    // The vertical component of the velocity
    private double dy;

    /**
     * Constructs a geometry.Velocity object with the specified horizontal and vertical components.
     *
     * @param dx the horizontal component of the velocity
     * @param dy the vertical component of the velocity
     */
    public Velocity(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Constructs a geometry.Velocity object from the specified angle and speed.
     * The angle is measured in degrees clockwise from the positive x-axis,
     * and the speed is measured in units per second.
     *
     * @param angle the angle in degrees clockwise from the positive x-axis
     * @param speed the speed in units per second
     * @return a geometry.Velocity object with the corresponding dx and dy components
     */
    public static Velocity fromAngleAndSpeed(double angle, double speed) {
        double dx = Math.cos(Math.toRadians(angle)) * speed;
        double dy = -1 * Math.sin(Math.toRadians(angle)) * speed;
        return new Velocity(dx, dy);
    }

    /**
     * Sets the horizontal and vertical components of this velocity to the specified values.
     *
     * @param dx the new horizontal component of the velocity
     * @param dy the new vertical component of the velocity
     */
    public void setVel(double dx, double dy) {
        this.dx = dx;
        this.dy = dy;
    }

    /**
     * Sets the horizontal and vertical components of this velocity to the same values as the specified velocity.
     *
     * @param v the velocity to copy the components from
     */
    public void setVelocity(Velocity v) {
        setVel(v.getDx(), v.getDy());
    }

    /**
     * Returns the horizontal component of this velocity.
     *
     * @return the horizontal component of this velocity
     */
    public double getDx() {
        return dx;
    }

    /**
     * Sets the horizontal component of this velocity to the specified value.
     *
     * @param dx the new horizontal component of the velocity
     */
    public void setDx(double dx) {
        this.dx = dx;
    }

    /**
     * Returns the vertical component of this velocity.
     *
     * @return the vertical component of this velocity
     */
    public double getDy() {
        return dy;
    }

    /**
     * Sets the vertical component of this velocity to the specified value.
     *
     * @param dy the new vertical component of the velocity
     */
    public void setDy(double dy) {
        this.dy = dy;
    }

    /**
     * Returns a new geometry.Point object with the same x and y coordinates as the specified point,
     * but with the x coordinate increased by the horizontal component of this velocity,
     * and the y coordinate increased by the vertical component of this velocity.
     *
     * @param p the point to apply the velocity to
     * @return a new geometry.Point object with the position (x+dx, y+dy)
     */
    public Point applyToPoint(Point p) {
        return new Point(p.getX() + dx, p.getY() + dy);
    }
}
