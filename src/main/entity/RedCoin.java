package main.entity;

import main.entity.interfaces.Drawable;
import main.entity.interfaces.Spawner;
import main.helper.Constant;
import main.helper.GameCamera;

import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.Random;

public class RedCoin extends GameObject implements Spawner<RedCoin>, Drawable {
    private Image redCoinImage;
    private int screenY;

    public RedCoin(int x, int y, int width, int height) {
        super(x, y, width, height);
        loadImage();
    }

    private void loadImage() {
        redCoinImage = main.helper.Image.readSingle("res/redcoin.png");
    }

    public boolean update(Player player) {
        if (player.getBounds().intersects(this.getBounds())) {
            return true;
        }
        return false;
    }

    public void draw(Graphics2D g2, GameCamera camera) {
        if (redCoinImage != null) {
            setScreenY(getY() - camera.getyOffset());
            g2.drawImage(redCoinImage, getX(), getY() - camera.getyOffset(), getWidth(), getHeight(), null);
        }
    }

    private void setScreenY(int i) {
        this.screenY = i;
    }

    public Image getImage() {
        return redCoinImage;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(getX(), screenY, getWidth(), getHeight());
    }

    @Override
    public void spawnObjects(ArrayList<RedCoin> objects, int maxObjects) {
        Random random = new Random();
        while (objects.size() < maxObjects) {
            int redCoinX = random.nextInt(Constant.MAX_X_SPAWN - Constant.MIN_X_SPAWN + 1) + Constant.MIN_X_SPAWN;
            int redCoinY = random.nextInt(Constant.MAX_Y_SPAWN - Constant.MIN_Y_SPAWN + 1) + Constant.MIN_Y_SPAWN;
            objects.add(new RedCoin(redCoinX, redCoinY, 50, 50));
        }
    }
}
