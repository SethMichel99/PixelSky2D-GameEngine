package tile;

import main.GamePanel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class TileManager {
    GamePanel gp;
    Tile[] tile;
    int mapTileNum[][];

    public TileManager(GamePanel gp) {
        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxWorldRow][gp.maxWorldCol];
        getTileImage();
        loadMap("/maps/worldmap01.txt");

    }

    public void getTileImage() {
        try {
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass01.png"));

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
            tile[1].collision = true;

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water00.png"));
            tile[2].collision = true;

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(getClass().getResourceAsStream("/tiles/earth.png"));

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(getClass().getResourceAsStream("/tiles/tree.png"));
            tile[4].collision = true;

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(getClass().getResourceAsStream("/tiles/sand.png"));
        } catch (IOException | IllegalArgumentException e) {
            e.printStackTrace();
            System.out.println("Error loading tile images. Check file paths or resource settings.");
        }
    }
    public void loadMap(String filePath) {
        try {
            InputStream is = getClass().getResourceAsStream(filePath);
            BufferedReader br = new BufferedReader(new java.io.InputStreamReader(is));

            int row = 0; // Initialize the row index

            while (row < gp.maxWorldRow) {
                String line = br.readLine();

                if (line != null) {
                    String[] numbers = line.split(" "); // Split the line into tile numbers
                    for (int col = 0; col < gp.maxWorldCol && col < numbers.length; col++) {
                        mapTileNum[row][col] = Integer.parseInt(numbers[col]);
                    }
                } else {
                    break; // Exit loop if there are no more lines in the file
                }

                row++; // Move to the next row
            }

            br.close(); // Close the file reader
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading map file: " + filePath);
        }
    }


//    public void loadMap(String filePath) {
//        try {
//            InputStream is = getClass().getResourceAsStream(filePath);
//            BufferedReader br = new BufferedReader(new java.io.InputStreamReader(is));
//
//            int col = 0;
//            int row = 0;
//
//            while (row < gp.maxWorldRow) { // Read rows
//                String line = br.readLine();
//                String[] numbers = line.split(" "); // Split each line into numbers
//
//                while (col < gp.maxWorldCol) { // Iterate over columns
//                    int num = Integer.parseInt(numbers[col]);
//                    mapTileNum[row][col] = num;
//                    col++;
//                }
//
//                col = 0; // Reset column after finishing a row
//                row++;   // Move to the next row
//            }
//
//            br.close();
//        } catch (Exception e) {
//            e.printStackTrace(); // Always print exceptions to debug issues
//        }
//    }
//


    //    public void getTileImage() {
//        try {
//
//            tile[0] = new Tile();
//            tile[0].image = ImageIO.read(getClass().getResourceAsStream("/tiles/grass.png"));
//
//            tile[1] = new Tile();
//            tile[1].image = ImageIO.read(getClass().getResourceAsStream("/tiles/wall.png"));
//
//            tile[2] = new Tile();
//            tile[2].image = ImageIO.read(getClass().getResourceAsStream("/tiles/water.png"));
//
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    public void draw(Graphics2D g2d) {
        int worldCol = 0; //set counters for traversal
        int worldRow = 0; //set counters for traversal

        while (worldRow < gp.maxWorldRow) {
            int tileNum = mapTileNum[worldRow][worldCol];

            int worldX = worldCol * gp.tileSize; // worldX = EX if worldCol[50] * gp.pilzesize(48) = 2400 X cords ((CURRENT TILE SIZE))
            int worldY = worldRow * gp.tileSize; // ex row 25 x pixelsize(48) = 1200 Y cords ((CURRENT TILE SIZE)
            int screenX = worldX - gp.player.worldX + gp.player.screenX; // screenX = tile position(2308) - player position in world(spawn 2208) = 100 + player position on screen spawn (384) = 484px, be drawn here in relevenacy to player screen.
            int screenY = worldY - gp.player.worldY + gp.player.screenY; //does same thing screenX does except for Y axis

            // if position of tile = 240 px (5,5) + 48px = 288px > player.worldX (spawn: 1104 - 384) //if right edge visible - draw it
            if (worldX + gp.tileSize > gp.player.worldX - gp.player.screenX && //furthest right of a tile -- position + 48px (tiles right edge)
                    worldX - gp.tileSize < gp.player.worldX + gp.player.screenX && //furthest left of a tile, which can be traced with - 48px to find tiles left edge.
                    worldY + gp.tileSize > gp.player.worldY - gp.player.screenY &&
                    worldY - gp.tileSize < gp.player.worldY + gp.player.screenY) {
                    //this IF checks parameters of a tile ( top/ col: left,right) and (bottom/row: left right) to render new tiles as player value increase throughtout the world
                g2d.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
            }



            worldCol++;
            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }
    }

  //  public void draw(Graphics2D g2d) {
   //     int worldCol = 0;
    //    int worldRow = 0;

//
//        while (worldCol < gp.maxWorldCol&& worldRow < gp.maxWorldRow) {
//
//            int tileNum = mapTileNum[worldCol][worldRow];
//
//            int worldX = worldCol * gp.tileSize;
//            int worldY = worldRow * gp.tileSize;
//            int screenX = worldX - gp.player.worldX + gp.player.screenX;
//            int screenY = worldY - gp.player.worldY + gp.player.screenY;
//
//            g2d.drawImage(tile[tileNum].image, screenX, screenY, gp.tileSize, gp.tileSize, null);
//            worldCol++;
//
//
//            if (worldCol == gp.maxScreenCol) {
//                worldCol = 0;
//
//                worldRow++;
//
//            }
//
//
//        }

    }



