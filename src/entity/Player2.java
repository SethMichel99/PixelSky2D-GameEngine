package entity;

import main.GamePanel;
import main.KeyHandler;

public class Player2 extends entity {

    GamePanel gp;
    KeyHandler keyh;
    public int ScreenX, ScreenY;

    public Player2(GamePanel gp, KeyHandler keyh) {
        this.gp = gp;
        this.keyh = keyh;

        ScreenX = gp.getWidth() / 2 + gp.tileSize /2;
        ScreenY = gp.getHeight() / 2 + gp.tileSize /2;


    }



}
