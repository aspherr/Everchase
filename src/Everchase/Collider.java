package Everchase;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

public class Collider implements CollisionListener {

    @Override
    public void collide(CollisionEvent c) {

        // increments No. of coins held and destroys coin object when player collides with a coin 
        if (c.getOtherBody() instanceof Coin) {
            ((Coin) c.getOtherBody()).incrementCoinsHeld();
            ((Player) c.getReportingBody()).incrementScore(20);
            c.getOtherBody().destroy();

        } else if (c.getOtherBody() instanceof Teleporter) {
            c.getOtherBody().destroy();
            c.getReportingBody().destroy();
            Main.getManager().loadNextLevel(Main.getView());


        } else if (c.getOtherBody() instanceof Enemy) {

            // prevents enemy from sliding away
            ((Enemy) c.getOtherBody()).startWalking(0);

            // attacking the enemy collision
            if (((Player) c.getReportingBody()).getAttackingState()) {
                
                // decrement enemy's health and if health <= 0, then destroy enemy object
                ((Enemy) c.getOtherBody()).decrementHealth((Player) c.getReportingBody());
                if (((Enemy) c.getOtherBody()).getHealth() <= 0) {
                    ((Player) c.getReportingBody()).incrementScore(100);
                    c.getOtherBody().destroy();
                }   

            // enemy attacking the player collision
            } else {
                ((Player) c.getReportingBody()).decrementHealth();
                ((Player) c.getReportingBody()).incrementScore(-15);

                // if health <= 0, then destroy the player object
                if (((Player) c.getReportingBody()).getHealth() <= 0) {
                    c.getReportingBody().destroy();
                }
            } 
        }
    }
}
