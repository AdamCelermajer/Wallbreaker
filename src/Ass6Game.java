// 332638592 Adam Celermajer

import biuoop.GUI;
import biuoop.KeyboardSensor;
import game.AnimationRunner;


import game.GameFlow;
import interfaces.LevelInformation;
import level.DirectHit;
import level.Vanilla;
import level.WideEasy;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * class representing the game of Assement 5d.
 */
public class Ass6Game {
    /**
     * The main method creates a new game and runs it.
     *
     * @param args an array of command-line arguments for the game
     */
    public static void main(String[] args) {
        GUI gui = new GUI("Arkanoid", 800, 600);
        KeyboardSensor keyboard = gui.getKeyboardSensor();
        AnimationRunner runner = new AnimationRunner(60, gui);
        GameFlow gf = new GameFlow(runner, keyboard, gui);

        // Create a HashMap to map level numbers to LevelInformation objects
        Map<Integer, LevelInformation> levelMap = new HashMap<>();
        levelMap.put(1, new DirectHit());
        levelMap.put(2, new WideEasy());
        levelMap.put(3, new Vanilla());

        // Create a list to hold the levels to run
        List<LevelInformation> levelsToRun = new ArrayList<>();
        int counter = 0;
        // If there are no arguments, add all levels to the list
        if (args.length == 0) {
            levelsToRun.addAll(levelMap.values());
        } else {
            // Otherwise, add the levels specified in the arguments
            for (String arg : args) {
                try {
                    int levelNumber = Integer.parseInt(arg);
                    LevelInformation level = levelMap.get(levelNumber);
                    if (level != null) {
                        levelsToRun.add(level);
                    }
                } catch (NumberFormatException e) {
                    // Ignore arguments that are not numbers
                }
            }
        }
        if (levelsToRun.isEmpty()) {
            levelsToRun.addAll(levelMap.values());
        }

        gf.runLevels(levelsToRun);
    }
}
