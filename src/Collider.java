import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

public class Collider implements CollisionListener {

    @Override
    public void collide(CollisionEvent c) {

        if (c.getOtherBody() instanceof Coin) {
            ((Coin) c.getOtherBody()).incrementCoinsHeld();
            c.getOtherBody().destroy();
            
        } else if (c.getOtherBody() instanceof Enemy) {

            ((Enemy) c.getOtherBody()).startWalking(0);

            if (((Player) c.getReportingBody()).getAttackingState() == true) {
                
                ((Enemy) c.getOtherBody()).decrementHealth((Player) c.getReportingBody());
                if (((Enemy) c.getOtherBody()).getHealth() <= 0) {
                    ((Enemy) c.getOtherBody()).destroy();
                }
            
            } else {
                ((Player) c.getReportingBody()).decrementHealth();
                if (((Player) c.getReportingBody()).getHealth() <= 0) {
                    ((Player) c.getReportingBody()).destroy();
                }
            } 
        }
    }
}
