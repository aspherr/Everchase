package Everchase;

import city.cs.engine.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;

/**
 * <p>
 *  * Heart pickup which awards the player +1 health or +10 points if their health is full
 */
public class Heart extends DynamicBody implements SensorListener {

    private static final Shape heartShape = new CircleShape(0.35f);
    private static final BodyImage heartImage = new BodyImage("res/sprites/environment/heart.gif", 1.50f);

    /**
     *  * Constructor for the heart pickup
     */
    public Heart(World w) {
        super(w);

        Sensor heartSensor = new Sensor(this, heartShape);
        heartSensor.addSensorListener(this);

        setGravityScale(0);
    }

    /**
     *  * Adds gif to animate the pickup
     */
    public void animateHeart() {
        this.addImage(heartImage);
    }

    @Override
    public void beginContact(SensorEvent sensorEvent) {

        if (sensorEvent.getContactBody() instanceof Player) {

            try {
                SoundClip sfx = new SoundClip("res/sfx/heart-collect.wav");
                sfx.setVolume(1.00f);
                sfx.play();

            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                System.out.println(e);
            }

            if (Player.getHealth() == 3) {
                Player.incrementScore(10);

            } else {
                Player.incrementHealth();
            }

            this.destroy();
        }

    }

    @Override
    public void endContact(SensorEvent sensorEvent) {

    }
}
