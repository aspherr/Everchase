import city.cs.engine.UserView;
import city.cs.engine.World;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;

public class Window extends UserView {

    private Image background;
    private Player yumiko;
    private int coins;

    public Window(World w, int width, int height, Player player, int pickups) {
        super(w, width, height);
        this.yumiko = player;
        this.coins = pickups;
        background = new ImageIcon("res/sprites/enviroment/background.png").getImage();
    }

    @Override
    protected void paintBackground(Graphics2D g) {
        g.drawImage(background, 0, -280, this);
    }

    @Override
    protected void paintForeground(Graphics2D g) {
        int healthValue = yumiko.getHealth();
        g.drawImage(new ImageIcon("res/sprites/icons/health-0" + healthValue + ".png").getImage(),
                    15, 20, this);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        
        try {
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/PixelSmall.ttf")));
        
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        g.setFont(new Font("PixelSmall", 0, 30));
        g.setColor(new Color(255, 255, 255));
        g.drawString("COINS: " + coins, 18.50f, 80.00f);
    }
}
