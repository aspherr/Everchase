package Everchase;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

public class Collider implements CollisionListener {

    @Override
    public void collide(CollisionEvent c) {


        if (c.getOtherBody() instanceof Teleporter) {
            c.getOtherBody().destroy();
            c.getReportingBody().destroy();
            Main.getManager().loadNextLevel(Main.getView());


        } else if (c.getOtherBody() instanceof Enemy) {

            // prevents enemy from sliding away
            ((Enemy) c.getOtherBody()).startWalking(0);

            // attacking the enemy collision
            if (((Player) c.getReportingBody()).getAttackingState()) {

                ((Enemy) c.getOtherBody()).decrementHealth((Player) c.getReportingBody());

                // if health <= 0, then destroy the enemy object
                if (((Enemy) c.getOtherBody()).getHealth() <= 0) {
                    Player.incrementScore(100);

                    if (Player.getHealth() < 3) {
                        Heart heart = new Heart(Manager.getWorld());
                        heart.setPosition(((Enemy) c.getOtherBody()).getPosition());
                        heart.animateHeart();
                    }

                    c.getOtherBody().destroy();
                }   

            // enemy attacking the player collision
            } else {
                ((Player) c.getReportingBody()).decrementHealth();
                Player.incrementScore(-15);

                // if health <= 0, then destroy the player object
                if (Player.getHealth() <= 0) {
                    c.getReportingBody().destroy();
                }
            } 
        }
    }
}
