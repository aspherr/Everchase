package Everchase;

import org.jbox2d.common.Vec2;

import city.cs.engine.BodyImage;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import city.cs.engine.Walker;
import city.cs.engine.World;

import java.util.Objects;

public class Enemy extends Walker {

    private static final Shape enemyShape = new PolygonShape(
                                            -0.62f, 0.86f, -1.43f, 0.54f, 
                                            -1.62f, -0.58f, -0.97f, -1.18f, 
                                            0.82f, -1.18f, 1.44f, -0.52f, 1.14f,
                                            0.62f, 0.52f, 0.85f);
    private int health = 5;
    private float SPEED = 1.50f;
    private String direction;
    private Vec2 startingPosition;

    public Enemy(World world) {
        super(world, enemyShape);
        addImage(new BodyImage("res/sprites/enemy/critter-idle-right.gif", 5.00f));
    }

    public void enemyMotion() {

        // allows for patrolling behavior
        
        if (this.getPosition().x < (startingPosition.x - 6.00f)) {
            direction = "right";
        
        } else if (this.getPosition().x > startingPosition.x + 4.00f) {
            direction = "left";
        }
    }

    public void animationManager() {

        // controls animation for patrolling behaviour

        enemyMotion();

        if (Objects.equals(direction, "right")) {
            this.removeAllImages();
            this.addImage(new BodyImage("res/sprites/enemy/critter-walk-right.gif", 5.00f));
            this.startWalking(SPEED);

        } else if (Objects.equals(direction, "left"))  {
            this.removeAllImages();
            this.addImage(new BodyImage("res/sprites/enemy/critter-walk-left.gif", 5.00f));
            this.startWalking(-SPEED); 
        }

    }

    public void setStartingPosition(Vec2 position) {
        startingPosition = position;
    } 

    public void setDirection(String currentDirection) {
        direction = currentDirection;
    }

    public void decrementHealth(Player player) {

        // decrements enemy's health depending on the type of attack used

        if (Objects.equals(player.getCurrentPlayerState(), "light-attack-right") ||
                Objects.equals(player.getCurrentPlayerState(), "light-attack-left")) {
            health -= 1;

        } else if (Objects.equals(player.getCurrentPlayerState(), "heavy-attack-right") ||
                Objects.equals(player.getCurrentPlayerState(), "heavy-attack-left")) {
            health -= 2;
        }
    }

    public int getHealth() {
        return health;
    }
    
}
