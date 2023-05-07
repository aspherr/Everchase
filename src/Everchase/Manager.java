package Everchase;

// import of debugger tool; uncomment to use
// import city.cs.engine.DebugViewer;

import Everchase.levels.Credits;
import Everchase.levels.One;
import Everchase.levels.Three;
import Everchase.levels.Two;
import city.cs.engine.SoundClip;
import city.cs.engine.World;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * <p>
 *  * handles the switching between different levels
 */
public class Manager extends World {

    private static One worldOne;
    private static Two worldTwo;
    private static Three worldThree;

    private static int currentLevel = 0;

    /**
     *  * Used to play background music between the levels
     */
    public SoundClip bgm;
    private String bgmFilePath;

    private boolean resetOccurred = false;

    private static boolean gameComplete = false;

    /**
     *  * Used to load a specified level
     */
    public void loadLevel(Window view, int level) throws IOException {

        currentLevel = level;

        switch (level) {
            case 1 -> {
                worldOne = new One(view);
                bgmFilePath = "res/sfx/bgm/level-1-bgm.wav";
                view.setWorld(worldOne);

                // optional: line below creates a debugger window; uncomment to use
                // new DebugViewer(worldOne, 900, 500);

                worldOne.start();
            }

            case 2 -> {
                worldTwo = new Two(view);
                bgmFilePath = "res/sfx/bgm/level-2-bgm.wav";
                view.setWorld(worldTwo);

                // optional: line below creates a debugger window; uncomment to use
                // new DebugViewer(worldOne, 900, 500);

                worldTwo.start();
            }

            case 3 -> {
                worldThree = new Three(view);
                bgmFilePath = "res/sfx/bgm/level-3-bgm.wav";
                view.setWorld(worldThree);

                // optional: line below creates a debugger window; uncomment to use
                // new DebugViewer(worldOne, 900, 500);

                worldThree.start();
            }

            case 4 -> {
                Reader scoreManager = new Reader("src/Everchase/scores.txt");
                scoreManager.writeScore(Player.getScore());
                gameComplete = true;

                Credits creditsPage = new Credits(view);
                view.setWorld(creditsPage);
                creditsPage.start();

            }
        }

        try {

            if (currentLevel > 1 || resetOccurred) {
                bgm.stop();
            }

            bgm = new SoundClip(bgmFilePath);
            bgm.setVolume(0.05f);
            bgm.loop();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }
    }

    /**
     *  * Increments to the next level
     */
    public int incrementLevel() {
        currentLevel++;
        return currentLevel;
    }

    /**
     * @return returns the current level
     */
    public static int getLevel() {
        return currentLevel;
    }

    /**
     * @return returns the world of the current level
     */
    public static World getWorld() {
        switch (currentLevel) {
            case 1-> {
                return worldOne;
            }

            case 2 -> {
                return worldTwo;
            }

            case 3-> {
                return worldThree;
            }
        }

        return null;
    }

    /**
     *  * Pauses current world
     */
    private void unloadLevel(World currentWorld) {
        currentWorld.stop();
    }

    /**
     *  * Resets the entire level back to its starting state
     */
    public void resetLevel(World currentWorld, Window view) throws IOException {
        resetOccurred = true;
        unloadLevel(currentWorld);
        loadLevel(view, currentLevel);
    }

    /**
     *  * Used to load the next level
     */
    public void loadNextLevel(Window view) throws IOException {
        unloadLevel(view.getWorld());
        loadLevel(view, incrementLevel());
    }

    /**
     *  * Used to check if all levels have been completed
     */
    public static boolean getGameComplete() {
        return gameComplete;
    }
}
