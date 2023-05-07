package Everchase.levels;

import Everchase.*;
import Everchase.Window;
import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import java.awt.*;


/**
 * <p>
 *  * Creates the layout of the third level
 */
public class Three extends Manager {

    private final Enemy critterOne = new Enemy(this);
    private final Enemy critterTwo = new Enemy(this);
    private final Enemy critterThree = new Enemy(this);

    private final Enemy[] critterCollection = new Enemy[3];

    public Three(Window view) {

        view.setBackgroundPath("res/sprites/environment/level 3/background.png");
        view.setPosY(3);

        Teleporter.setIsCreated(false);

        // Walls for each side of the window
        Shape groundShape = new BoxShape(50.00f, 0.00f);
        StaticBody ground = new StaticBody(this, groundShape);
        ground.setPosition(new Vec2(0f, -11.50f));
        ground.setFillColor(new Color(0, true));
        ground.setLineColor(new Color(0, true));

        Shape ceilingShape = new BoxShape(50.00f, 1.5f);
        StaticBody ceiling = new StaticBody(this, ceilingShape);
        ceiling.setPosition(new Vec2(0f, 16.50f));

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

        // detects inputs from keyboard
        Controller playerController = new Controller(player);
        playerController.updatePlayer(player);
        view.addKeyListener(playerController);

        this.addStepListener(new Tracker(this, player, critterCollection));
    }

    private void generatePlatforms() {
        Shape platformOneShape = new BoxShape(6.00f, 1.00f);
        StaticBody platformOne = new StaticBody(this, platformOneShape);
        platformOne.setPosition(new Vec2(15.00f, -6.00f));
        platformOne.addImage(new BodyImage("res/sprites/environment/level 3/long-platform.png", 2.5f));

        Shape platformTwoShape = new BoxShape(1.00f, 0.90f);
        StaticBody platformTwo = new StaticBody(this, platformTwoShape);
        platformTwo.setPosition(new Vec2(-2.00f, -4.50f));
        platformTwo.addImage(new BodyImage("res/sprites/environment/level 3/small-platform.png", 2.5f));

        Shape platformThreeShape = new BoxShape(1.00f, 0.90f);
        StaticBody platformThree = new StaticBody(this, platformThreeShape);
        platformThree.setPosition(new Vec2(-7.00f, -4.50f));
        platformThree.addImage(new BodyImage("res/sprites/environment/level 3/small-platform.png", 2.5f));

        Shape platformFourShape = new BoxShape(1.00f, 0.90f);
        StaticBody platformFour = new StaticBody(this, platformFourShape);
        platformFour.setPosition(new Vec2(-12.00f, -4.50f));
        platformFour.addImage(new BodyImage("res/sprites/environment/level 3/small-platform.png", 2.5f));

        Shape platformFiveShape = new BoxShape(6.00f, 1.00f);
        StaticBody platformFive = new StaticBody(this, platformFiveShape);
        platformFive.setPosition(new Vec2(-6.00f, 7.00f));
        platformFive.addImage(new BodyImage("res/sprites/environment/level 3/long-platform.png", 2.5f));

        Shape platformSixShape = new BoxShape(1.00f, 0.90f);
        StaticBody platformSix = new StaticBody(this, platformSixShape);
        platformSix.setPosition(new Vec2(-21.50f, 2.00f));
        platformSix.addImage(new BodyImage("res/sprites/environment/level 3/small-platform.png", 2.5f));

        Shape platformSevenShape = new BoxShape(3.00f, 1.00f);
        StaticBody platformSeven = new StaticBody(this, platformSevenShape);
        platformSeven.setPosition(new Vec2(19.50f, 6.00f));
        platformSeven.addImage(new BodyImage("res/sprites/environment/level 3/mid-platform.png", 2.5f));

    }

    private void generatePickups() {
        Currency.resetCoinsHeld();

        // Three collectible coins
        Currency currencyOne = new Currency(this);
        Currency currencyTwo = new Currency(this);
        Currency currencyThree = new Currency(this);
        Currency currencyFour = new Currency(this);

        currencyOne.setPosition(new Vec2(17.00f, -9.50f));
        currencyTwo.setPosition(new Vec2(-12.00f, -2.00f));
        currencyThree.setPosition(new Vec2(-11.00f, 10.00f));
        currencyFour.setPosition(new Vec2(17.00f, 9.00f));

        Currency.setMaxCoins(4);

    }

    private void generateEnemies() {
        critterOne.setPosition(new Vec2(5.00f, -10.00f));
        critterOne.setStartingPosition(new Vec2(5.00f, -10.00f));
        critterOne.setDirection("left");
        critterOne.setPatrolling(true);

        critterTwo.setPosition(new Vec2(-6.75f, -2.50f));
        critterTwo.setStartingPosition(new Vec2(-6.75f, -2.50f));
        critterTwo.setDirection("right");
        critterTwo.setPatrolling(false);

        critterThree.setPosition(new Vec2(-6.75f, 8.50f));
        critterThree.setStartingPosition(new Vec2(-6.75f, 8.50f));
        critterThree.setDirection("left");
        critterThree.setPatrolling(false);

        critterCollection[0] = critterOne;
        critterCollection[1] = critterTwo;
        critterCollection[2] = critterThree;

    }
}
