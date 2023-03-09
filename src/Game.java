import org.jbox2d.common.Vec2;

import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.StaticBody;
import city.cs.engine.World;

public class Game extends World {

    private Player yumiko;

    public Game() {
        Shape groundShape = new BoxShape(50.00f, 1.5f);
        StaticBody ground = new StaticBody(this, groundShape);
        ground.setPosition(new Vec2(0f, -11.50f));
        ground.addImage(new BodyImage("res/sprites/enviroment/ground.png", 0f));

        yumiko = new Player(this);
        yumiko.setPosition(new Vec2(0f, -10.00f));
        yumiko.animationManager(yumiko.getLinearVelocity());
    }
}
