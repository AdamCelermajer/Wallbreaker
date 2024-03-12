// 332638592 Adam Celermajer
package game;

import biuoop.DrawSurface;
import biuoop.KeyboardSensor;
import interfaces.Animation;

/**
 * The KeyPressStoppableAnimation class represents an animation that can be stopped by a key press.
 * It implements the Animation interface and provides methods
 * for displaying frames and determining when the animation should stop.
 */
public class KeyPressStoppableAnimation implements Animation {
    private KeyboardSensor sensor;
    private String key;
    private Animation animation;
    private boolean isAlreadyPressed;

    /**
     * Constructs a KeyPressStoppableAnimation object with the specified keyboard sensor, key, and animation.
     *
     * @param sensor    the keyboard sensor used to detect key presses
     * @param key       the key that stops the animation when pressed
     * @param animation the animation to be displayed
     */
    public KeyPressStoppableAnimation(KeyboardSensor sensor, String key, Animation animation) {
        this.sensor = sensor;
        this.key = key;
        this.animation = animation;
        this.isAlreadyPressed = true;
    }

    /**
     * Displays one frame of the animation on the given draw surface.
     *
     * @param d the draw surface on which to display the frame
     */
    public void doOneFrame(DrawSurface d) {
        animation.doOneFrame(d);

        if (this.sensor.isPressed(this.key)) {
            if (!this.isAlreadyPressed) {
                this.animation = null;
            }
        } else {
            this.isAlreadyPressed = false;
        }
    }

    /**
     * Determines whether the animation should stop.
     *
     * @return true if the animation should stop, false otherwise
     */
    public boolean shouldStop() {
        return this.animation == null;
    }
}
