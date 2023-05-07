package Everchase;

import city.cs.engine.*;

public class Coin extends DynamicBody implements SensorListener {

    private static final Shape coinShape = new CircleShape(0.35f);
    private static final BodyImage coinImage = new BodyImage("res/sprites/environment/coin.gif", 1.00f);
    private static int coinsHeld;
    private static int maxCoins;
    
    public Coin(World w) {
        super(w);

        Sensor coinSensor = new Sensor(this, coinShape);
        coinSensor.addSensorListener(this);

        addImage(coinImage);
        setGravityScale(0);
    }


    public void incrementCoinsHeld() {
        coinsHeld++;
    }

    public static int getCoinsHeld() {
        return coinsHeld;
    }

    public static void resetCoinsHeld() {
        coinsHeld = 0;
    }

    public static void setMaxCoins(int maxNum) { maxCoins = maxNum;}

    public static int getMaxCoins() { return maxCoins;}

    @Override
    public void beginContact(SensorEvent sensorEvent) {

        if (sensorEvent.getContactBody() instanceof Player) {
            incrementCoinsHeld();
            Player.incrementScore(20);
            this.destroy();
        }
    }

    @Override
    public void endContact(SensorEvent sensorEvent) {

    }
}
