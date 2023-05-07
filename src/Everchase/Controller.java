package Everchase;

import city.cs.engine.SoundClip;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.util.Objects;


/**
 * <p>
 *  * Handles all key inputs
 */
public class Controller implements KeyListener {
    private static final float SPEED = 6.00f;
    
    private Player player;

    /**
     *  * Constructor for controller
     */
    public Controller(Player player) {
        this.player = player;
    }

    /**
     *  * Updates the player object when switching between levels
     */
    public void updatePlayer(Player playerObj) {
        player = playerObj;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        // walk right
        if (keyCode == KeyEvent.VK_D) {
            player.setAttackingState(false);
            player.startWalking(SPEED);
        
        // walk left 
        } else if (keyCode == KeyEvent.VK_A) {
            player.setAttackingState(false);
            player.startWalking(-SPEED);
        
        // jump
        } else if (keyCode == KeyEvent.VK_W) {
            player.setAttackingState(false);
            player.jump(SPEED*2.00f);

            // allows for change in direction mid-jump
            if (keyCode == KeyEvent.VK_A) {
                player.setNextPlayerState("jump-left");
            }

        // light attack
        } else if (keyCode == KeyEvent.VK_F) {

            try {
                SoundClip sfx = new SoundClip("res/sfx/sword-slash.wav");
                sfx.setVolume(0.25f);
                sfx.play();

            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException err) {
                System.out.println(err);
            }

            if (Objects.equals(Player.getCurrentPlayerState(), "idle-left")) {
                player.setNextPlayerState("light-attack-left");

            
            } else if (Objects.equals(Player.getCurrentPlayerState(), "idle-right")) {
                player.setNextPlayerState("light-attack-right");
            }

        // heavy attack
        } else if (keyCode == KeyEvent.VK_E) {

            try {
                SoundClip sfx = new SoundClip("res/sfx/sword-slash.wav");
                sfx.setVolume(0.25f);
                sfx.play();

            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException err) {
                System.out.println(err);
            }

            if (Objects.equals(Player.getCurrentPlayerState(), "idle-left")) {
                player.setNextPlayerState("heavy-attack-left");
            
            } else if (Objects.equals(Player.getCurrentPlayerState(), "idle-right")) {
                player.setNextPlayerState("heavy-attack-right");
            }
        } 
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_D) {
            player.stopWalking();
            player.startWalking(0);
        
        } else if (keyCode == KeyEvent.VK_A) {
            player.stopWalking();
            player.startWalking(0);
        }
    }
}
