import org.jbox2d.common.Vec2;

import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.Walker;
import city.cs.engine.World;

public class Player extends Walker {

    private static final Shape playerShape = new BoxShape(1.00f, 2.00f);
    private float imgSize = 9.00f;
    private boolean inAir;
    private String direction = "right";
    private String currentPlayerState = "";
    private String nextPlayerState = "";

    public Player(World world) {
        super(world, playerShape);
        nextPlayerState = "idle-right";
    }

    public void attack() {

    }
    
    public void playerMotion(Vec2 velocity, Vec2 position) {

        if (((velocity.x > 0.00 || velocity.x < -0.10) && velocity.y < 0.1) && inAir == false) {

            if (direction == "right") {
                nextPlayerState = "run-right";
            
            } else if (direction == "left") {
                nextPlayerState = "run-left";
            }
            
        } else if (velocity.y > 0.10 || (inAir == true && velocity.y < 0.10)) {

            if (direction == "left" || currentPlayerState == "idle-left") {
                nextPlayerState = "jump-left";
            
            } else if (direction == "right") {
                nextPlayerState = "jump-right";

            }

            inAir = true;
        
        } else if ((velocity.x > -0.10 && velocity.x < 0.10) && velocity.y >= -0.10 && inAir == false) {
  
            if (currentPlayerState == "jump-left" || currentPlayerState == "run-left") {
                nextPlayerState = "idle-left";
            
            } else if (currentPlayerState == "jump-right" || currentPlayerState == "run-right") {
                nextPlayerState = "idle-right";
            }

        } 
    }

    public void animationManager(Vec2 velocity, Vec2 position) {

        if (velocity.y == 0.00) {
            inAir = false;
        }

        if ((velocity.x <= -0.10f) || (currentPlayerState == "jump-left" && velocity.x < 0.10f)) {
            direction = "left";
            
        } else {
            direction = "right";
        }

        playerMotion(velocity, position);

        if (nextPlayerState != "" && !(nextPlayerState.equals(currentPlayerState))) {
            this.removeAllImages();

            if (nextPlayerState == "light-attack-right" || nextPlayerState == "light-attack-left" ||
                nextPlayerState == "heavy-attack-right" || nextPlayerState == "heavy-attack-left") {
                imgSize = 14.00f;

            } else {
                imgSize = 9.00f;
            }

            this.addImage(new BodyImage("res/sprites/player/yumiko-" + nextPlayerState + ".gif", imgSize));
            currentPlayerState = nextPlayerState;
        }
    }

    public String getCurrentPlayerState() {
        return currentPlayerState;
    }

    public void setNextPlayerState(String state) {
        nextPlayerState = state;
    }
}
