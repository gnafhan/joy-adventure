package main.entity;

import main.entity.interfaces.Drawable;
import main.entity.interfaces.Spawner;
import main.helper.Constant;

import java.awt.*;
import java.io.IOException;
import javax.imageio.ImageIO;
import java.util.ArrayList;
import java.util.Random;

public class Diamond extends GameObject implements Spawner<Diamond>, Drawable {
    private Image diamondImage;
    private int screenY;

    public Diamond(int x, int y, int width, int height) {
        super(x, y, width, height);
        loadImage();
    }

    private void loadImage() {
        diamondImage = main.helper.Image.readSingle("res/diamond.png");
    }

    public boolean update(Player player) {
        if (player.getBounds().intersects(this.getBounds())) {
            return true;
        }
        return false;
    }

    public void draw(Graphics2D g2, int offsetY) {
        if (diamondImage != null) {
            setScreenY(getY() - offsetY);
            g2.drawImage(diamondImage, getX(), getY() - offsetY, getWidth(), getHeight(), null);
        }
    }

    private void setScreenY(int i) {
        this.screenY = i;
    }

    public Image getImage() {
        return diamondImage;
    }

    @Override
    public Rectangle getBounds() {
        return new Rectangle(getX(), screenY, getWidth(), getHeight());
    }

    @Override
    public void spawnObjects(ArrayList<Diamond> objects, int maxObjects) {
        Random random = new Random();
        while (objects.size() < maxObjects) {
            int diamondX = random.nextInt(Constant.MAX_X_SPAWN - Constant.MIN_X_SPAWN + 1) + Constant.MIN_X_SPAWN;
            int diamondY = random.nextInt(Constant.MAX_Y_SPAWN - Constant.MIN_Y_SPAWN + 1) + Constant.MIN_Y_SPAWN;
            objects.add(new Diamond(diamondX, diamondY, 50, 50));
        }
    }
}
