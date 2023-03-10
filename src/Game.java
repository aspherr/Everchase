import org.jbox2d.common.Vec2;

import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import city.cs.engine.World;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Timer;

public class Game extends World implements ActionListener{

    private Player yumiko;
    private Timer clock;

    public Game() {
        Shape groundShape = new BoxShape(50.00f, 1.5f);
        StaticBody ground = new StaticBody(this, groundShape);
        ground.setPosition(new Vec2(0f, -11.50f));
        ground.addImage(new BodyImage("res/sprites/enviroment/ground.png", 0f));

        yumiko = new Player(this);
        yumiko.setPosition(new Vec2(0f, -10.00f));

        clock = new Timer(1000, this);
        clock.start();
    }

    public Player getPlayer() {
        return yumiko;
    }

    @Override
    public void actionPerformed(ActionEvent ae) {

        if (yumiko.getCurrentPlayerState() == "light-attack-right" || 
            yumiko.getCurrentPlayerState() == "heavy-attack-right") {

            yumiko.setNextPlayerState("idle-right");
            
        } else if (yumiko.getCurrentPlayerState() == "light-attack-left" ||
                    yumiko.getCurrentPlayerState() == "heavy-attack-left") {

            yumiko.setNextPlayerState("idle-left");
        }
    }
}
