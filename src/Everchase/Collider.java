package Everchase;

import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;
import city.cs.engine.SoundClip;
import org.jbox2d.common.Vec2;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

public class Collider implements CollisionListener {

    @Override
    public void collide(CollisionEvent c) {

        if (c.getOtherBody() instanceof Jumper) {

            try {
                SoundClip sfx = new SoundClip("res/sfx/teleport.wav");
                sfx.setVolume(0.5f);
                sfx.play();

            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                System.out.println(e);
            }

            c.getReportingBody().setPosition(new Vec2(22.00f, 10.00f));
            c.getOtherBody().destroy();
            Jumper.setIsDestroyed(true);

        } else if (c.getOtherBody() instanceof Teleporter) {

            try {
                SoundClip sfx = new SoundClip("res/sfx/teleport.wav");
                sfx.setVolume(0.5f);
                sfx.play();

            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                System.out.println(e);
            }

            c.getOtherBody().destroy();
            c.getReportingBody().destroy();

            try {
                Main.getManager().loadNextLevel(Main.getView());

            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            ((Player) c.getReportingBody()).setLevelComplete(false);

        } else if (c.getOtherBody() instanceof Enemy) {

            try {
                SoundClip sfx = new SoundClip("res/sfx/damaged.wav");
                sfx.setVolume(0.25f);
                sfx.play();

            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                System.out.println(e);
            }

            // prevents enemy from sliding away
            ((Enemy) c.getOtherBody()).startWalking(0);

            // enemy attacking the player
            ((Player) c.getReportingBody()).decrementHealth();
            Player.incrementScore(-15);

            // if health <= 0, then destroy the player object
            if (Player.getHealth() <= 0) {
                c.getReportingBody().destroy();

                try {
                    Main.getManager().resetLevel(Manager.getWorld(), Main.getView());

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                Coin.resetCoinsHeld();
                Player.resetHealth();
                Player.resetScore();
            }
        }
    }
}
