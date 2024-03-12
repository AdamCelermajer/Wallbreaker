// 332638592 Adam Celermajer
package geometry;

import biuoop.DrawSurface;

import interfaces.Sprite;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;


/**
 * A class representing a rectangle in a 2D plane.
 */
public class Rectangle implements Sprite {

    private Point topLeft;
    private double width;
    private double height;
    private Color color;

    /**
     * Constructs a new rectangle with the given upper-left point, width, height, and color.
     *
     * @param upperLeft the upper-left point of the rectangle
     * @param width     the width of the rectangle
     * @param height    the height of the rectangle
     * @param color     the color of the rectangle (default is black if null)
     */
    public Rectangle(Point upperLeft, double width, double height, Color color) {
        this.topLeft = upperLeft;
        this.width = width;
        this.height = height;
        if (color == null) {
            color = Color.black;
        }
        this.color = color;
    }

    /**
     * Checks if a given point is contained within the boundaries of the object.
     *
     * @param p the point to check
     * @return true if the point is contained within the boundaries, false otherwise
     */
    public Boolean contains(Point p) {

        if (p.getX() >= this.getUpperLeft().getX() && p.getX() <= this.getUpperLeft().getX() + this.width) {
            if (p.getY() >= this.getUpperLeft().getY() && p.getY() <= this.getUpperLeft().getY() + this.height) {
                return true;
            }
        }
        return false;

    }

    /**
     * Returns a (possibly empty) List of intersection points between the rectangle and the specified line.
     *
     * @param line the line to check for intersection with the rectangle
     * @return a (possibly empty) List of intersection points between the rectangle and the specified line
     */
    public List<Point> intersectionPoints(Line line) {


        Point topRight = new Point(topLeft.getX() + width, topLeft.getY());
        Point bottomLeft = new Point(topLeft.getX(), topLeft.getY() + height);
        Point bottomRight = new Point(topLeft.getX() + width, topLeft.getY() + height);
        Line upperLine = new Line(topLeft, topRight);
        Line bottomLine = new Line(bottomRight, bottomLeft);

        List<Point> rst = new ArrayList<>();
        Point intersection;


        intersection = line.intersectionWith(upperLine);
        if (intersection != null) {
            rst.add(intersection);
        }

        intersection = line.intersectionWith(new Line(topLeft, bottomLeft));
        if (intersection != null) {
            rst.add(intersection);
        }

        intersection = line.intersectionWith(bottomLine);

        if (intersection != null) {
            rst.add(intersection);
        }

        intersection = line.intersectionWith(new Line(bottomRight, topRight));
        if (intersection != null) {
            rst.add(intersection);
        }
        if (rst.isEmpty() && contains(line.end())) {
            rst.add(line.start());
        }
        return rst;
    }

    /**
     * Returns the width of the rectangle.
     *
     * @return the width of the rectangle
     */
    public double getWidth() {
        return width;
    }

    /**
     * Sets the width of the rectangle.
     *
     * @param width the new width of the rectangle
     */
    public void setWidth(double width) {
        this.width = width;
    }


    /**
     * Returns the height of the rectangle.
     *
     * @return the height of the rectangle
     */
    public double getHeight() {
        return height;
    }

    /**
     * Sets the height of the rectangle.
     *
     * @param height the new height of the rectangle
     */
    public void setHeight(double height) {
        this.height = height;
    }

    /**
     * Returns the upper-left point of the rectangle.
     *
     * @return the upper-left point of the rectangle
     */
    public Point getUpperLeft() {
        return topLeft;
    }

    /**
     * Gets the bottom-right point of the rectangle.
     *
     * @return the bottom-right point of the rectangle
     */
    public Point getBottomRight() {
        return new Point(topLeft.getX() + width, topLeft.getY() + height);
    }


    /**
     * Draws the rectangle on the given draw surface.
     *
     * @param d the draw surface on which to draw the rectangle
     */
    public void drawOn(DrawSurface d) {
        d.setColor(this.color);
        d.fillRectangle((int) topLeft.getX(), (int) topLeft.getY(), (int) width, (int) height);
        d.setColor(Color.black);
        d.drawRectangle((int) topLeft.getX(), (int) topLeft.getY(), (int) width, (int) height);
    }

    /**
     * Returns the color of the rectangle.
     *
     * @return the color of the rectangle
     */
    public Color getColor() {
        return color;
    }


    /**
     * Sets the color of the rectangle.
     *
     * @param color the new color of the rectangle
     */
    public void setColor(Color color) {
        this.color = color;
    }

    /**
     * An empty implementation of the timePassed() method from the interfaces.Sprite interface.
     * This method is not used by the geometry.Rectangle class.
     */
    public void timePassed() {

    }
}