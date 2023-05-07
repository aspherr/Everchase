package Everchase;

import org.jbox2d.common.Vec2;

import city.cs.engine.BodyImage;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import city.cs.engine.Walker;
import city.cs.engine.World;

import java.util.Objects;


/**
 * <p>
 *  * Creates the structure for an enemy
 */
public class Enemy extends Walker {

    private static final Shape enemyShape = new PolygonShape(
            -0.62f, 0.86f, -1.43f, 0.54f,
            -1.62f, -0.58f, -0.97f, -1.18f,
            0.82f, -1.18f, 1.44f, -0.52f, 1.14f,
            0.62f, 0.52f, 0.85f);
    private int health = 4;
    private String direction;

    private boolean patrolling;

    private Vec2 startingPosition;

    /**
     *  * Constructor for enemy
     */
    public Enemy(World world) {
        super(world, enemyShape);
        addImage(new BodyImage("res/sprites/enemy/idle-right.gif", 5.00f));
    }

    /**
     *  * Enables basic patrolling behavior
     */
    public void enemyMotion() {

        // allows for patrolling behavior
        if (this.getPosition().x < (startingPosition.x - 6.00f)) {
            direction = "right";

        } else if (this.getPosition().x > startingPosition.x + 4.00f) {
            direction = "left";
        }
    }

    /**
     *  * Switches between different gifs based on the current state of the object
     */

    public void animationManager() {

        // controls animation for patrolling behaviour

        enemyMotion();

        float SPEED = 1.50f;
        if (Objects.equals(direction, "right") && patrolling) {
            this.removeAllImages();
            this.addImage(new BodyImage("res/sprites/enemy/walk-right.gif", 5.00f));
            this.startWalking(SPEED);

        } else if (Objects.equals(direction, "left") && patrolling) {
            this.removeAllImages();
            this.addImage(new BodyImage("res/sprites/enemy/walk-left.gif", 5.00f));
            this.startWalking(-SPEED);

        } else {

            if (Objects.equals(direction, "right")) {
                this.removeAllImages();
                this.addImage(new BodyImage("res/sprites/enemy/idle-right.gif", 5.00f));

            } else if (Objects.equals(direction, "left")) {
                this.removeAllImages();
                this.addImage(new BodyImage("res/sprites/enemy/idle-left.gif", 5.00f));
            }
        }

    }

    public void setStartingPosition(Vec2 position) {
        startingPosition = position;
    }

    /**
     *  * Sets which way the enemy should face
     */
    public void setDirection(String currentDirection) {
        direction = currentDirection;
    }

    /**
     *  * Decrements the health of the enemy based on the type of attack the player does
     */
    public void decrementHealth(String playerState) {

        // decrements enemy's health depending on the type of attack used

        if (Objects.equals(playerState, "light-attack-right") ||
                Objects.equals(playerState, "light-attack-left")) {
            health -= 1;

        } else if (Objects.equals(playerState, "heavy-attack-right") ||
                Objects.equals(playerState, "heavy-attack-left")) {
            health -= 2;
        }
    }

    /**
     *  @return returns the enemy's current health
     */
    public int getHealth() {
        return health;
    }

    /**
     *  * Sets whether the enemy should patrol or not
     */
    public void setPatrolling(boolean state) {patrolling = state;}
    
}
