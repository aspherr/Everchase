import city.cs.engine.BodyImage;
import city.cs.engine.CircleShape;
import city.cs.engine.DynamicBody;
// import city.cs.engine.GhostlyFixture;
import city.cs.engine.Shape;
import city.cs.engine.World;

public class Coin extends DynamicBody {

    private static final Shape coinShape = new CircleShape(0.35f);
    private static final BodyImage coinImage = new BodyImage("res/sprites/enviroment/coin.gif", 1.00f);
    private static int coinsHeld;
    
    public Coin(World w) {
        super(w, coinShape);
        // sensor listener bugged
        // GhostlyFixture coinGhostFixture = new GhostlyFixture(this, coinShape); 
        addImage(coinImage);
        setGravityScale(0);
    }

    public void incrementCoinsHeld() {
        coinsHeld++;
    }

    public static int getCoinsHeld() {
        return coinsHeld;
    }
    
}
