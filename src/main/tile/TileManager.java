package main.tile;

import main.GamePanel;
import main.helper.Constant;
import main.helper.FileRead;
import main.helper.Image;

import java.awt.*;
import java.awt.image.BufferedImage;

public class TileManager {
    Tile[] tiles;
    GamePanel gp;
    Integer[][] tileMap;

    public TileManager(GamePanel gp){
        this.gp = gp;
        tiles = new Tile[10000000];
        this.tileMap = FileRead.getMap("res/tiles/tilemap.txt");
    }

    public void loadTiles(){
        getTileImage();
    }

    private void getTileImage() {
            Integer[] unique = FileRead.uniqueMap("res/tiles/tilemap.txt");
            for (int i = 0; i < unique.length; i++){
                System.out.println(unique[i]);
//                tiles[unique[i]] = new Tile(getTilefromId(unique[i]));
//                tiles[unique[i]].setImage(getTilefromId(unique[i]));
                System.out.println("Tile " + unique[i] + " loaded");
            }
    }

    public void draw(Graphics2D g2, int offsetY){
//        for (int y = 0; y < Constant.SCREEN_HEIGHT / 32; y++){
//            for (int x = 0; x < Constant.SCREEN_WIDTH / 32; x++){
//                if (tileMap[y][x] != -1){
//                    g2.drawImage(tiles[tileMap[y][x]].getImage(), x * 32, y * 32, gp);
//                }
//            }
//        }
        g2.drawImage(Image.readSingle("res/tiles/fullmap.png"), 0, -Constant.MAP_START_Y-offsetY, gp);
    }

    private BufferedImage getTilefromId(int id){
        int y = Math.round(id / 172) * 32;
        int x = (id % 172) * 32;
        return Image.read("res/tiles/tiles.png", x, y, 32, 32);
    }
}
