package Everchase;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Objects;

import org.jbox2d.common.Vec2;

import city.cs.engine.BodyImage;
import city.cs.engine.Fixture;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import city.cs.engine.SolidFixture;
import city.cs.engine.Walker;
import city.cs.engine.World;

import javax.swing.*;

public class Player extends Walker implements ActionListener {

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
    private Boolean isAttacking = false;
    private String direction = "right";
    private String currentPlayerState = "";
    private String nextPlayerState = "idle-right";

    private final Timer attackTimer = new Timer(0, this);

    private int health = 3;

    public Player(World world) {
        super(world);
        idle = new SolidFixture(this, playerShape);
    }

    public void configPolygons() {
        
        // if idle and attacking, change polygon to attack right shape
        if (this.getFixtureList().contains(idle) && (Objects.equals(nextPlayerState, "light-attack-right") ||
                Objects.equals(nextPlayerState, "heavy-attack-right"))) {
            idle.destroy();
            rightAttack = new SolidFixture(this, rightAttackShape);
        
        // if idle and attacking, change polygon to attack left shape
        } else if (this.getFixtureList().contains(idle) && (Objects.equals(nextPlayerState, "light-attack-left") ||
                                                            Objects.equals(nextPlayerState, "heavy-attack-left"))) {
            idle.destroy();
            rightAttack = new SolidFixture(this, leftAttackShape);
        
        // when finished attacking right, switch to idle right 
        } else if (this.getFixtureList().contains(rightAttack) && (Objects.equals(nextPlayerState, "idle-right") ||
                                                                    Objects.equals(nextPlayerState, "run-right"))) {
            rightAttack.destroy();
            idle = new SolidFixture(this, playerShape);
            currentPlayerState = "idle-left";
        
        // when finished attacking left, switch to idle left
        } else if (this.getFixtureList().contains(leftAttack) && (Objects.equals(nextPlayerState, "idle-left") ||
                                                                    Objects.equals(nextPlayerState, "run-left"))) {
            leftAttack.destroy();
            idle = new SolidFixture(this, playerShape);
            currentPlayerState = "idle-right";

        }

    }
    
    public void playerMotion(Vec2 velocity, Vec2 position) {
         
        // if the player is moving, then use run gif 
        if (((velocity.x > 0.00 || velocity.x < -0.10) && velocity.y < 0.1) && !inAir) {

            if (Objects.equals(direction, "right")) {
                nextPlayerState = "run-right";
            
            } else if (Objects.equals(direction, "left")) {
                nextPlayerState = "run-left";
            }
        
        // if the player is moving upwards, then use jump-right gif 
        } else if (velocity.y > 1.00 || (inAir && velocity.y < 1.00)) {

            if (Objects.equals(direction, "left") || Objects.equals(currentPlayerState, "idle-left")) {
                nextPlayerState = "jump-left";
            
            } else if (direction == "right") {
                nextPlayerState = "jump-right";
            }

            inAir = true;
        
        // if player isn't moving, then use idle gif
        } else if ((velocity.x > -0.10 && velocity.x < 0.10) && velocity.y >= -0.10 && !inAir) {
  
            if (Objects.equals(currentPlayerState, "jump-left") ||
                    Objects.equals(currentPlayerState, "run-left")) {
                nextPlayerState = "idle-left";
            
            } else if (Objects.equals(currentPlayerState, "jump-right") ||
                    Objects.equals(currentPlayerState, "run-right")) {
                nextPlayerState = "idle-right";
            }
        } 
    }

    public void animationManager(Vec2 velocity, Vec2 position) {

        if (velocity.y == 0.00) {
            inAir = false;
        }

        // switches direction based on if you moving left (-velocity) or moving right (+velocity)
        if ((velocity.x <= -0.10f) || (Objects.equals(currentPlayerState, "jump-left") && velocity.x < 0.10f)) {
            direction = "left";
            
        } else {
            direction = "right";
        }

        playerMotion(velocity, position);

        if (!Objects.equals(nextPlayerState, "") && !(nextPlayerState.equals(currentPlayerState))) {

            // scaling and timings of 'attack' gifs
            if (Objects.equals(nextPlayerState, "light-attack-right") ||
                    Objects.equals(nextPlayerState, "light-attack-left")) {
                imgSize = 14.00f;

                attackTimer.setInitialDelay(500);
                attackTimer.setRepeats(false);
                attackTimer.start();

            } else if (Objects.equals(nextPlayerState, "heavy-attack-right") ||
                    Objects.equals(nextPlayerState, "heavy-attack-left")) {
                imgSize = 14.00f;

                attackTimer.setInitialDelay(700);
                attackTimer.setRepeats(false);
                attackTimer.start();

            } else {
                imgSize = 9.00f;
            }

            this.removeAllImages();
            this.addImage(new BodyImage("res/sprites/player/" + nextPlayerState + ".gif", imgSize));
            currentPlayerState = nextPlayerState;
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        this.removeAllImages();
        if (Objects.equals(nextPlayerState, "light-attack-right") ||
                Objects.equals(nextPlayerState, "heavy-attack-right")) {

            imgSize = 9.00f;
            this.addImage(new BodyImage("res/sprites/player/idle-right.gif", imgSize));

        } else if (Objects.equals(nextPlayerState, "light-attack-left") ||
                Objects.equals(nextPlayerState, "heavy-attack-left")) {

            imgSize = 9.00f;
            this.addImage(new BodyImage("res/sprites/player/idle-left.gif", imgSize));
        }

        currentPlayerState = nextPlayerState;
        attackTimer.stop();
    }

    public String getCurrentPlayerState() {
        return currentPlayerState;
    }

    public String getNextPlayerState() {
        return nextPlayerState;
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

    public void setIsHeavyAttack(boolean state) {
    }

}
