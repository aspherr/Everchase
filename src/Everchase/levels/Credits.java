package Everchase.levels;

import Everchase.Manager;
import Everchase.Window;

/**
 * <p>
 *  * Sets the window to black, to allow for credits to be presented
 */
public class Credits extends Manager {

    /**
     *  * Constructor for credits page
     */
    public Credits(Window view) {
        super();
        view.setBackgroundPath("res/sprites/environment/background.png");
        view.setPosY(0);
    }
}
