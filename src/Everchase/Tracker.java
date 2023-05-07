package Everchase;

import Everchase.levels.One;
import Everchase.levels.Three;
import Everchase.levels.Two;
import city.cs.engine.BoxShape;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

import java.util.Objects;

/**
 * <p>
 *  * Constantly monitors certain actions every fame
 */

public class Tracker implements StepListener {

    private final Player player;
    private final Enemy[] critterCollection;

    private final Teleporter teleporter;

    private final Jumper jumpTeleporter;

    public Tracker(World world, Player player, Enemy[] critter) {
        this.player = player;
        this.critterCollection = critter;
        this.teleporter = new Teleporter(world, new BoxShape(0.25f, 1.75f));
        this.jumpTeleporter = new Jumper(world, new BoxShape(0.25f, 1.75f));
    }


    @Override
    public void postStep(StepEvent post) {

        if (Player.getAttackingState() && !Player.isInAir()) {
            if (Objects.equals(player.getDirection(), "right") &&
                    player.getProjectile().getPosition().x > (player.getPosition().x + 5.00f)) {

                player.getProjectile().destroy();

            } else if (Objects.equals(player.getDirection(), "left") &&
                    player.getProjectile().getPosition().x < (player.getPosition().x - 5.00f)) {

                player.getProjectile().destroy();
            }
        }

        player.animationManager(player.getLinearVelocity());

        for (Enemy critter : critterCollection) {
            critter.animationManager();
        }

        if (Manager.getLevel() == 3 && Currency.getCoinsHeld() == 3) {

            this.jumpTeleporter.setPosition(new Vec2(0.00f, 10.50f));
            jumpTeleporter.animateTeleporter();

            Jumper.setIsCreated(!Jumper.getIsDestroyed());

        } else if (Currency.getCoinsHeld() == Currency.getMaxCoins()) {

            if (Manager.getWorld() instanceof One) {
                this.teleporter.setPosition(new Vec2(20.00f, -7.00f));

            } else if (Manager.getWorld() instanceof Two) {
                this.teleporter.setPosition(new Vec2(20.00f, -8.00f));

            } else if (Manager.getWorld() instanceof Three) {
                this.teleporter.setPosition(new Vec2(21.00f, 10.00f));
            }

            teleporter.animateTeleporter();
            Teleporter.setIsCreated(true);
        }
    }

    @Override
    public void preStep(StepEvent pre) {

    }
}
