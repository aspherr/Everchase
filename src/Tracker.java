import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;

public class Tracker implements StepListener {

    private final Player yumiko;
    private final Enemy critter;

    public Tracker(Player yumiko, Enemy critter) {
        this.yumiko = yumiko;
        this.critter = critter;
    }

    @Override
    public void postStep(StepEvent post) {
        yumiko.configPolygons();
    }

    @Override
    public void preStep(StepEvent pre) {
        yumiko.animationManager(yumiko.getLinearVelocity(), yumiko.getPosition());
        critter.animationManager();
    }
    
}
