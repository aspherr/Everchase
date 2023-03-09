import org.jbox2d.common.Vec2;

import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.Walker;
import city.cs.engine.World;

public class Player extends Walker {

    private static final Shape playerShape = new BoxShape(1f, 2.00f);
    private boolean inAir;
    private String currentPlayerState = "";
    private String nextPlayerState = "";

    public Player(World world) {
        super(world, playerShape);
    }

    public void animationManager(Vec2 velocity) {

        if (velocity.y == 0.00) {
            inAir = false;
        }

        if (velocity.x > 0.00 && velocity.y < 0.1 && inAir == false) {
            nextPlayerState = "run";

        } else if (velocity.y > 0.1) {
            nextPlayerState = "jump";
            inAir = true;
        
        } else if (velocity.x > -0.1 && velocity.x < 0.1 && velocity.y < 0.1 && inAir == false) {
            nextPlayerState = "idle";
        }

        if (nextPlayerState != "" && !(nextPlayerState.equals(currentPlayerState))) {
            this.removeAllImages();
            this.addImage(new BodyImage("res/sprites/player/yumiko-" + nextPlayerState + ".gif", 9.00f));
            currentPlayerState = nextPlayerState;
        }

    }
}
