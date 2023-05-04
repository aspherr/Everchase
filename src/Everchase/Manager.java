package Everchase;

import Everchase.levels.One;
import Everchase.levels.Two;
import city.cs.engine.World;

public class Manager extends World {

    private static int currentLevel;

    public void loadLevel(Window view, int level) {

        currentLevel = level;

        switch (level) {
            case 1 -> {
                One worldOne = new One(view);
                view.setWorld(worldOne);
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

    private void unloadLevel(World currentWorld) {
        currentWorld.stop();
    }

    public void loadNextLevel(Window view) {
        unloadLevel(view.getWorld());
        loadLevel(view, incrementLevel());
    }
}
