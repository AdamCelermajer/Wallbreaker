// 332638592 Adam Celermajer
package geometry;

import java.util.List;

/**
 * Represents a line in a 2-dimensional coordinate system.
 * The line is defined by two endpoints.
 */
public class Line {

    // The maximum difference allowed between two double values to consider them equal
    private static final double EPSILON = 0.00000001;
    private final Point noIntersection = new Point(Double.POSITIVE_INFINITY, Double.POSITIVE_INFINITY);
    // The start point of the line
    private final Point start;

    // The end point of the line
    private final Point end;

    /**
     * Constructs a geometry.Line object with the specified start and end points.
     *
     * @param start the start point of the line
     * @param end   the end point of the line
     */
    public Line(Point start, Point end) {
        this.start = start;
        this.end = end;
    }

    /**
     * Constructs a geometry.Line object with the specified coordinates of start and end points.
     *
     * @param x1 the x-coordinate of the start point
     * @param y1 the y-coordinate of the start point
     * @param x2 the x-coordinate of the end point
     * @param y2 the y-coordinate of the end point
     */
    public Line(double x1, double y1, double x2, double y2) {
        this.start = new Point(x1, y1);
        this.end = new Point(x2, y2);
    }

    /**
     * Returns the length of this line.
     *
     * @return the length of this line
     */
    public double length() {
        return start.distance(end);
    }

    /**
     * Returns the middle point of this line.
     *
     * @return the middle point of this line
     */
    public Point middle() {
        double midX = (start.getX() + end.getX()) / 2;
        double midY = (start.getY() + end.getY()) / 2;
        return new Point(midX, midY);
    }

    /**
     * Returns the start point of this line.
     *
     * @return the start point of this line
     */
    public Point start() {
        return start;
    }

    /**
     * Returns the end point of this line.
     *
     * @return the end point of this line
     */
    public Point end() {
        return end;
    }

    /**
     * Calculates the slope of this line.
     * If the line is vertical, returns positive infinity.
     *
     * @return the slope of this line, or positive infinity if the line is vertical
     */
    public double slope() {
        double xDiff = end.getX() - start.getX();
        double yDiff = end.getY() - start.getY();

        if (Math.abs(xDiff) < EPSILON) {
            return Double.POSITIVE_INFINITY;
        } else {
            return yDiff / xDiff;
        }
    }

    /**
     * Calculates the y-intercept of this line.
     * If the line is vertical, returns positive infinity.
     *
     * @return the y-intercept of this line, or positive infinity if the line is vertical
     */
    public double yIntersect() {
        double slope = slope();
        if (Math.abs(slope - Double.POSITIVE_INFINITY) < EPSILON) {
            return Double.POSITIVE_INFINITY;
        }
        return start.getY() - slope * start.getX();
    }

    /**
     * Calculates the distance from a point to this line.
     *
     * @param point The point for which the distance to the line is to be calculated.
     * @return The distance from the point to the line.
     */
    public double distanceToPoint(Point point) {
        // Convert the line equation to general form (Ax + By + C = 0)
        double a = -slope();
        double b = 1;
        double c = -yIntersect();

        // Coordinates of the point
        double x = point.getX();
        double y = point.getY();

        // Calculate the distance using the formula
        return Math.abs(a * x + b * y + c) / Math.sqrt(a * a + b * b);
    }


    /**
     * Checks if the specified point lies on this line.
     *
     * @param p the point to check if it lies on this line
     * @return true if the point lies on this line, false otherwise
     */
    public boolean isInLine(Point p) {
        double yOnLine = this.slope() * p.getX() + this.yIntersect();

        if (Math.abs(yOnLine - p.getY()) < EPSILON) {
            return p.getX() >= Math.min(this.start.getX(), this.end.getX())
                    && p.getX() <= Math.max(this.start.getX(), this.end.getX());
        }
        return false;
    }

    /**
     * Checks if this line intersects with the specified line.
     *
     * @param other the other line to check for intersection
     * @return true if the lines intersect, false otherwise
     */
    public boolean isIntersecting(Line other) {
        Point intersection = intersectionWith(other);
        return intersection != null && !intersection.equals(noIntersection);
    }

