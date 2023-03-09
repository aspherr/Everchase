import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;

public class Tracker implements StepListener {

    private Player yumiko;

    public Tracker(Player yumiko) {
        this.yumiko = yumiko;
    }

    @Override
    public void postStep(StepEvent post) {
        yumiko.animationManager(yumiko.getLinearVelocity());
    }

    @Override
    public void preStep(StepEvent pre) {

    }
    
}
