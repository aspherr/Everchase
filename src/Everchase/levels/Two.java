package Everchase.levels;

import Everchase.*;
import Everchase.Window;
import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import java.awt.*;

public class Two extends Manager {

    private final Enemy critterOne = new Enemy(this);
    private final Enemy critterTwo = new Enemy(this);
    private final Enemy[] critterCollection = new Enemy[2];

    public Two(Window view) {

        view.setBackgroundPath("res/sprites/environment/level 2/background.png");
        view.setPosY(0);

        Teleporter.setIsCreated(false);

        // Walls for each side of the window
        Shape groundShape = new BoxShape(50.00f, 0.00f);
        StaticBody ground = new StaticBody(this, groundShape);
        ground.setPosition(new Vec2(0f, -11.50f));
        ground.setFillColor(new Color(0, true));
        ground.setLineColor(new Color(0, true));

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
        playerController.updatePlayer(player);
        view.addKeyListener(playerController);
    }

    private void generatePlatforms() {

        Shape platformOneShape = new BoxShape(1.00f, 0.90f);
        StaticBody platformOne = new StaticBody(this, platformOneShape);
        platformOne.setPosition(new Vec2(0.00f, -5.50f));
        platformOne.addImage(new BodyImage("res/sprites/environment/level 2/small-platform.png", 2.5f));

        Shape platformTwoShape = new BoxShape(3.00f, 1.00f);
        StaticBody platformTwo = new StaticBody(this, platformTwoShape);
        platformTwo.setPosition(new Vec2(9.00f, 0f));
        platformTwo.addImage(new BodyImage("res/sprites/environment/level 2/mid-platform.png", 2.5f));

        Shape platformThreeShape = new BoxShape(6.00f, 1.00f);
        StaticBody platformThree = new StaticBody(this, platformThreeShape);
        platformThree.setPosition(new Vec2(-10.00f, 4.00f));
        platformThree.addImage(new BodyImage("res/sprites/environment/level 2/long-platform.png", 2.5f));

        Shape platformFourShape = new BoxShape(1.00f, 0.90f);
        StaticBody platformFour = new StaticBody(this, platformFourShape);
        platformFour.setPosition(new Vec2(18.00f, 5.00f));
        platformFour.addImage(new BodyImage("res/sprites/environment/level 2/small-platform.png", 2.5f));
    }

    private void generatePickups() {

        Coin.resetCoinsHeld();

        // Three collectible coins
        Coin coinOne = new Coin(this);
        Coin coinTwo = new Coin(this);
        Coin coinThree = new Coin(this);

        coinOne.setPosition(new Vec2(-15.00f, 6.50f));
        coinTwo.setPosition(new Vec2(18.00f, 7.25f));
        coinThree.setPosition(new Vec2(0.00f, -3.50f));

        Coin.setMaxCoins(3);
    }

    private void generateEnemies() {
        critterOne.setPosition(new Vec2(12.00f, -10.00f));
        critterOne.setStartingPosition(new Vec2(12.00f, -10.00f));
        critterOne.setDirection("left");
        critterOne.setPatrolling(true);

        critterTwo.setPosition(new Vec2(-11.00f, 5.00f));
        critterTwo.setStartingPosition(new Vec2(-11.00f, 5.00f));
        critterOne.setDirection("right");

        critterCollection[0] = critterOne;
        critterCollection[1] = critterTwo;
    }

}
