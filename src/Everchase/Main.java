package Everchase;

import city.cs.engine.World;

import javax.swing.JFrame;
import java.io.IOException;

/**
 * @author      Huzaifa Syed
 * * Handles all game logic
 */
public class Main {

    private static final World world = new World();
    private static final Manager manager = new Manager();
    private static final Window view = new Window(world, 900, 500);;

    /**
     *  * Creates the game
     */
    public static void everchase() throws IOException {

        manager.loadLevel(view, manager.incrementLevel());

        final JFrame frame = new JFrame("Everchase");
        frame.add(view);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.pack();

        world.start();
        view.requestFocus();
    }

    /**
     *  @returns returns the current window
     */
    public static Window getView() {
        return view;
    }

    /**
     * @returns returns the manager object
     */
    public static Manager getManager() {
        return manager;
    }

    /**
     *  * Main method
     */
    public static void main(String[] args) throws Exception {
        System.out.println("booting up game...\nHave fun, adventurer!");
        everchase();
    }
}
