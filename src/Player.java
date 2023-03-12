import org.jbox2d.common.Vec2;

import city.cs.engine.BodyImage;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import city.cs.engine.Walker;
import city.cs.engine.World;

public class Player extends Walker {

    // right attack poly: 0.05f,1.77f, -1.09f,1.25f, -1.15f,-0.42f, -0.38f,-1.74f, 0.57f,-1.78f, 2.12f,0.26f, 1.05f,1.34f
    // left attack poly: -0.1f,1.86f, 1.06f,1.34f, 1.09f,-0.24f, 0.41f,-1.79f, -0.41f,-1.78f, -2.58f,-0.01f, -1.26f,1.49f

    private static final Shape playerShape = new PolygonShape(
                                                -0.13f, 1.90f, -1.12f, 
                                                1.34f, -1.26f, -0.27f, 
                                                -0.65f, -1.82f, 0.46f, 
                                                -1.83f, 0.90f, -0.27f, 
                                                0.81f, 1.44f);
    private float imgSize = 9.00f;
    private boolean inAir;
    private Boolean isAttacking;
    private String direction = "right";
    private String currentPlayerState = "";
    private String nextPlayerState = "";

    private int health = 3;

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

    public int getHealth() {
        return health;
    }

    public void decrementHealth() {
        health -= 1;
    }

    public boolean getAttackingState() {
        return isAttacking;
    }

    public void setAttackingState(boolean state) {
        isAttacking = state;
    }

}
