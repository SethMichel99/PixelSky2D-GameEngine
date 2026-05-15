package main;
import entity.entity;

import javax.swing.text.html.parser.Entity;

public class CollisionChecker {
    GamePanel gp;
    public CollisionChecker(GamePanel gp) {
        this.gp = gp;

    }
    public void checkTile(entity entity) {

        int entityLeftWorldX = entity.worldX + entity.solidArea.x;
    }
}
