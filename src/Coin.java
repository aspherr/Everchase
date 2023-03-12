import city.cs.engine.BodyImage;
import city.cs.engine.CircleShape;
import city.cs.engine.DynamicBody;
import city.cs.engine.Shape;
import city.cs.engine.World;

public class Coin extends DynamicBody {

    private static final Shape coinShape = new CircleShape(0.35f);
    private static final BodyImage coinImage = new BodyImage("res/sprites/enviroment/coin.gif", 1.00f);
    
    public Coin(World w) {
        super(w, coinShape);
        addImage(coinImage);
        setGravityScale(0);
    }
    
}
