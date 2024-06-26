package main;

import main.entity.*;
import main.helper.*;
import main.helper.Image;
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
    private ArrayList<Car> cars;
    private ArrayList<Crocodile> crocodiles;
    private Wood wood;
    private ArrayList<GameObject> objects;
    private boolean isMapLoaded = false;
    private MainFrame mainFrame;
    private int coinsCollected = 0;
    private GameCamera camera;
    private boolean isGameOver = false;
    private boolean isFirstRenderDone = false;

    private ArrayList<Diamond> diamonds;
    private ArrayList<Coin> coins;
    private ArrayList<RedCoin> redCoins;

    public GamePanel(MainFrame mainFrame) {
        this.setPreferredSize(new Dimension(Constant.SCREEN_WIDTH, Constant.SCREEN_HEIGHT));
        this.setBackground(Color.BLUE);
        this.setDoubleBuffered(true);
//        this.setFocusable(true);
        requestFocusInWindow();

        this.mainFrame = mainFrame;
        this.input = mainFrame.getKeyHandler();
        this.objects = new ArrayList<>();
        this.camera = new GameCamera(this, 0, 0);
        this.tm = new TileManager(this, this.camera);

//        tm.loadTiles();
        preload();
        startGame();
    }

    private void preload() {
        player = new Player(Constant.PLAYER_START_X, Constant.PLAYER_START_Y, 50, 50, 1, Direction.DOWN, 0, "res/char.png", "res/sprites.txt", camera);
        wood = new Wood(0, 300, 100, 50, 2);
        Coin coinSpawner = new Coin(0, 0, 50, 50);
        crocodiles = new ArrayList<>();
        crocodiles.add(new Crocodile(Randomize.getRandomNumber(1, Constant.MAX_X_SPAWN), 170, 120, 50, 1));
        crocodiles.add(new Crocodile(Randomize.getRandomNumber(1, Constant.MAX_X_SPAWN), -150, 120, 50, 1));
        crocodiles.add(new Crocodile(Randomize.getRandomNumber(1, Constant.MAX_X_SPAWN), -470, 120, 50, 1));
        crocodiles.add(new Crocodile(Randomize.getRandomNumber(1, Constant.MAX_X_SPAWN), -800, 120, 50, 1));
        cars = new ArrayList<>();
        cars.add(new Car(Randomize.getRandomNumber(1, Constant.MAX_X_SPAWN), 290, 100, 50, 1));
        cars.add(new Car(Randomize.getRandomNumber(1, Constant.MAX_X_SPAWN), -20, 100, 50, 1));
        cars.add(new Car(Randomize.getRandomNumber(1, Constant.MAX_X_SPAWN), -330, 100, 50, 2));
        cars.add(new Car(Randomize.getRandomNumber(1, Constant.MAX_X_SPAWN), -730, 100, 50, 2));
        cars.add(new Car(Randomize.getRandomNumber(1, Constant.MAX_X_SPAWN), -1030, 100, 50, 3));
        Diamond diamondSpawner = new Diamond(0, 0, 50, 50);
        RedCoin redCoinSpawner = new RedCoin(0, 0, 50, 50);

        coins = new ArrayList<>();
        diamonds = new ArrayList<>();
        redCoins = new ArrayList<>();

//        spawnCollectables();

        objects.add(wood);
    }

    public void spawnCollectables() {
        Coin coinSpawner = new Coin(0, 0, 50, 50);
        Diamond diamondSpawner = new Diamond(0, 0, 50, 50);
        RedCoin redCoinSpawner = new RedCoin(0, 0, 50, 50);

        coinSpawner.spawnObjects(coins, 6);
        diamondSpawner.spawnObjects(diamonds, 3);
        redCoinSpawner.spawnObjects(redCoins, 4);

        this.coinsCollected = 0;
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
            if (!isMapLoaded) {
                tm.loadTiles();
                isMapLoaded = true;
            }
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



        if (cars != null) {
            for (Car car : cars){
                car.update();
                if (car.checkCollision(player)) {
                    player.offsetY = 0;
                    player.setX(Constant.PLAYER_START_X);
                    player.setY(Constant.PLAYER_START_Y);
                    if (SoundManager.isMainMusicPlaying) {
                        SoundManager.soundManager.stopMainMusic();
                        SoundManager.soundManager.playLoseMusic();
                    }

                    saveCoin();
                }
            }
        }

        if (checkWinCondition()){
            if (SoundManager.isMainMusicPlaying) {
                SoundManager.soundManager.stopMainMusic();
            }
            saveCoin();
            mainFrame.switchToPage("GameFinish");
        }

        if (crocodiles != null) {
            for (Crocodile crocodile : crocodiles) {
                crocodile.update();
                if (crocodile.checkCollision(player)) {
                    player.offsetY = 0;
                    player.setX(Constant.PLAYER_START_X);
                    player.setY(Constant.PLAYER_START_Y);
                    if (SoundManager.isMainMusicPlaying) {
                        SoundManager.soundManager.stopMainMusic();
                        SoundManager.soundManager.playLoseMusic();
                    }
                    saveCoin();
                }
            }
        }

        if (wood != null) {
            wood.update();
        }
        camera.centerOnEntity(player);

    }

    private boolean checkWinCondition() {
        return player.getY() < -1080;
    }

    public Player getPlayer() {
        return player;
    }

    private void saveCoin() {
        if (Session.getUser() != null && !isGameOver) {
            int previousCoin = Session.getUser().getDBCoinCount();
            System.out.println("Previous coin: " + previousCoin + " Coins collected: " + coinsCollected);
            System.out.println(Session.getUser());
            Session.getUser().updateDBCoinCount(previousCoin + coinsCollected);
            isGameOver = true;
            System.out.println("aasas");
            mainFrame.switchToPage("GameLose");
        }
        if (!isGameOver && Session.getUser() == null){
                mainFrame.switchToPage("Login");
        }
    }

    private void updateCoins() {
        for (int i = 0; i < coins.size(); i++) {
            if (coins.get(i).update(player)) {
                coins.remove(i);
                if (SoundManager.isMainMusicPlaying){
                    SoundManager.soundManager.playCoinSound(1);
                }
                coinsCollected++;
            }
        }

        for (int i = 0; i < diamonds.size(); i++) {
            if (diamonds.get(i).update(player)) {
                diamonds.remove(i);
                if (SoundManager.isMainMusicPlaying){
                    SoundManager.soundManager.playCoinSound(3);
                }
                coinsCollected += 5;
            }
        }

        for (int i = 0; i < redCoins.size(); i++) {
            if (redCoins.get(i).update(player)) {
                redCoins.remove(i);
                if (SoundManager.isMainMusicPlaying){
                    SoundManager.soundManager.playCoinSound(2);
                }
                coinsCollected += 3;
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2d = (Graphics2D) g;

        tm.draw(g2d, player.offsetY);
        for (Car cr: cars){
            cr.draw(g2d, camera);
        }
        drawCoins(g2d);
        for (Crocodile crocodile : crocodiles) {
            crocodile.draw(g2d, camera);
        }
        wood.draw(g2d, camera);
        player.draw(g2d);
//        g2d.drawImage(Image.readSingle("res/tiles/atas.png"), 0, -Constant.MAP_START_Y-camera.getyOffset(), this);
    }

    private void drawCoins(Graphics2D g2d) {
        for (Coin coin : coins) {
            coin.draw(g2d, camera);
        }

        for (Diamond diamond : diamonds) {
            diamond.draw(g2d, camera);
        }

        for (RedCoin redCoin : redCoins) {
            redCoin.draw(g2d, camera);
        }

        BufferedImage coinImage = coins.isEmpty() ? Image.readSingle("res/coin.png") : (BufferedImage) coins.get(0).getImage();
        g2d.drawImage(coinImage, 10, 10, 32, 32, null);
        g2d.drawString(" x " + coinsCollected, 30, 35);
    }

    public void setGameOver(boolean isGameOver) {
        this.isGameOver = isGameOver;
    }

    public int getCoinsCollected() {
        return coinsCollected;
    }
}
