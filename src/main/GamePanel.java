package main;

import main.entity.*;
import main.helper.Constant;
import main.helper.Image;
import main.helper.KeyHandler;
import main.tile.TileManager;
import main.ui.MainFrame;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

public class GamePanel extends JPanel implements Runnable {
    private Thread gameThread;
    private KeyHandler input;
    private Player player;
    private TileManager tm;
    private Car car;
    private Wood wood;
    private ArrayList<GameObject> objects;
    private boolean isMapLoaded = false;
    private MainFrame mainFrame;
    private int coinsCollected = 0;

    private ArrayList<Diamond> diamonds;
    private ArrayList<Coin> coins;
    private ArrayList<RedCoin> redCoins;

    public GamePanel(MainFrame mainFrame) {
        this.setPreferredSize(new Dimension(Constant.SCREEN_WIDTH, Constant.SCREEN_HEIGHT));
        this.setBackground(Color.BLUE);
        this.setDoubleBuffered(true);
        this.setFocusable(true);
        requestFocusInWindow();

        this.mainFrame = mainFrame;
        this.input = mainFrame.getKeyHandler();
        this.tm = new TileManager(this);
        this.objects = new ArrayList<>();

        tm.loadTiles();
        preload();
        startGame();
    }

    private void preload() {
        player = new Player(Constant.PLAYER_START_X, Constant.PLAYER_START_Y, 50, 50, 3, Direction.DOWN, 0, "res/char.png", "res/sprites.txt");
        car = new Car(0, 200, 100, 50, 1);
        wood = new Wood(0, 300, 100, 50, 2);
        Coin coinSpawner = new Coin(0, 0, 50, 50);
        Diamond diamondSpawner = new Diamond(0, 0, 50, 50);
        RedCoin redCoinSpawner = new RedCoin(0, 0, 50, 50);

        coins = new ArrayList<>();
        diamonds = new ArrayList<>();
        redCoins = new ArrayList<>();

        coinSpawner.spawnObjects(coins, 6);
        diamondSpawner.spawnObjects(diamonds, 3);
        redCoinSpawner.spawnObjects(redCoins, 4);

        objects.add(car);
        objects.add(wood);
    }

    private void startGame() {
        if (gameThread == null) {
            gameThread = new Thread(this);
            gameThread.start();
        }
    }

    @Override
    public void run() {
        while (gameThread != null) {
            update();
            repaint();
            try {
                double remainingTime = Constant.NEXT_DRAW_TIME - System.nanoTime();
                remainingTime = remainingTime / 1000000;
                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);
                Constant.NEXT_DRAW_TIME += Constant.DRAW_INTERVAL;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private void update() {
        if (player != null) {
            player.update(input, objects);
        }

        updateCoins();

        if (car != null) {
            car.update();
            if (car.checkCollision(player)) {
                player.offsetY = 0;
                player.setX(Constant.PLAYER_START_X);
                player.setY(Constant.PLAYER_START_Y);
                mainFrame.switchToPage("MainMenu");
            }
        }

        if (wood != null) {
            wood.update();
        }
    }

    private void updateCoins() {
        for (int i = 0; i < coins.size(); i++) {
            if (coins.get(i).update(player)) {
                coins.remove(i);
                coinsCollected++;
            }
        }

        for (int i = 0; i < diamonds.size(); i++) {
            if (diamonds.get(i).update(player)) {
                diamonds.remove(i);
                coinsCollected += 10;
            }
        }

        for (int i = 0; i < redCoins.size(); i++) {
            if (redCoins.get(i).update(player)) {
                redCoins.remove(i);
                coinsCollected += 5;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        tm.draw(g2d, player.offsetY);
        player.draw(g2d);
        car.draw(g2d, player.offsetY);
        drawCoins(g2d);
        wood.draw(g2d, player.offsetY);
    }

    private void drawCoins(Graphics2D g2d) {
        for (Coin coin : coins) {
            coin.draw(g2d, player.offsetY);
        }

        for (Diamond diamond : diamonds) {
            diamond.draw(g2d, player.offsetY);
        }

        for (RedCoin redCoin : redCoins) {
            redCoin.draw(g2d, player.offsetY);
        }

        BufferedImage coinImage = coins.isEmpty() ? Image.readSingle("res/coin.png") : (BufferedImage) coins.get(0).getImage();
        g2d.drawImage(coinImage, 10, 10, 32, 32, null);
        g2d.drawString(" x " + coinsCollected, 30, 35);
    }
}
