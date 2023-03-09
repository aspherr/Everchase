import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Controller implements KeyListener {
    private static final float SPEED = 5.00f;
    
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
        
        } else if (keyCode == KeyEvent.VK_W) {
            yumiko.jump(SPEED*2.25f);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int keyCode = e.getKeyCode();

        if (keyCode == KeyEvent.VK_D) {
            yumiko.stopWalking();  
        }
    }
    
}
