package Everchase;

import city.cs.engine.*;

import java.awt.*;

/**
 * <p>
 *  * Creates a short-ranged projectile used to detect sword collision
 */
public class Projectile extends DynamicBody {

    private static final CircleShape projectileShape = new CircleShape(0.35f);

    public Projectile(World w) {
        super(w, projectileShape);
        setGravityScale(0);
        setFillColor(new Color(0, true));
        setLineColor(new Color(0, true));

        Sword swordCollision = new Sword();
        addCollisionListener(swordCollision);
    }
}
