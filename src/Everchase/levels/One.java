package Everchase.levels;

import Everchase.*;
import Everchase.Window;
import city.cs.engine.*;
import city.cs.engine.Shape;
import org.jbox2d.common.Vec2;

import java.awt.*;

public class One extends Manager {

    private final Enemy critter = new Enemy(this);
    private final Enemy[] critterCollection = new Enemy[1];

    public One(Window view) {
        super();

        view.setBackgroundPath("res/sprites/environment/level 1/background.png");
        view.setPosY(-280);

        // Walls for each side of the window
        Shape groundShape = new BoxShape(50.00f, 1.5f);
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
        view.addKeyListener(playerController);

    }

    private void generatePlatforms() {
        Shape platformOneShape = new BoxShape(6.00f, 1.00f);
        StaticBody platformOne = new StaticBody(this, platformOneShape);
        platformOne.setPosition(new Vec2(-0.50f, -4.00f));
        platformOne.addImage(new BodyImage("res/sprites/environment/level 1/long-platform.png", 2.5f));

        Shape platformTwoShape = new BoxShape(1.00f, 0.90f);
        StaticBody platformTwo = new StaticBody(this, platformTwoShape);
        platformTwo.setPosition(new Vec2(0.00f, 3.00f));
        platformTwo.addImage(new BodyImage("res/sprites/environment/level 1/small-platform.png", 2.5f));
    }

    private void generatePickups() {

        // One collectible coins
        Coin coin = new Coin(this);
        coin.setPosition(new Vec2(0.00f, 5.00f));
        Coin.setMaxCoins(1);
    }

    private void generateEnemies() {

        critter.setPosition(new Vec2(0.00f, -2.00f));
        critter.setStartingPosition(new Vec2(0.00f, -2.00f));
        critter.setDirection("left");
        critter.setPatrolling(false);

        critterCollection[0] = critter;
    }
}
