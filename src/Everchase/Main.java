package Everchase;

import city.cs.engine.World;

import javax.swing.JFrame;

// import of debugger tool; uncomment to use
// import city.cs.engine.DebugViewer;


public class Main {

    private static Manager manager;
    private static Window view;

    public static void everchase() {
        World world = new World();
        view = new Window(world, 900, 500);

        manager = new Manager();
        manager.loadLevel(view, 1);

        final JFrame frame = new JFrame("Everchase");
        frame.add(view);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.pack();

        // optional: line below creates a debugger window; uncomment to use
        // new DebugViewer(world, 900, 500);

        world.start();
        view.requestFocus();
    }

    public static Window getView() {
        return view;
    }

    public static Manager getManager() {
        return manager;
    }

    public static void main(String[] args) throws Exception {
        System.out.println("booting up game...\nHave fun, adventurer!");
        everchase();
    }
}
