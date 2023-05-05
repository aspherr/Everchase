package Everchase.levels;

import Everchase.*;
import city.cs.engine.*;
import org.jbox2d.common.Vec2;

import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;


public class One extends Manager {

    public SoundClip bgm;

    private final Enemy critterOne = new Enemy(this);
    private final Enemy critterTwo = new Enemy(this);
    private final Enemy[] critterCollection = new Enemy[2];

    public One(Window view) {
        super();

        try {
            bgm = new SoundClip("res/sfx/bgm/level-1-bgm.wav");
            bgm.setVolume(0.15f);
            bgm.loop();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e);
        }

        // Walls for each side of the window
        Shape groundShape = new BoxShape(50.00f, 1.5f);
        StaticBody ground = new StaticBody(this, groundShape);
        ground.setPosition(new Vec2(0f, -11.50f));
        ground.addImage(new BodyImage("res/sprites/environment/ground.png", 0f));

        Shape ceilingShape = new BoxShape(50.00f, 1.5f);
        StaticBody ceiling = new StaticBody(this, ceilingShape);
        ceiling.setPosition(new Vec2(0f, 14.50f));

        Shape rightWallShape = new BoxShape(1.50f, 50.00f);
        StaticBody rightWall = new StaticBody(this, rightWallShape);
        rightWall.setPosition(new Vec2(-25.00f, -11.50f));

        Shape leftWallShape = new BoxShape(1.50f, 50.00f);
        StaticBody leftWall = new StaticBody(this, leftWallShape);
        leftWall.setPosition(new Vec2(25.00f, -11.50f));

        generatePlatforms();
        generateEnemies();
        generatePickups();

        Player player = new Player(this);
        player.setPosition(new Vec2(-20f, -10.00f));

        // detects for collisions with the player's body
        Collider collisionListener = new Collider();
        player.addCollisionListener(collisionListener);

        this.addStepListener(new Tracker(this, player, critterCollection));

        // detects inputs from keyboard
        Controller playerController = new Controller(player);
        view.addKeyListener(playerController);

    }

    private void generatePlatforms() {
        Shape platformOneShape = new BoxShape(1.00f, 0.90f);
        StaticBody platformOne = new StaticBody(this, platformOneShape);
        platformOne.setPosition(new Vec2(0.00f, -4.50f));
        platformOne.addImage(new BodyImage("res/sprites/environment/small-platform.png", 7.5f));

        Shape platformTwoShape = new BoxShape(3.00f, 1.00f);
        StaticBody platformTwo = new StaticBody(this, platformTwoShape);
        platformTwo.setPosition(new Vec2(9.00f, 0f));
        platformTwo.addImage(new BodyImage("res/sprites/environment/mid-platform.png", 7.5f));

        Shape platformThreeShape = new BoxShape(6.00f, 1.00f);
        StaticBody platformThree = new StaticBody(this, platformThreeShape);
        platformThree.setPosition(new Vec2(-10.00f, 4.00f));
        platformThree.addImage(new BodyImage("res/sprites/environment/long-platform.png", 7.5f));

        Shape platformFourShape = new BoxShape(1.00f, 0.90f);
        StaticBody platformFour = new StaticBody(this, platformFourShape);
        platformFour.setPosition(new Vec2(18.00f, 5.00f));
        platformFour.addImage(new BodyImage("res/sprites/environment/small-platform.png", 7.5f));
    }

    private void generatePickups() {

        // Three collectible coins
        Coin coinOne = new Coin(this);
        Coin coinTwo = new Coin(this);
        Coin coinThree = new Coin(this);

        coinOne.setPosition(new Vec2(-14.00f, 6.50f));
        coinTwo.setPosition(new Vec2(17.90f, 7.50f));
        coinThree.setPosition(new Vec2(-0.25f, -2.00f));

        Coin.setMaxCoins(3);
    }

    private void generateEnemies() {

        critterOne.setPosition(new Vec2(12.00f, -10.00f));
        critterOne.setDirection("left");
        critterOne.setStartingPosition(new Vec2(12.00f, -10.00f));

        critterTwo.setPosition(new Vec2(-11.00f, 5.00f));
        critterTwo.setStartingPosition(new Vec2(-11.00f, 5.00f));

        critterCollection[0] = critterOne;
        critterCollection[1] = critterTwo;
    }
}
