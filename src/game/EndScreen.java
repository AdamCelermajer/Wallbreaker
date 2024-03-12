//332638592 Adam Celermajer
package game;

import biuoop.DrawSurface;
import interfaces.Animation;
import java.awt.Color;

/**
 * The EndScreen class implements the Animation interface and represents the screen displayed at the end of the game.
 * It provides methods for performing each frame of the animation and determining if the animation should stop.
 */
public class EndScreen implements Animation {
    private int score;
    private boolean win;

    /**
     * Constructs an EndScreen object with the given win status and score.
     *
     * @param win   {@code true} if the player won the game, {@code false} if the player lost the game.
     * @param score the final score of the player.
     */
    public EndScreen(boolean win, int score) {
        this.win = win;
        this.score = score;
    }

    /**
     * Performs one frame of the end screen animation.
     *
     * @param d the DrawSurface on which to draw the end screen.
     */
    public void doOneFrame(DrawSurface d) {
        d.setColor(Color.BLACK);
        if (win) {
            d.drawText(10, d.getHeight() / 2, "You Win! Your score is " + score, 32);
        } else {
            d.drawText(10, d.getHeight() / 2, "Game Over. Your score is " + score, 32);
        }
    }

    /**
     * Checks if the end screen animation should stop.
     *
     * @return always {@code false}, as the end screen animation never stops.
     */
    public boolean shouldStop() {
        return false;
    }
}
