import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Objects;

public class Controller implements KeyListener {
    private static final float SPEED = 6.00f;
    
    private final Player player;

    public Controller(Player player) {
        this.player = player;
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

            player.setAttackingState(true);
            player.setIsHeavyAttack(false);

            if (Objects.equals(player.getCurrentPlayerState(), "idle-left")) {
                player.setNextPlayerState("light-attack-left");

            
            } else if (Objects.equals(player.getCurrentPlayerState(), "idle-right")) {
                player.setNextPlayerState("light-attack-right");
            }

        // heavy attack
        } else if (keyCode == KeyEvent.VK_E) {

            player.setAttackingState(true);
            player.setIsHeavyAttack(true);

            if (Objects.equals(player.getCurrentPlayerState(), "idle-left")) {
                player.setNextPlayerState("heavy-attack-left");
            
            } else if (Objects.equals(player.getCurrentPlayerState(), "idle-right")) {
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
