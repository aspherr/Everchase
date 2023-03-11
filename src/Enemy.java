import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.Shape;
import city.cs.engine.Walker;
import city.cs.engine.World;

public class Enemy extends Walker {

    private static final Shape enemyShape = new BoxShape(1.90f, 0.90f);
    private int health;

    public Enemy(World world) {
        super(world, enemyShape);
        addImage(new BodyImage("res/sprites/enemy/critter-idle-left.gif", 5.00f));
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int healthValue) {
        health -= healthValue;
    }
    
}
