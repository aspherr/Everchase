package Everchase;

import city.cs.engine.UserView;
import city.cs.engine.World;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import javax.swing.ImageIcon;

public class Window extends UserView {

    private String bgPath = "res/sprites/environment/level 1/background.png";
    private int yPos;

    public Window(World w, int width, int height) {
        super(w, width, height);
    }

    // draws the background
    @Override
    protected void paintBackground(Graphics2D g) {
        Image background = new ImageIcon(bgPath).getImage();
        g.drawImage(background, 0, yPos, this);
    }

    //draws the foreground
    @Override
    protected void paintForeground(Graphics2D g) {

        GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
        
        // downloads font from resources
        try {
            ge.registerFont(Font.createFont(Font.TRUETYPE_FONT, new File("res/fonts/PixelSmall.ttf")));
        
        } catch (FontFormatException | IOException e) {
            e.printStackTrace();
        }

         if (!Manager.getGameComplete()) {

             // draws health bar status
             int healthValue = Player.getHealth();
             g.drawImage(new ImageIcon("res/sprites/icons/health-0" + healthValue + ".png").getImage(),
                     15, 20, this);

             // renders text to display number of coins held
             g.setFont(new Font("PixelSmall", Font.PLAIN, 30));
             g.setColor(new Color(255, 255, 255));
             g.drawString("SCORE: " + Player.getScore(), 18.50f, 80.00f);

             Reader scoreManager = new Reader("src/Everchase/scores.txt");
             try {
                 g.drawString("HIGH SCORE: " + scoreManager.readScores(), 18.50f, 100.00f);

             } catch (IOException e) {
                 throw new RuntimeException(e);
             }

         } else {
             g.setFont(new Font("PixelSmall", Font.PLAIN, 125));
             g.setColor(new Color(255, 255, 255));

             g.drawString("THANK YOU FOR PLAYING!", 50.00f, 200.00f);
             g.drawString("GITHUB: ASPHERR_", 150.00f, 350.00f);

         }
    }

    public void setBackgroundPath(String path) {
        bgPath = path;
    }
    public void setPosY(int pos) {
        yPos = pos;
    }

}
