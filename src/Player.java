import java.util.List;

import org.jbox2d.common.Vec2;

import city.cs.engine.BodyImage;
import city.cs.engine.Fixture;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;
import city.cs.engine.Walker;
import city.cs.engine.World;

public class Player extends Walker {

    @Override
    public List<Fixture> getFixtureList() {
        return super.getFixtureList();
    }

    private static final Shape playerShape = new PolygonShape(
                                                -0.13f, 1.90f, -1.12f, 
                                                1.34f, -1.26f, -0.27f, 
                                                -0.65f, -1.82f, 0.46f, 
                                                -1.83f, 0.90f, -0.27f, 
                                                0.81f, 1.44f);

    private static final Shape rightAttackShape = new PolygonShape(
                                                -0.02f, 1.84f, -1.01f, 1.47f, 
                                                -1.18f ,-0.01f, -0.28f, -1.9f, 
                                                0.34f, -1.88f, 3.28f, 0.24f, 
                                                0.86f, 1.55f
                                                );
    
    private static final Shape leftAttackShape = new PolygonShape(
                                                -0.02f, 1.84f, -1.01f, 1.47f, 
                                                -3.46f, 0.05f, -0.28f, -1.9f, 
                                                0.34f, -1.88f, 1.26f, 0.01f, 
                                                0.86f, 1.55f
                                                );
    
    SolidFixture idle;
    SolidFixture rightAttack;
    SolidFixture leftAttack;
        
    private float imgSize = 9.00f;
    private boolean inAir;
    private Boolean isAttacking;
    private String direction = "right";
    private String currentPlayerState = "";
    private String nextPlayerState = "";

    private int health = 3;

    public Player(World world) {
        super(world);
        idle = new SolidFixture(this, playerShape);
        nextPlayerState = "idle-right";
    }

    public void configPolygons() {
        
        if (this.getFixtureList().contains(idle) && (nextPlayerState == "light-attack-right" || 
                                                        nextPlayerState == "heavy-attack-right")) {
            idle.destroy();
            rightAttack = new SolidFixture(this, rightAttackShape);
        
        } else if (this.getFixtureList().contains(idle) && (nextPlayerState == "light-attack-left" || 
                                                            nextPlayerState == "heavy-attack-left")) {
            idle.destroy();
            rightAttack = new SolidFixture(this, leftAttackShape);
        }

        if (this.getFixtureList().contains(rightAttack) && (nextPlayerState == "idle-right" || nextPlayerState == "idle-left" ||
                                                            nextPlayerState == "run-right" || nextPlayerState == "run-left")) {
            rightAttack.destroy();
            idle = new SolidFixture(this, playerShape);

        } else if (this.getFixtureList().contains(leftAttack) && (nextPlayerState == "idle-right" || nextPlayerState == "idle-left" ||
                                                                    nextPlayerState == "run-right" || nextPlayerState == "run-left")) {
            leftAttack.destroy();
            idle = new SolidFixture(this, playerShape);
        }

    }
    
    public void playerMotion(Vec2 velocity, Vec2 position) {

        if (((velocity.x > 0.00 || velocity.x < -0.10) && velocity.y < 0.1) && inAir == false) {

            if (direction == "right") {
                nextPlayerState = "run-right";
            
            } else if (direction == "left") {
                nextPlayerState = "run-left";
            }
            
        } else if (velocity.y > 1.00 || (inAir == true && velocity.y < 1.00)) {

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
