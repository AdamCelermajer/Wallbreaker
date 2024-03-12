// 332638592 Adam Celermajer
package level.Back;

import biuoop.DrawSurface;
import interfaces.Sprite;

import java.awt.Color;

/**
 * The VanillaBack class represents the background sprite for the Vanilla level.
 * It implements the Sprite interface and provides a method to draw the background on a draw surface.
 */
public class VanillaBack implements Sprite {
    /**
     * Constructs a VanillaBack object.
     */
    public VanillaBack() {
    }

    /**
     * Draws the background on the given draw surface.
     *
     * @param d the draw surface on which to draw the background
     */
    public void drawOn(DrawSurface d) {
        // Set the background color
        for (int i = 0; i <= 600; i += 5) {
            double offset = 255.0 / 600.0;
            d.setColor(new Color(0, (int) (offset * i), 0));
            d.fillRectangle(0, i, 800, 5);
        }

        // Draw the sun
        d.setColor(Color.YELLOW);
        d.fillCircle(100, 100, 50);

        // Draw sun rays
        for (int i = 0; i < 360; i += 15) {
            double angle = Math.toRadians(i);
            int lineLength = 70;
            int endX = (int) (100 + lineLength * Math.cos(angle));
            int endY = (int) (100 + lineLength * Math.sin(angle));
            d.drawLine(100, 100, endX, endY);
        }

        // Draw the tree trunk
        d.setColor(new Color(139, 69, 19));  // brown color
        d.fillRectangle(250, 400, 50, 200);

        // Draw the tree leaves
        d.setColor(Color.GREEN);
        for (int i = 0; i < 3; i++) {
            d.fillCircle(275, 375 - i * 20, 75 - i * 10);
        }

        // Draw the grass
        for (int i = 0; i <= 800; i += 20) {
            d.setColor(Color.GREEN.darker());
            d.drawLine(i, 600, i + 10, 580);
        }
    }


    @Override
    public void timePassed() {
    }
}