    /**
     * Determines if two line segments have a shared endpoint and returns that point if they do.
     * If the two line segments have no shared endpoint, this method returns null.
     *
     * @param other The other geometry.Line object to check for a shared endpoint with this line segment.
     * @return The shared geometry.Point object if the line segments have a shared endpoint, otherwise null.
     */
    public Point edgeCollision(Line other) {
        if (Math.abs(this.slope() - other.slope()) < EPSILON) {
            return null;
        }
        boolean eStart = this.start.equals(other.start) || this.start.equals(other.end);

        boolean eEnd = this.end.equals(other.start) || this.end.equals(other.end);
        if (eStart) {
            return this.start;
        }
        if (eEnd) {
            return this.end;
        }
        return null;
    }

    /**
     * Calculates the x-coordinate of the point at which the line intersects with the given y-coordinate.
     *
     * @param y the y-coordinate of the intersection point
     * @return the x-coordinate of the intersection point
     */
    public Double getXWithY(Double y) {
        return (y - yIntersect()) / this.slope();
    }

    /**
     * Calculates the point of intersection between this line and the given line segment.
     * If the lines do not intersect, returns null.
     *
     * @param other the line segment to check for intersection with this line
     * @return the point of intersection between this line and the given line segment, or null if they do not intersect
     */
    public Point bugPatch(Line other) {
        Point p = new Point(this.getXWithY(other.start.getY()), other.start.getY());
        if (Math.max(this.start.getY(), this.end.getY()) > other.start.getY()
                && Math.min(this.start.getY(), this.end.getY()) < other.start.getY()
                && isPointInRange(p, other.start, other.end)) {

            return p;

        }
        return null;
    }

    /**
     * Returns the intersection point of this line segment with another line segment, if it exists.
     * If the lines are overlapping, returns the first endpoint of the overlap.
     * If there is no intersection, returns null.
     *
     * @param other The other geometry.Line object to check for intersection with this line segment.
     * @return The intersection geometry.Point object if the line segments intersect, otherwise null.
     */
    public Point intersectionWith(Line other) {
        if (other.slope() == 0 && this.slope() != 0 && this.slope() != Double.POSITIVE_INFINITY) {
            return bugPatch(other);
        }
        if (edgeCollision(other) != null) {
            return edgeCollision(other);
        }
        if (isPoint() || other.isPoint()) {
            return intersectionWithPointLine(this, other);
        }

        double mySlope = slope();
        double otherSlope = other.slope();

        if (Double.isInfinite(mySlope) && Double.isInfinite(otherSlope)) {
            return intersectionWithVerticalLines(other);
        }

        if (Math.abs(mySlope - otherSlope) < EPSILON) {
            return intersectionWithParallelLines(other);
        }

        return intersectionWithNonParallelLines(other, mySlope, otherSlope);
    }

    /**
     * Checks if the current line segment is just a point, i.e., has the same start and end coordinates.
     *
     * @return true if the line segment is a point, otherwise false.
     */
    private boolean isPoint() {
        return start.equals(end);
    }

    /**
     * Returns the intersection point of a point-line with a non-point line if it exists, otherwise null.
     *
     * @param line1 The first geometry.Line object, which can be a point-line or a non-point line.
     * @param line2 The second geometry.Line object, which can be a point-line or a non-point line.
     * @return The intersection geometry.Point object if the point is on the non-point line, otherwise null.
     */
    private Point intersectionWithPointLine(Line line1, Line line2) {
        Line pointLine = line1.isPoint() ? line1 : line2;
        Line nonPointLine = line1.isPoint() ? line2 : line1;
        Point point = pointLine.start;

        if (nonPointLine.distanceToPoint(point) < EPSILON
                && isPointInRange(point, nonPointLine.start, nonPointLine.end)) {
            return new Point(point.getX(), point.getY());
        }

        return null;
    }


    /**
     * Checks if the given point lies within the range defined by the start and end points of a line segment.
     *
     * @param point     The geometry.Point object to check for its position.
     * @param lineStart The starting geometry.Point of the line segment.
     * @param lineEnd   The ending geometry.Point of the line segment.
     * @return true if the point is within the range, otherwise false.
     */
    private boolean isPointInRange(Point point, Point lineStart, Point lineEnd) {
        return point.getX() >= Math.min(lineStart.getX(), lineEnd.getX())
                && point.getX() <= Math.max(lineStart.getX(), lineEnd.getX())
                && point.getY() >= Math.min(lineStart.getY(), lineEnd.getY())
                && point.getY() <= Math.max(lineStart.getY(), lineEnd.getY());
    }

