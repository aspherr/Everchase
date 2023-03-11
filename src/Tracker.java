import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;

public class Tracker implements StepListener {

    private Player yumiko;
    private Enemy critter;

    public Tracker(Player yumiko, Enemy criiter) {
        this.yumiko = yumiko;
        this.critter = criiter;
    }

    @Override
    public void postStep(StepEvent post) {}

    @Override
    public void preStep(StepEvent pre) {
        yumiko.animationManager(yumiko.getLinearVelocity(), yumiko.getPosition());
        critter.animationManager();
    }
    
}
