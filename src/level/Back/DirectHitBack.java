//332638592 Adam Celermajer
package level.Back;

import biuoop.DrawSurface;
import interfaces.Sprite;

import java.awt.Color;


/**
 * The DirectHitBack class implements the Sprite interface and represents the background of the "Direct Hit" level.
 * It provides methods for drawing the background and updating its state over time.
 */
public class DirectHitBack implements Sprite {
    private double angle;
    private int cloudOffset;

    /**
     * Constructs a DirectHitBack object.
     * It initializes the angle and cloudOffset properties.
     */
    public DirectHitBack() {
        this.angle = 0;
        this.cloudOffset = 0;
    }

    /**
     * Draws the background on the given DrawSurface.
     *
     * @param d the DrawSurface on which to draw the background.
     */
    public void drawOn(DrawSurface d) {
        // Set the background color

        for (int i = 0; i <= 600; i += 5) {

            double ofset = 255.0 / 600.0;
            d.setColor(new Color(0, 0, (int) (ofset * i)));
            d.fillRectangle(0, i, 800, 5);

        }
        // Draw the clouds
        for (int i = 0; i < 3; i++) {
            int cloudX = (i * 300 + this.cloudOffset) % 900 - 100; // calculate cloud position with offset
            d.setColor(new Color(220, 220, 220)); // light gray
            d.fillRectangle(cloudX, 80, 80, 50);
            d.setColor(new Color(200, 200, 200)); // medium gray
            d.fillCircle(cloudX, 100, 50);
            d.fillCircle(cloudX + 80, 100, 50);
            d.setColor(new Color(180, 180, 180)); // dark gray
            d.fillCircle(cloudX + 40, 70, 50);
        }

        // Draw the cookie base with shading
        for (int i = 200; i > 0; i--) {
            int shade = 210 - (200 - i) / 2; // calculate shading factor
            d.setColor(new Color(shade, shade, 140)); // RGB for tan color with shading
            d.fillCircle(400, 300, i);
        }

        // Draw the chocolate chips
        d.setColor(new Color(125, 125, 125));
        int radius = 100; // radius of rotation
        for (int i = 0; i < 5; i++) {
            double radian = Math.toRadians(this.angle + i * 72); // 72 degree intervals for 5 chips
            int x = 400 + (int) (radius * Math.cos(radian));
            int y = 300 + (int) (radius * Math.sin(radian));
            d.fillCircle(x, y, 20);
        }
    }

    @Override
    public void timePassed() {
        this.angle += 1; // adjust this value to change the speed of rotation
        if (this.angle >= 360) {
            this.angle -= 360;
        }

        this.cloudOffset += 1; // adjust this value to change the speed of the clouds
        if (this.cloudOffset >= 300) {
            this.cloudOffset -= 300;
        }
    }
}
