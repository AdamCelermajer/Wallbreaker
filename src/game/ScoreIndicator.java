// 332638592 Adam Celermajer
package game;

import biuoop.DrawSurface;
import geometry.Rectangle;
import interfaces.Sprite;

import java.awt.Color;

/**
 * The game.ScoreIndicator class represents a sprite that displays the score on a rectangle.
 */
public class ScoreIndicator implements Sprite {

    private String levelName;
    private Rectangle rectangle;
    private Counter score;

    /**
     * Constructs a new game.ScoreIndicator object with the specified rectangle and score.
     *
     * @param rectangle the rectangle representing the background of the score indicator
     * @param score     the game.Counter object representing the score to be displayed
     * @param levelName String representing the level name
     */
    public ScoreIndicator(Rectangle rectangle, Counter score, String levelName) {
        this.rectangle = rectangle;
        this.score = score;
        this.levelName = levelName;
    }

    /**
     * Draws the score indicator on the given DrawSurface.
     *
     * @param d the DrawSurface on which to draw the score indicator
     */
    @Override
    public void drawOn(DrawSurface d) {
        this.rectangle.drawOn(d);
        d.setColor(Color.BLACK);
        d.drawText(400, 15, "Score: " + this.score.toString(), 15);
        d.drawText(600, 15, "Level Name: " + this.levelName, 15);
    }

    @Override
    public void timePassed() {
        // Not used in this implementation
    }
}
