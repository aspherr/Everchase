import city.cs.engine.UserView;
import city.cs.engine.World;

import java.awt.*;

import javax.swing.ImageIcon;

public class Window extends UserView {

    private Image background;

    public Window(World w, int width, int height) {
        super(w, width, height);
        background = new ImageIcon("res/sprites/.png").getImage();
    }

    @Override
    protected void paintBackground(Graphics2D g) {
        g.drawImage(background, 0, 0 , this);
    }
}
