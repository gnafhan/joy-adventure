package main.helper;

public class Constant {
//    Screen or Tile
    public static final int WINDOW_WIDTH = (int) (1080/1.8);
    public static final int WINDOW_HEIGHT = (int) (1920/2.5);
    public static final int TILE_SIZE = 32;
    public static final int SCALE = 2;
    public static final int TILE_SIZE_SCALED = TILE_SIZE * SCALE;
    public static final int MAX_SCREEN_COL = WINDOW_WIDTH / TILE_SIZE_SCALED;
    public static final int MAX_SCREEN_ROW = WINDOW_HEIGHT / TILE_SIZE_SCALED;
    public static final int SCREEN_WIDTH = TILE_SIZE_SCALED * MAX_SCREEN_COL + 45;
    public static final int SCREEN_HEIGHT = TILE_SIZE_SCALED * MAX_SCREEN_ROW;
    public static final int MAP_START_Y = 1990-SCREEN_HEIGHT;

//    FPS
    public static final int FPS = 120;
    public static final double DRAW_INTERVAL = 1000000000/FPS;
    public static double NEXT_DRAW_TIME = System.nanoTime() + DRAW_INTERVAL;

//    Player
    public static final int PLAYER_SPEED = 5;
    public static final int PLAYER_WIDTH = 32;
    public static final int PLAYER_HEIGHT = 32;
    public static final String PLAYER_IMAGE_PATH = "res/player/player.png";
    public static final String PLAYER_TEXT_PATH = "res/player/player.txt";
    public static final int PLAYER_START_Y = 432;
    public static final int PLAYER_START_X= 224;

//    Spawner
    public static final int MAX_COINS = 6;
    public static final int MAX_RED_COINS = 4;
    public static final int MAX_DIAMONDS = 3;
    public static final int MAX_Y_SPAWN = 400;
    public static final int MIN_Y_SPAWN = -1100;
    public static final int MAX_X_SPAWN = 515;
    public static final int MIN_X_SPAWN = 0;

}
