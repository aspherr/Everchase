package Everchase;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

public class Sword implements CollisionListener {

    @Override
    public void collide(CollisionEvent c) {
        if (c.getOtherBody() instanceof Enemy) {

            if (Player.getAttackingState()) {

                c.getReportingBody().destroy();
                ((Enemy) c.getOtherBody()).decrementHealth(Player.getCurrentPlayerState());

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
            }
        }
    }
}
