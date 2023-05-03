import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;

public class Tracker implements StepListener {

    private final Player player;
    private final Enemy critter;

    public Tracker(Player player, Enemy critter) {
        this.player = player;
        this.critter = critter;
    }

    @Override
    public void postStep(StepEvent post) {
        player.configPolygons();
        player.animationManager(player.getLinearVelocity(), player.getPosition());
    }

    @Override
    public void preStep(StepEvent pre) {
        player.animationManager(player.getLinearVelocity(), player.getPosition());
        critter.animationManager();
    }
    
}
