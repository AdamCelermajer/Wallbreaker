//332638592 Adam Celermajer
package game;

import biuoop.DrawSurface;
import biuoop.Sleeper;
import interfaces.Animation;

import java.awt.Color;

/**
 * The CountdownAnimation class is responsible for displaying a countdown animation on the screen.
 * It implements the Animation interface and defines methods for performing each frame of the animation.
 */
public class CountdownAnimation implements Animation {

    private SpriteCollection sprites;
    private double numOfSeconds;
    private int countFrom;
    private boolean stop;
    private Sleeper sleeper;

    /**
     * Constructs a CountdownAnimation object with the given duration, starting count, and sprite collection.
     *
     * @param numOfSeconds the duration of the countdown animation in seconds.
     * @param countFrom    the starting count value for the countdown.
     * @param gameScreen   the sprite collection representing the game screen.
     */
    public CountdownAnimation(double numOfSeconds, int countFrom, SpriteCollection gameScreen) {
        this.sprites = gameScreen;
        this.numOfSeconds = numOfSeconds;
        this.countFrom = countFrom;
        this.stop = false;
        this.sleeper = new Sleeper();
    }

    /**
     * Performs one frame of the countdown animation.
     *
     * @param d the DrawSurface on which to draw the animation frame.
     */
    public void doOneFrame(DrawSurface d) {
        this.sprites.drawAllOn(d);
        d.setColor(Color.BLACK);
        d.drawText(400, d.getHeight() / 2, Integer.toString(countFrom), 92);

        if (countFrom == 0) {
            this.stop = true;
        }

        this.sleeper.sleepFor((long) (numOfSeconds * 1000));
        this.countFrom -= 1;
    }

    /**
     * Checks if the countdown animation should stop.
     *
     * @return {@code true} if the animation should stop, {@code false} otherwise.
     */
    public boolean shouldStop() {
        return this.stop;
    }
}