    /**
     * Returns the intersection point of two vertical lines if they overlap, otherwise null.
     *
     * @param other The other geometry.Line object, which is also vertical.
     * @return The intersection geometry.Point object if the vertical lines overlap, otherwise null.
     */
    private Point intersectionWithVerticalLines(Line other) {
        if (Math.abs(start.getX() - other.start.getX()) < EPSILON) {
            return intersectionWithOverlap(this, other);
        }
        return null;
    }

    /**
     * Returns the intersection point of two parallel lines if they overlap, otherwise null.
     *
     * @param other The other geometry.Line object, which is parallel to this line.
     * @return The intersection geometry.Point object if the parallel lines overlap, otherwise null.
     */
    private Point intersectionWithParallelLines(Line other) {
        if (Math.abs(yIntersect() - other.yIntersect()) < EPSILON) {
            return intersectionWithOverlap(this, other);
        }
        return null;
    }

    /**
     * Returns the intersection point of two line segments if they overlap.
     * If there is no overlap, returns null.
     *
     * @param line1 The first geometry.Line object.
     * @param line2 The second geometry.Line object.
     * @return The intersection geometry.Point object if the line segments overlap, otherwise null.
     */
    private Point intersectionWithOverlap(Line line1, Line line2) {
        if (isPointInRange(line1.start, line2.start, line2.end)) {

            if (Math.abs(line1.start.getY() - line2.start.getY()) > EPSILON
                    || Math.abs(line1.start.getY() - line2.end.getY()) > EPSILON) {
                return noIntersection;
            }
            return null;
        } else if (isPointInRange(line1.end, line2.start, line2.end)) {


            if (Math.abs(line1.end.getY() - line2.start.getY()) > EPSILON
                    || Math.abs(line1.end.getY() - line2.end.getY()) > EPSILON) {
                return noIntersection;
            }

        }
        return null;
    }

    /**
     * Returns the intersection point of two non-parallel lines, if it exists and lies within the line segments.
     * If there is no intersection, returns null.
     *
     * @param other      The other geometry.Line object that is non-parallel to this line segment.
     * @param mySlope    The slope of this line segment.
     * @param otherSlope The slope of the other line segment.
     * @return The intersection geometry.Point object if the non-parallel lines intersect within the line segments
     * , otherwise
     * null.
     */
    private Point intersectionWithNonParallelLines(Line other, double mySlope, double otherSlope) {
        double xInter;
        double yInter;

        if (Double.isInfinite(mySlope) || Double.isInfinite(otherSlope)) {
            Line verticalLine = Double.isInfinite(mySlope) ? this : other;
            Line nonVerticalLine = Double.isInfinite(mySlope) ? other : this;

            xInter = verticalLine.start.getX();
            yInter = nonVerticalLine.slope() * xInter + nonVerticalLine.yIntersect();
        } else {
            xInter = (other.yIntersect() - yIntersect()) / (mySlope - otherSlope);
            yInter = xInter * mySlope + yIntersect();
        }

        Point intersection = new Point(xInter, yInter);

        if (isPointInRange(intersection, start, end) && isPointInRange(intersection, other.start, other.end)) {
            return intersection;
        } else {
            return null;
        }
    }

    /**
     * Checks if this line is equal to the specified line.
     * Two lines are considered equal if they have the same start and end points,
     * regardless of the order of the points.
     *
     * @param other the other line to compare to
     * @return true if the lines are equal, false otherwise
     */
    public boolean equals(Line other) {
        return this.start.equals(other.start) && this.end.equals(other.end)
                ||
                this.start.equals(other.end) && this.end.equals(other.start);
    }


    /**
     * Finds the closest intersection point between this line and the given rectangle.
     * If the line does not intersect with the rectangle, returns null.
     *
     * @param rect the rectangle to check for intersection with this line
     * @return the closest intersection point to the start of the line, or null if there is no intersection
     */
    public Point closestIntersectionToStartOfLine(Rectangle rect) {
        List<Point> intersectionPoints = rect.intersectionPoints(this);
        if (intersectionPoints.isEmpty()) {
            return null;
        }
        Point closestIntersection = null;
        double closestDistance = Double.POSITIVE_INFINITY;
        for (Point p : intersectionPoints) {
            double distance = this.start().distance(p);
            if (distance < closestDistance) {
                closestIntersection = p;
                closestDistance = distance;
            }
        }
        return closestIntersection;
    }

}
