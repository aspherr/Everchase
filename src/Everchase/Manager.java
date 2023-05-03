package Everchase;

import Everchase.levels.One;
import Everchase.levels.Two;
import city.cs.engine.World;

public class Manager extends World {

    public void loadLevel(Window view, int level) {

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


    private void unloadLevel(World currentWorld) {
        currentWorld.stop();
    }

    private void loadNextLevel(Window view, int nextLevel) {
        unloadLevel(view.getWorld());
        loadLevel(view, nextLevel);
    }
}
