// 332638592 Adam Celermajer
package game;

import biuoop.GUI;
import biuoop.KeyboardSensor;
import interfaces.Animation;
import interfaces.LevelInformation;

import java.util.List;

/**
 * The GameFlow class is responsible for running the game levels and handling the flow of the game.
 * It initializes the necessary components, runs each level, and displays the end screen when the game is over.
 */
public class GameFlow {
    private GUI gui;
    private Counter score;
    private AnimationRunner ar;
    private KeyboardSensor ks;

    /**
     * Constructs a GameFlow object with the specified AnimationRunner, KeyboardSensor, and GUI.
     *
     * @param ar  the AnimationRunner responsible for running animations.
     * @param ks  the KeyboardSensor for detecting key presses.
     * @param gui the GUI for displaying the game.
     */
    public GameFlow(AnimationRunner ar, KeyboardSensor ks, GUI gui) {
        this.ar = ar;
        this.ks = ks;
        this.gui = gui;
        this.score = new Counter(0);
    }

    /**
     * Runs the given list of level information, one level at a time.
     *
     * @param levels the list of level information to run.
     */
    public void runLevels(List<LevelInformation> levels) {
        int win = 0;
        for (LevelInformation levelInfo : levels) {
            // Create a new GameLevel with the current level information and other components
            GameLevel level = new GameLevel(levelInfo, this.ks, this.ar, this.gui, this.score);

            // Initialize the level
            level.initialize();

            // Run the level until it should stop
            while (!level.shouldStop()) {
                win = level.run();
            }

            // Check if the player won or lost the level
            if (win == 1) {
                // Display the "Game Over" screen with the final score
                Animation endScreen = new EndScreen(false, score.getValue());
                Animation endScreenWithKeyStop = new KeyPressStoppableAnimation(this.ks, "space", endScreen);
                ar.run(endScreenWithKeyStop);
                System.exit(0);
            }
        }

        // Display the "Game Over" screen with the final score
        Animation endScreen = new EndScreen(true, score.getValue());
        Animation endScreenWithKeyStop = new KeyPressStoppableAnimation(this.ks, "space", endScreen);
        ar.run(endScreenWithKeyStop);
        System.exit(0);
    }
}
