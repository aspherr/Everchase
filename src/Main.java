import javax.swing.JFrame;
// import city.cs.engine.DebugViewer;

import city.cs.engine.UserView;

public class Main {

    public static void everchase() {
        Game world = new Game();
        UserView view = new UserView(world, 900, 500);

        final JFrame frame = new JFrame("Everchase");
        frame.add(view);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLocationByPlatform(true);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.pack();

        // new DebugViewer(world, 900, 500);

        world.start();

    }

    public static void main(String[] args) throws Exception {
        System.out.println("booting up game...\nHave fun adventurer!");
        everchase();
    }
}
