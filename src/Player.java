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
    private String attackType;
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
            imgSize = 9.00f;

            if (direction == "right") {
                nextPlayerState = "run-right";
            
            } else if (direction == "left") {
                nextPlayerState = "run-left";
            }
            
        } else if (velocity.y > 0.10 || (inAir == true && velocity.y < 0.10)) {
            imgSize = 9.00f;

            if (direction == "right") {
                nextPlayerState = "jump-right";
            
            } else if (direction == "left") {
                nextPlayerState = "jump-left";
            }

            inAir = true;
        
        } else if ((velocity.x > -0.10 && velocity.x < 0.10) && velocity.y >= -0.10 && inAir == false) {
            imgSize = 9.00f;

            if (currentPlayerState == "jump-left" || currentPlayerState == "run-left") {
                nextPlayerState = "idle-left";
            
            } else if (currentPlayerState == "jump-right" || currentPlayerState == "run-right") {
                nextPlayerState = "idle-right";
            }

            System.out.println(imgSize);

        } 
        
        if (attackType == "light" && inAir == false) {
            // imgSize = 14.00f;

        } else if (attackType == "heavy" && inAir == false) {
            // imgSize = 14.00f;
        
        } else if (attackType == "combo" && inAir == false) {
            // imgSize = 14.00f;
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
            this.addImage(new BodyImage("res/sprites/player/yumiko-" + nextPlayerState + ".gif", imgSize));
            currentPlayerState = nextPlayerState;
        }
    }

    public String getAttackType() {
        return attackType;
    }

    public void setAttackType(String type) {
        attackType = type;
    }

    public String getPlayerState() {
        return currentPlayerState;
    }

    public void setPlayerState(String state) {
        nextPlayerState = state;
    }
}
