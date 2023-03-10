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
            yumiko.startWalking(SPEED);
           
        } else if (keyCode == KeyEvent.VK_A) {
            yumiko.startWalking(-SPEED);
        
        } else if (keyCode == KeyEvent.VK_W) {
            yumiko.jump(SPEED*2.00f);

            if (keyCode == KeyEvent.VK_A) {
                yumiko.setPlayerState("jump-left");
            }
        
        } else if (keyCode == KeyEvent.VK_F) {
            yumiko.setAttackType("light");
            yumiko.attack();
        
        } else if (keyCode == KeyEvent.VK_E) {
            yumiko.setAttackType("heavy");
            yumiko.attack();
        
        } else if (keyCode == KeyEvent.VK_R) {
            yumiko.setAttackType("combo");
            yumiko.attack();
            
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

        } else if (keyCode == KeyEvent.VK_F) {
            yumiko.stopWalking();
            yumiko.startWalking(0);
        }  
    }
}
