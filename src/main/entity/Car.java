package main.entity;

import main.entity.interfaces.Drawable;
import main.helper.Constant;
import main.helper.GameCamera;
import main.helper.Image;

import java.awt.*;
import java.awt.image.*;
import java.io.*;
import javax.imageio.ImageIO;

public class Car extends GameObject implements Drawable {
    private BufferedImage carImage;
    private int speed;
    private int screenY;

    public Car(int x, int y, int width, int height, int speed) {
        super(x, y, width, height);
        this.speed = speed;
        loadCarImage();
    }

    private void loadCarImage() {
        carImage = Image.readSingle("res/car.png");
    }

    public void update() {
        setX( getX() + speed);
        // Gerakkan mobil ke kanan
        if (getX() > Constant.SCREEN_WIDTH) { // Jika keluar dari batas kanan layar, kembalikan ke kiri
            setX(0);
        }
    }

    public boolean checkCollision(Player player){
        Rectangle carRect = new Rectangle(getX(), getScreenY(), getWidth(), getHeight());
        Rectangle playerRect = new Rectangle(player.getX(), player.getScreenY(), player.getWidth(), player.getHeight());
        return carRect.intersects(playerRect);
    }

    public int getScreenY() {
        return screenY;
    }

    public void setScreenY(int screenY) {
        this.screenY = screenY;
    }

    public void draw(Graphics2D g2, GameCamera camera) {
        if (carImage != null) {
            int x = getX();
            int y = getY() - camera.getyOffset();
            int width = getWidth();
            int height = getHeight();
            setScreenY(y);
            g2.drawImage(carImage, x, (int) y , width, height, null);
        } else {
            g2.setColor(Color.RED);
            g2.fillRect(getX(), getY(), getWidth(), getHeight());
        }
    }
}
