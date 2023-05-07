package Everchase;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects;

import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import javax.swing.*;

public class Player extends Walker implements ActionListener {

    private static final Shape playerShape = new PolygonShape(
                                                -0.13f, 1.90f, -1.12f, 
                                                1.34f, -1.26f, -0.27f, 
                                                -0.65f, -1.82f, 0.46f, 
                                                -1.83f, 0.90f, -0.27f, 
                                                0.81f, 1.44f);
    SolidFixture player;

    private Projectile projectile;

    private float imgSize = 9.00f;
    private static boolean inAir;
    private static Boolean isAttacking = false;
    private String direction = "right";
    private static String currentPlayerState;
    private String nextPlayerState;

    private final Timer attackTimer = new Timer(0, this);

    private static int health = 3;

    private static int score = 0;

    private boolean levelComplete;

    public Player(World world) {
        super(world);
        player = new SolidFixture(this, playerShape);
        currentPlayerState = "";
        nextPlayerState = "idle-right";
    }

    public void swordCollision(World world) {

        if (isAttacking && !inAir) {
            projectile = new Projectile(world);
            projectile.setPosition(this.getPosition());

            if (Objects.equals(direction, "right")) {
                projectile.setLinearVelocity(new Vec2(30.00f, 0.00f));

            } else if (Objects.equals(direction, "left")) {
                projectile.setPosition(new Vec2(this.getPosition().x - 0.50f, this.getPosition().y));
                projectile.setLinearVelocity(new Vec2(-30.00f, 0.00f));
            }
        }
    }

    public void playerMotion(Vec2 velocity) {
         
        // if the player is moving, then use run gif
        if (((velocity.x > 0.00 || velocity.x < -0.10) && velocity.y < 0.1) && !inAir) {

            if (Objects.equals(direction, "right")) {
                if (velocity.y < -1.00) {
                    nextPlayerState = "jump-right";

                } else {
                    nextPlayerState = "run-right";
                }
            
            } else if (Objects.equals(direction, "left")) {
                if (velocity.y < -1.00) {
                    nextPlayerState = "jump-left";

                } else {
                    nextPlayerState = "run-left";
                }
            }


        // if the player is moving upwards, then use jump-right gif
        } else if (velocity.y > 1.00 || (inAir && velocity.y < 1.00)) {

            if (Objects.equals(direction, "left") ||
                    Objects.equals(currentPlayerState, "idle-left")) {
                nextPlayerState = "jump-left";
            
            } else if (Objects.equals(direction, "right")) {
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

    public void animationManager(Vec2 velocity) {

        if (velocity.y == 0.00) {
            inAir = false;
        }

        // switches direction based on if you moving left (-velocity) or moving right (+velocity)
        if ((velocity.x <= -0.10f) || (Objects.equals(currentPlayerState, "jump-left") && velocity.x < 0.10f)) {
            direction = "left";
            
        } else {
            direction = "right";
        }

        if (Objects.equals(nextPlayerState, "light-attack-right") ||
                Objects.equals(nextPlayerState, "heavy-attack-right")) {

            direction = "right";

        } else if (Objects.equals(nextPlayerState, "light-attack-left") ||
                Objects.equals(nextPlayerState, "heavy-attack-left")) {

            direction = "left";
        }

        playerMotion(velocity);

        if (!Objects.equals(nextPlayerState, "") && !(nextPlayerState.equals(currentPlayerState))) {

            // scaling and timings of 'attack' gifs
            if (Objects.equals(nextPlayerState, "light-attack-right") ||
                    Objects.equals(nextPlayerState, "light-attack-left")) {
                imgSize = 14.00f;

                setAttackingState(true);
                swordCollision(Manager.getWorld());

                attackTimer.setInitialDelay(500);
                attackTimer.setRepeats(false);
                attackTimer.start();

            } else if (Objects.equals(nextPlayerState, "heavy-attack-right") ||
                    Objects.equals(nextPlayerState, "heavy-attack-left")) {
                imgSize = 14.00f;

                setAttackingState(true);
                swordCollision(Manager.getWorld());

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
    public void actionPerformed(ActionEvent ae) {

        if (Objects.equals(nextPlayerState, "light-attack-right") ||
                Objects.equals(nextPlayerState, "heavy-attack-right")) {

            this.removeAllImages();
            imgSize = 9.00f;
            this.addImage(new BodyImage("res/sprites/player/idle-right.gif", imgSize));

        } else if (Objects.equals(nextPlayerState, "light-attack-left") ||
                Objects.equals(nextPlayerState, "heavy-attack-left")) {

            this.removeAllImages();
            imgSize = 9.00f;
            this.addImage(new BodyImage("res/sprites/player/idle-left.gif", imgSize));
        }

        currentPlayerState = nextPlayerState;
        attackTimer.stop();
    }

    public static String getCurrentPlayerState() {
        return currentPlayerState;
    }

    public void setNextPlayerState(String state) {
        nextPlayerState = state;
    }

    public static int getHealth() {
        return health;
    }

    public static void incrementHealth() {
        health += 1;
    }

    public void decrementHealth() {
        health -= 1;
    }

    public static boolean getAttackingState() {
        return isAttacking;
    }

    public void setAttackingState(boolean state) {
        isAttacking = state;
    }

    public static void incrementScore(int points) {
        score += points;
    }

    public static int getScore() {
        return score;
    }

    public Projectile getProjectile() {
        return projectile;
    }

    public static boolean isInAir() {
        return inAir;
    }

    public String getDirection() {
        return direction;
    }

    public void setLevelComplete(boolean state) {levelComplete = state;}

    public boolean getLevelComplete() {return levelComplete;};
}
