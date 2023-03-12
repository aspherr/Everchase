import org.jbox2d.common.Vec2;

import city.cs.engine.BodyImage;
import city.cs.engine.PolygonShape;
import city.cs.engine.Shape;
import city.cs.engine.Walker;
import city.cs.engine.World;

public class Enemy extends Walker {

    private static final Shape enemyShape = new PolygonShape(
                                            -0.62f, 0.86f, -1.43f, 0.54f, 
                                            -1.62f, -0.58f, -0.97f, -1.18f, 
                                            0.82f, -1.18f, 1.44f, -0.52f, 1.14f,
                                            0.62f, 0.52f, 0.85f);
    private int health = 8;
    private float SPEED = 1.50f;
    private String direction;
    private Vec2 startingPosition;

    public Enemy(World world) {
        super(world, enemyShape);
        addImage(new BodyImage("res/sprites/enemy/critter-idle-right.gif", 5.00f));
    }

    public void enemyMotion() {
        
        if (this.getPosition().x < (startingPosition.x - 6.00f)) {
            direction = "right";
        
        } else if (this.getPosition().x > startingPosition.x + 4.00f) {
            direction = "left";
        }
    }

    public void animationManager() {

        enemyMotion();

        if (direction == "right") {
            this.removeAllImages();
            this.addImage(new BodyImage("res/sprites/enemy/critter-walk-right.gif", 5.00f));
            this.startWalking(SPEED);

        } else if (direction == "left")  {
            this.removeAllImages();
            this.addImage(new BodyImage("res/sprites/enemy/critter-walk-left.gif", 5.00f));
            this.startWalking(-SPEED); 
        }

    }

    public int getHealth() {
        return health;
    }

    public void decrementHealth(Player yumiko) {

        if (yumiko.getCurrentPlayerState() == "light-attack-right" || 
            yumiko.getCurrentPlayerState() == "light-attack-left") {
            health -= 1;

        } else if (yumiko.getCurrentPlayerState() == "heavy-attack-right" ||
                    yumiko.getCurrentPlayerState() == "heavy-attack-left") {
            health -= 2;
        }
    }

    public void setStartingPosition(Vec2 position) {
        startingPosition = position;
    } 

    public void setDirection(String currentDirection) {
        direction = currentDirection;
    }
    
}
