package Everchase;

import city.cs.engine.*;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;


/**
 * <p>
 *  * Creates coin currency which awards the player +20 points
 */
public class Currency extends DynamicBody implements SensorListener {

    private static final Shape coinShape = new CircleShape(0.35f);
    private static final BodyImage coinImage = new BodyImage("res/sprites/environment/coin.gif", 1.00f);
    private static int coinsHeld;
    private static int maxCoins;


    /**
     *  * Constructor for currency pickup
     */
    public Currency(World w) {
        super(w);

        Sensor coinSensor = new Sensor(this, coinShape);
        coinSensor.addSensorListener(this);

        addImage(coinImage);
        setGravityScale(0);
    }

    /**
     *  * Increments the number of coins held
     */
    public void incrementCoinsHeld() {
        coinsHeld++;
    }

    /**
     * @return returns the current number of coins are held
     */
    public static int getCoinsHeld() {
        return coinsHeld;
    }

    /**
     *  * Resets the number of coins held when a player dies or switches levels
     */
    public static void resetCoinsHeld() {
        coinsHeld = 0;
    }

    public static void setMaxCoins(int maxNum) { maxCoins = maxNum;}

    /**
     * @return returns the maximum number of coins that can be held within that level
     */
    public static int getMaxCoins() { return maxCoins;}

    @Override
    public void beginContact(SensorEvent sensorEvent) {

        if (sensorEvent.getContactBody() instanceof Player) {

            try {
                SoundClip sfx = new SoundClip("res/sfx/coin-collect.wav");
                sfx.setVolume(0.50f);
                sfx.play();

            } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
                System.out.println(e);
            }

            incrementCoinsHeld();
            Player.incrementScore(20);
            this.destroy();
        }
    }

    @Override
    public void endContact(SensorEvent sensorEvent) {

    }
}
