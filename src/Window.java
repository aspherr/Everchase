import city.cs.engine.UserView;
import city.cs.engine.World;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;

public class Window extends UserView {

    private Image background;
    private Player yumiko;

    public Window(World w, int width, int height, Player player) {
        super(w, width, height);
        this.yumiko = player;
        background = new ImageIcon("res/sprites/enviroment/background.png").getImage();
    }

    // draws the background
    @Override
    protected void paintBackground(Graphics2D g) {
        g.drawImage(background, 0, -280, this);
    }

    //draws the forground
    @Override
    protected void paintForeground(Graphics2D g) {
        // draws health bar status
        int healthValue = yumiko.getHealth();
        g.drawImage(new ImageIcon("res/sprites/icons/health-0" + healthValue + ".png").getImage(),
                    15, 20, this);

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        
        // downloads font from resources
        try {
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/PixelSmall.ttf")));
        
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

        // renders text to display number of coins held
        g.setFont(new Font("PixelSmall", 0, 30));
        g.setColor(new Color(255, 255, 255));
        g.drawString("COINS: " + Coin.getCoinsHeld(), 18.50f, 80.00f);
    }
}
