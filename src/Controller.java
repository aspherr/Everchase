import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener {
    private static final float SPEED = 6.00f;
    
    private Player yumiko;

    public Controller(Player yumiko) {
        this.yumiko = yumiko;
    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_D) {
            yumiko.setAttackingState(false);
            yumiko.startWalking(SPEED);
           
        } else if (keyCode == KeyEvent.VK_A) {
            yumiko.setAttackingState(false);
            yumiko.startWalking(-SPEED);
        
        } else if (keyCode == KeyEvent.VK_W) {
            yumiko.setAttackingState(false);
            yumiko.jump(SPEED*2.00f);

            if (keyCode == KeyEvent.VK_A) {
                yumiko.setNextPlayerState("jump-left");
            } 
        
        } else if (keyCode == KeyEvent.VK_F) {

            yumiko.setAttackingState(true);

            if (yumiko.getCurrentPlayerState() == "idle-left") {
                yumiko.setNextPlayerState("light-attack-left");
            
            } else if (yumiko.getCurrentPlayerState() == "idle-right") {
                yumiko.setNextPlayerState("light-attack-right");
            }

        } else if (keyCode == KeyEvent.VK_E) {

            yumiko.setAttackingState(true);

            if (yumiko.getCurrentPlayerState() == "idle-left") {
                yumiko.setNextPlayerState("heavy-attack-left");
            
            } else if (yumiko.getCurrentPlayerState() == "idle-right") {
                yumiko.setNextPlayerState("heavy-attack-right");
            }
        } 
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_D) {
            yumiko.stopWalking();
            yumiko.startWalking(0); 
        
        } else if (keyCode == KeyEvent.VK_A) {
            yumiko.stopWalking();
            yumiko.startWalking(0);  
        } 
    }
}
