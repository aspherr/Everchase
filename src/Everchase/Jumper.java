package Everchase;

import city.cs.engine.BoxShape;
import city.cs.engine.World;

/**
 * <p>
 *  * Used to differentiate between teleporters
 */
public class Jumper extends Teleporter{

    private static boolean isDestroyed = false;

    /**
     *  * Constructor for the jumper portal in level 3
     */
    public Jumper(World world, BoxShape body) {
        super(world, body);
    }

    /**
     *  * Used to check if the teleporter has been used
     */
    public static void setIsDestroyed(boolean state) {
        isDestroyed = state;
    }

    /**
     *  @return returns the state of the teleporter
     */
    public static boolean getIsDestroyed() {
        return isDestroyed;
    }
}
