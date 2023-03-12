import city.cs.engine.CollisionEvent;
import city.cs.engine.CollisionListener;

public class Collider implements CollisionListener {

    @Override
    public void collide(CollisionEvent c) {

        if (c.getOtherBody() instanceof Coin) {
            ((Coin) c.getOtherBody()).incrementCoinsHeld();
            c.getOtherBody().destroy();
        }
    }
}
