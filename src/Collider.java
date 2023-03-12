import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

public class Collider implements CollisionListener {

    @Override
    public void collide(CollisionEvent c) {

        // increments No. of coins held and destroys coin object when player collides with a coin 
        if (c.getOtherBody() instanceof Coin) {
            ((Coin) c.getOtherBody()).incrementCoinsHeld();
            c.getOtherBody().destroy();
            
        } else if (c.getOtherBody() instanceof Enemy) {

            // prevents enemy from sliding away
            ((Enemy) c.getOtherBody()).startWalking(0);

            // attacking the enemy collision
            if (((Player) c.getReportingBody()).getAttackingState() == true) {
                
                // decrement enemy's health and if health <= 0, then destroy enemy object
                ((Enemy) c.getOtherBody()).decrementHealth((Player) c.getReportingBody());
                if (((Enemy) c.getOtherBody()).getHealth() <= 0) {
                    ((Enemy) c.getOtherBody()).destroy();
                }   

            // enemy attacking the player collision
            } else {
                
                // decrement player's health and if health <= 0, then destroy player object
                ((Player) c.getReportingBody()).decrementHealth();
                if (((Player) c.getReportingBody()).getHealth() <= 0) {
                    ((Player) c.getReportingBody()).destroy();
                }
            } 
        }
    }
}
