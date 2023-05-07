package Everchase;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import org.jbox2d.common.Vec2;

public class Collider implements CollisionListener {

    @Override
    public void collide(CollisionEvent c) {

        if (c.getOtherBody() instanceof Jumper) {

            c.getReportingBody().setPosition(new Vec2(22.00f, 10.00f));
            c.getOtherBody().destroy();
            Jumper.setIsDestroyed(true);

        } else if (c.getOtherBody() instanceof Teleporter) {

            c.getOtherBody().destroy();
            c.getReportingBody().destroy();
            Main.getManager().loadNextLevel(Main.getView());
            ((Player) c.getReportingBody()).setLevelComplete(false);

        } else if (c.getOtherBody() instanceof Enemy) {

            // prevents enemy from sliding away
            ((Enemy) c.getOtherBody()).startWalking(0);

            // enemy attacking the player
            ((Player) c.getReportingBody()).decrementHealth();
            Player.incrementScore(-15);

            // if health <= 0, then destroy the player object
            if (Player.getHealth() <= 0) {
                c.getReportingBody().destroy();
            }

        }
    }
}
