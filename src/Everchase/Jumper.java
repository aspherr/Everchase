package Everchase;

import city.cs.engine.BoxShape;
import city.cs.engine.World;

public class Jumper extends Teleporter{

    private static boolean isDestroyed = false;
    public Jumper(World world, BoxShape body) {
        super(world, body);
    }

    public static void setIsDestroyed(boolean state) {
        isDestroyed = state;
    }

    public static boolean getIsDestroyed() {
        return isDestroyed;
    }
}
