package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.keyhandler;

public class Player extends Entity {

    GamePanel gp;
    keyhandler keyH;

    public final int screenx;
    public final int screeny;

    BufferedImage up, down, left, right;

    // constructor
    public Player(GamePanel gp, keyhandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        screenx = gp.screenWidth / 2 - (gp.tileSize / 2);
        screeny = gp.screenHeight / 2 - (gp.tileSize / 2);
        setDefaultValues();
        getplayerimage(); // load images
    }

    public void setDefaultValues() {
        worldx = 100;
        worldy = 100;
        speed = 4;
        direction = "down";
    }

    public void getplayerimage() {

        System.out.println(getClass().getResource("/res/back.png"));

        try {
            up = ImageIO.read(getClass().getResourceAsStream("/res/back.png"));
            down = ImageIO.read(getClass().getResourceAsStream("/res/front.png"));
            left = ImageIO.read(getClass().getResourceAsStream("/res/left.png"));
            right = ImageIO.read(getClass().getResourceAsStream("/res/right.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        if (keyH.upPressed && worldy - speed >= 0) {
            worldy -= speed;
            direction = "up";
        }

        if (keyH.downPressed && worldy + speed <= gp.worldheight - gp.tileSize) {
            worldy += speed;
            direction = "down";
        }

        if (keyH.leftPressed && worldx - speed >= 0) {
            worldx -= speed;
            direction = "left";
        }

        if (keyH.rightPressed && worldx + speed <= gp.worldwidth - gp.tileSize) {
            worldx += speed;
            direction = "right";
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        switch (direction) {

            case "up":
                image = up;
                break;

            case "down":
                image = down;
                break;

            case "left":
                image = left;
                break;

            case "right":
                image = right;
                break;
        }

        int drawX = screenx;
        int drawY = screeny;

        // RIGHT BOUNDARY OR LEFT BOUNDARY
        if (screenx > worldx) {
            drawX = worldx;
        }
        if (gp.screenWidth - screenx > gp.worldwidth - worldx) {
            drawX = gp.screenWidth - (gp.worldwidth - worldx);
        }

        // TOP BOUNDARY OR BOTTOM BOUNDARY
        if (screeny > worldy) {
            drawY = worldy;
        }
        if (gp.screenHeight - screeny > gp.worldheight - worldy) {
            drawY = gp.screenHeight - (gp.worldheight - worldy);
        }

        g2.drawImage(image, drawX, drawY, gp.tileSize, gp.tileSize, null);
    }
}