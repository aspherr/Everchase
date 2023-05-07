package Everchase;

import city.cs.engine.BodyImage;
import city.cs.engine.BoxShape;
import city.cs.engine.StaticBody;
import city.cs.engine.World;
import org.jbox2d.common.Vec2;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Teleporter extends StaticBody implements ActionListener {

    private final Timer popupTimer = new Timer(880, this);

    private static boolean isCreated = false;

    public Teleporter(World world, BoxShape body) {
        super(world, body);
        setPosition(new Vec2(25.00f, -7.00f));
    }

    public void animateTeleporter() {
        popupTimer.start();

        if (!isCreated) {
            this.removeAllImages();
            this.addImage(new BodyImage("res/sprites/environment/create-teleporter.gif", 5));
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.removeAllImages();
        this.addImage(new BodyImage("res/sprites/environment/teleporter-idle.gif", 5));
        popupTimer.stop();

    }

    public static void setIsCreated(boolean state) {
        isCreated = state;
    }

}
