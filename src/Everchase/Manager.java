package Everchase;

// import of debugger tool; uncomment to use
// import city.cs.engine.DebugViewer;

import Everchase.levels.One;
import Everchase.levels.Two;
import city.cs.engine.World;


public class Manager extends World {

    private static One worldOne;
    private static Two worldTwo;
    private static int currentLevel;


    public void loadLevel(Window view, int level) {

        currentLevel = level;

        switch (level) {
            case 1 -> {
                worldOne = new One(view);
                view.setWorld(worldOne);

                // optional: line below creates a debugger window; uncomment to use
                // new DebugViewer(worldOne, 900, 500);

                worldOne.start();
            }

            case 2 -> {
                Two worldTwo = new Two(view);
                view.setWorld(worldTwo);
                worldTwo.start();

            }
        }
    }

    private int incrementLevel() {
        currentLevel++;
        return currentLevel;
    }

    public static World getWorld() {
        switch (currentLevel) {
            case 1-> {return worldOne;}
            case 2 -> {return worldTwo;}
        }

        return null;
    }

    private void unloadLevel(World currentWorld) {
        currentWorld.stop();
    }

    public void loadNextLevel(Window view) {
        unloadLevel(view.getWorld());
        loadLevel(view, incrementLevel());
    }
}
