package Everchase;
import city.cs.engine.World;


public class Game extends World {

    private final Player player;

    public Game() {
        player = new Player(this);
    }

    public Player getPlayer() {
        return player;
    }

}
