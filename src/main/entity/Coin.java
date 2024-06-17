package main.entity;

import main.entity.interfaces.Drawable;
import main.entity.interfaces.Spawner;
import main.helper.Constant;

import java.awt.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;
import javax.imageio.ImageIO;

public class Coin extends GameObject implements Spawner<Coin>, Drawable {
    private Image coinImage;
    private int screenY;

    public Coin(int x, int y, int width, int height) {
        super(x, y, width, height);
        loadImage();
    }

    private void loadImage() {
        coinImage = main.helper.Image.readSingle("res/coin.png");
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(getX(), screenY, getWidth(), getHeight());
    }

    public boolean update(Player player) {
        if (player.getBounds().intersects(this.getBounds())) {
            return true;
        }
        return false;
    }

    public void draw(Graphics2D g2, int offsetY) {
        if (coinImage != null) {
            setScreenY(getY() - offsetY);
            g2.drawImage(coinImage, getX(), getY()-offsetY, getWidth(), getHeight(), null);
        }
    }

    private void setScreenY(int i) {
        this.screenY = i;
    }

    public Image getImage() {
        return coinImage;
    }

    @Override
    public void spawnObjects(ArrayList<Coin> objects, int maxObjects) {
        Random random = new Random();
        while (objects.size() < maxObjects) {
            int coinX = random.nextInt(Constant.MAX_X_SPAWN - Constant.MIN_X_SPAWN + 1) + Constant.MIN_X_SPAWN;
            int coinY = random.nextInt(Constant.MAX_Y_SPAWN - Constant.MIN_Y_SPAWN + 1) + Constant.MIN_Y_SPAWN;
            objects.add(new Coin(coinX, coinY, 50, 50));
        }
    }
}
