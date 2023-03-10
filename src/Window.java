import city.cs.engine.UserView;
import city.cs.engine.World;

import java.awt.*;

import javax.swing.ImageIcon;

public class Window extends UserView {

    private Image background;
    private Player yumiko;

    public Window(World w, int width, int height, Player player) {
        super(w, width, height);
        this.yumiko = player;
        background = new ImageIcon("res/sprites/enviroment/background.png").getImage();
    }

    @Override
    protected void paintBackground(Graphics2D g) {
        g.drawImage(background, 0, -280, this);
    }

    @Override
    protected void paintForeground(Graphics2D g) {
        int healthValue = yumiko.getHealth();
        System.out.println(healthValue);
        g.drawImage(new ImageIcon("res/sprites/icons/health-0" + healthValue + ".png").getImage(),
                    15, 20, this);
    }
}
