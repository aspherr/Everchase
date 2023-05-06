package Everchase;

import city.cs.engine.BoxShape;
import city.cs.engine.StepEvent;
import city.cs.engine.StepListener;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

import java.util.Objects;

public class Tracker implements StepListener {

    private final Player player;
    private final Enemy[] critterCollection;

    private final Teleporter teleporter;

    public Tracker(World world, Player player, Enemy[] critter) {
        this.player = player;
        this.critterCollection = critter;
        this.teleporter = new Teleporter(world, new BoxShape(0.25f, 1.75f));
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

        if (Coin.getCoinsHeld() == Coin.getMaxCoins()) {
            teleporter.setPosition(new Vec2(20.00f, -7.00f));
            teleporter.animateTeleporter();
            teleporter.setIsCreated(true);
        }
    }

    @Override
    public void preStep(StepEvent pre) {

    }
}
