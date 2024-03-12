// 332638592 Adam Celermajer
package level.Back;

import biuoop.DrawSurface;
import interfaces.Sprite;


/**
 * The WideEasyBack class represents the background sprite for the WideEasy level.
 * It implements the Sprite interface and provides a method to draw the background on a draw surface.
 */
public class WideEasyBack implements Sprite {
    private int numLines = 50; // The number of lines in the star
    private double rotation = 0; // The current rotation of the star
    private double rotationSpeed = Math.PI / 180; // The speed of rotation
    private java.awt.Color color = java.awt.Color.BLACK; // The color of the lines
    private int numCircles = 50; // The number of circles in the gradient
    private java.awt.Color backgroundColor = new java.awt.Color(255, 255, 255, 255); // The color of the background

    /**
     * Draws the background on the given draw surface.
     *
     * @param d the draw surface on which to draw the background
     */
    public void drawOn(DrawSurface d) {
        // Draw the background
        for (int i = numCircles; i >= 0; i--) {
            int shade = (int) (255 * i / numCircles); // Calculate the shade
            d.setColor(new java.awt.Color(shade, shade, shade));
            d.fillCircle(400, 300, i * 600 / numCircles);
        }

        // Draw the star
        d.setColor(color);
        for (int i = 0; i < numLines; i++) {
            double angle = rotation + 2 * Math.PI * i / numLines;
            int x = 400 + (int) (300 * Math.cos(angle));
            int y = 300 + (int) (300 * Math.sin(angle));
            d.drawLine(400, 300, x, y);
        }
    }

    @Override
    public void timePassed() {
        rotation += rotationSpeed;
    }
}

