package entity;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import main.GamePanel;
import main.KeyHandler;

public class Player extends entity {

    GamePanel gp;
    KeyHandler keyH;


    public int screenX, screenY;


    public Player(GamePanel gp, KeyHandler keyH) {


        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - (gp.tileSize / 2); //screen x = 768px /2 - (gp.tileSize /2)
        screenY = gp.screenHeight / 2 - (gp.tileSize / 2);
        solidArea = new Rectangle(8, 16, 32, 32);
        setDefaultValues();
        getPlayerImage();
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 23; //48px x 23 = 1104px //SPAWN
        worldY = gp.tileSize * 21; //48px x 21 = 1008 //SPAWN
        speed = 4; //4px traversal movement
        direction = "up"; //set default direction
    }

    public void getPlayerImage() {
        try {
            up1 = ImageIO.read(getClass().getResourceAsStream("/player/back-run-lf.png"));
            up2 = ImageIO.read(getClass().getResourceAsStream("/player/back-run-rf.png"));
            down1 = ImageIO.read(getClass().getResourceAsStream("/player/lf-run1.png"));
            down2 = ImageIO.read(getClass().getResourceAsStream("/player/rf-run.png"));
            left1 = ImageIO.read(getClass().getResourceAsStream("/player/left-run-lf.png"));
            left2 = ImageIO.read(getClass().getResourceAsStream("/player/left-run-rf.png"));
            right1 = ImageIO.read(getClass().getResourceAsStream("/player/right-run-lf.png"));
            right2 = ImageIO.read(getClass().getResourceAsStream("/player/right-run-rf.png"));

            idleUp = ImageIO.read(getClass().getResourceAsStream("/player/back-idle.png"));
            idleDown = ImageIO.read(getClass().getResourceAsStream("/player/idlefw.png"));
            idleLeft = ImageIO.read(getClass().getResourceAsStream("/player/idle-left.png"));
            idleRight = ImageIO.read(getClass().getResourceAsStream("/player/idle-right.png"));

            spriteNum = 1;
            spriteCounter = 0;

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void update() {

        if (keyH.upPressed == true || keyH.downPressed == true ||
                keyH.leftPressed == true || keyH.rightPressed == true) {


            if (keyH.upPressed == true) {
                direction = "up";
                worldY -= speed;
            }
            if (keyH.downPressed == true) {      //UPDATE METHOD = GIANT COUNTER
                direction = "down";
                worldY += speed;
            }
            if (keyH.leftPressed == true) {
                direction = "left";
                worldX -= speed;
            }
            if (keyH.rightPressed == true) { //EX: worldX = 1104 -> if going right, increase by 4px = 1108.
                direction = "right";
                worldX += speed;
            }
            collisionOn = false;
            gp.cChecker.checkTile(this);

            spriteCounter++;
            if (spriteCounter > 12) { // SPRITE COUNTER - ONCE REACH 12 FRAMES/SWITCH ANIMATION
                if (spriteNum == 1) {
                    spriteNum = 2;
                } else if (spriteNum == 2) {
                    spriteNum = 1;
                }
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2d) {
        BufferedImage image = null;

        if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
            // Handle movement animations
            switch (direction) {
                case "up":
                    image = (spriteNum == 1) ? up1 : up2;
                    break;
                case "down":
                    image = (spriteNum == 1) ? down1 : down2;
                    break;
                case "left":
                    image = (spriteNum == 1) ? left1 : left2;
                    break;
                case "right":
                    image = (spriteNum == 1) ? right1 : right2;
                    break;
            }
        } else {
            // Handle idle animations
            switch (direction) {
                case "up":
                    image = idleUp;
                    break;
                case "down":
                    image = idleDown;
                    break;
                case "left":
                    image = idleLeft;
                    break;
                case "right":
                    image = idleRight;
                    break;
            }
        }

        g2d.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }
}


//    public void draw(Graphics2D g2d) {
////        g2d.setColor(Color.WHITE);
//
////        g2d.fillRect(x, y, gp.tileSize, gp.tileSize);
//
//        BufferedImage image = null;
//
//        switch (direction) {
//            case "up":
//                if (spriteNum == 1) {
//                    image = up1;
//                }
//                if (spriteNum == 2) {
//                    image = up2;
//                }
//                break;
//            case "down":
//                if (spriteNum == 1) {
//                    image = down1;
//                }
//                if (spriteNum == 2) {
//                    image = down2;
//                }
//                break;
//            case "left":
//                if (spriteNum == 1) {
//                    image = left1;
//                }
//                if (spriteNum == 2) {
//                    image = left2;
//                }
//                break;
//                case "right":
//                    if (spriteNum == 1) {
//                        image = right1;
//                    }
//                    if (spriteNum == 2) {
//                        image = right2;
//                    }
//                    break;
//        }
//        g2d.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
//
//    }
//}
