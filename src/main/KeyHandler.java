package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, leftPressed, rightPressed;

    @Override
    public void keyTyped(KeyEvent e) {
        // Not used
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) upPressed = true;
        if (code == KeyEvent.VK_S) downPressed = true;
        if (code == KeyEvent.VK_A) leftPressed = true;
        if (code == KeyEvent.VK_D) rightPressed = true;
    }

    @Override
    public void keyReleased(KeyEvent e) {
        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) upPressed = false;
        if (code == KeyEvent.VK_S) downPressed = false;
        if (code == KeyEvent.VK_A) leftPressed = false;
        if (code == KeyEvent.VK_D) rightPressed = false;
    }
}











//package main;
//
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
//
//public class KeyHandler  implements KeyListener {
//
//    public boolean upPressed, downPressed, leftPressed, rightPressed;
//
//
//
//
//    @Override
//    public void keyTyped(KeyEvent e) {
//
//    }
//
//    @Override
//    public void keyPressed(KeyEvent e) {
//        int code = e.getKeyCode();
//        if(code == KeyEvent.VK_W) {
//            upPressed = true;
//        }
//        if(code == KeyEvent.VK_S) {
//            downPressed = true;
//        }
//        if(code == KeyEvent.VK_A) {
//            leftPressed = true;
//        }
//        if(code == KeyEvent.VK_D) {
//            rightPressed = true;
//        }
//
//    }
//
//    @Override
//    public void keyReleased(KeyEvent e) {
//        int code = e.getKeyCode();
//        if(code == KeyEvent.VK_W) {
//            upPressed = false;
//        }
//        if(code == KeyEvent.VK_S) {
//            downPressed = false;
//        }
//        if(code == KeyEvent.VK_A) {
//            leftPressed = false;
//        }
//        if (code == KeyEvent.VK_D) {
//            rightPressed = false;
//        }
//
//    }
//
//    public void update(GamePanel gamePanel) {
//        if(upPressed == true) {
//            gamePanel.playerY -= gamePanel.playerSpeed;
//        }
//        else if (downPressed == true) {
//            gamePanel.playerY += gamePanel.playerSpeed;
//        }
//        else if (leftPressed == true) {
//            gamePanel.playerX -= gamePanel.playerSpeed;
//        }
//        else if (rightPressed == true) {
//            gamePanel.playerX += gamePanel.playerSpeed;
//        }
//
//    }
//}
