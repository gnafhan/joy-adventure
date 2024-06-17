package main.entity;

import main.entity.interfaces.Drawable;
import main.helper.Constant;
import main.helper.Image;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;

public class Wood extends GameObject implements Drawable {
    private BufferedImage woodImage;
    private int speed;

    public Wood(int x, int y, int width, int height, int speed) {
        super(x, y, width, height);
        this.speed = speed;
        loadWoodImage();
    }

    private void loadWoodImage() {
       woodImage = Image.readSingle("res/batang.png");
    }

    public void update() {
        setX(getX() + speed); // Gerakkan kayu ke kanan
        if (getX() > Constant.SCREEN_WIDTH) { // Jika keluar dari batas kanan layar, kembalikan ke kiri
            setX(0);
        }
    }

    public void draw(Graphics2D g2, int offsetY) {
        if (woodImage != null) {
            int x = getX();
            int y = getY();
            int width = getWidth();
            int height = getHeight();
            g2.drawImage(woodImage, x, y - offsetY, width, height, null);
        } else {
            g2.setColor(Color.RED);
            g2.fillRect(getX(), getY(), getWidth(), getHeight());
        }
    }
}
