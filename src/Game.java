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
    private Enemy critterOne;
    private Enemy critterTwo;
    private Coin coinOne;
    private Coin coinTwo;
    private Coin coinThree;
    private Timer clock;

    public Game() {
        Shape groundShape = new BoxShape(50.00f, 1.5f);
        StaticBody ground = new StaticBody(this, groundShape);
        ground.setPosition(new Vec2(0f, -11.50f));
        ground.addImage(new BodyImage("res/sprites/enviroment/ground.png", 0f));

        Shape platformOneShape = new BoxShape(1.00f, 0.90f);
        StaticBody platformOne = new StaticBody(this, platformOneShape);
        platformOne.setPosition(new Vec2(0.00f, -4.50f));
        platformOne.addImage(new BodyImage("res/sprites/enviroment/small-platform.png", 7.5f));

        Shape platformTwoShape = new BoxShape(3.00f, 1.00f);
        StaticBody platformTwo = new StaticBody(this, platformTwoShape);
        platformTwo.setPosition(new Vec2(9.00f, 0f));
        platformTwo.addImage(new BodyImage("res/sprites/enviroment/mid-platform.png", 7.5f));

        Shape platformThreeShape = new BoxShape(6.00f, 1.00f);
        StaticBody platformThree = new StaticBody(this, platformThreeShape);
        platformThree.setPosition(new Vec2(-10.00f, 4f));
        platformThree.addImage(new BodyImage("res/sprites/enviroment/long-platform.png", 7.5f));

        Shape platformFourShape = new BoxShape(1.00f, 0.90f);
        StaticBody platformFour = new StaticBody(this, platformFourShape);
        platformFour.setPosition(new Vec2(18.00f, 5f));
        platformFour.addImage(new BodyImage("res/sprites/enviroment/small-platform.png", 7.5f));

        yumiko = new Player(this);
        yumiko.setPosition(new Vec2(-20f, -10.00f));

        critterOne = new Enemy(this);
        critterOne.setPosition(new Vec2(12.00f, -10.00f));
        critterOne.setDirection("left");
        critterOne.setStartingPosition(new Vec2(12.00f, -10.00f));

        critterTwo = new Enemy(this);
        critterTwo.setPosition(new Vec2(-11.00f, 5.00f));

        coinOne = new Coin(this);
        coinOne.setPosition(new Vec2(-14.00f, 6.50f));

        coinTwo = new Coin(this);
        coinTwo.setPosition(new Vec2(17.90f, 7.50f));

        coinThree = new Coin(this);
        coinThree.setPosition(new Vec2(-0.25f, -2.00f));

        Collider collisionListener = new Collider();
        yumiko.addCollisionListener(collisionListener);

        clock = new Timer(1000, this);
        clock.start();
    }

    public Player getPlayer() {
        return yumiko;
    }

    public Enemy getEnemy() {
        return critterOne;
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
