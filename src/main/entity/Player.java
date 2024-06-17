package main.entity;

import main.entity.interfaces.Drawable;
import main.entity.interfaces.Movable;
import main.helper.Constant;
import main.helper.FileRead;
import main.helper.Image;
import main.helper.KeyHandler;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

public class Player extends GameObject implements Drawable, Movable {
    private int speed;
    private Direction direction;
    private int score;
    private int spriteCounter = 0;
    private int spriteNum = 0;
    private String imagePath;
    private BufferedImage image;
    private ArrayList<String> pos;
    private String sprite;
    public int offsetY=0;

    private boolean isIdle = true;
    HashMap<String, ArrayList<String>> dataMap;

    public Player(int x, int y, int width, int height, int speed, Direction direction, int score, String imagePath, String textPath) {
        super(x, y, width, height);
        this.speed = speed;
        this.direction = direction;
        this.score = score;
        this.imagePath = imagePath;
        dataMap = FileRead.read(textPath);
        pos = dataMap.get("sprite5");
        this.image = Image.read(imagePath, Integer.parseInt(pos.get(0)), Integer.parseInt(pos.get(1)), Integer.parseInt(pos.get(2)), Integer.parseInt(pos.get(3)));
//        g2.setBackground(Color.BLUE);
    }

    private boolean isValidMove(int x, int y){
        return x >= 0 && x <= 520 && y >= 0 && y <= 682;
    }

    private boolean checkCollision(GameObject obj){
        switch (this.direction){
            case UP:
                return this.getX() < obj.getX() + obj.getWidth() && this.getX() + this.getWidth() > obj.getX() && this.getY() - this.speed < obj.getY() + obj.getHeight() && this.getY() > obj.getY();
            case DOWN:
                return this.getX() < obj.getX() + obj.getWidth() && this.getX() + this.getWidth() > obj.getX() && this.getY() + this.getHeight() + this.speed > obj.getY() && this.getY() < obj.getY();
            case LEFT:
                return this.getX() - this.speed < obj.getX() + obj.getWidth() && this.getX() > obj.getX() && this.getY() < obj.getY() + obj.getHeight() && this.getY() + this.getHeight() > obj.getY();
            case RIGHT:
                return this.getX() + this.getWidth() + this.speed > obj.getX() && this.getX() < obj.getX() && this.getY() < obj.getY() + obj.getHeight() && this.getY() + this.getHeight() > obj.getY();
        }
        return false;
    }

    public void move_up(){
        if (isValidMove(getX(), getY() - speed)){
            if (Constant.SCREEN_HEIGHT/2 > getY() && offsetY > -1305){
                offsetY -= speed;
                System.out.println("offsetY: " + offsetY);
            } else {
                setY(getY() - speed);
            }
        }
    }
    public void move_down(){
        if (isValidMove(getX(), getY() + speed)){
            if (Constant.SCREEN_HEIGHT/2 < getY() && offsetY < 0){
                offsetY += speed;
                System.out.println("offsetY: " + offsetY);
            } else {
                setY(getY() + speed);
            }
        }
    }
    public void move_left(){
        if (isValidMove(getX() - speed, getY())){
            setX(getX() - speed);
        }
    }
    public void move_right(){
        if (isValidMove(getX() + speed, getY())){
            setX(getX() + speed);
        }
    }

    public void update(KeyHandler input, ArrayList<GameObject> objects){
        if (input != null){

            input.poll();
            for (GameObject obj: objects){
                if (checkCollision(obj)){
                    System.out.println("Collision detected");
                    break;
                }
            }
            if (input.isKeyDown(KeyEvent.VK_UP)){
                this.direction = Direction.UP;
                move_up();
                this.isIdle = false;
                System.out.println(getX()+ ", " +  getY());
            }
            if (input.isKeyDown(KeyEvent.VK_DOWN)){
                this.direction = Direction.DOWN;
                move_down();
                this.isIdle = false;
                System.out.println(getX()+ ", " +  getY());

            }
            if (input.isKeyDown(KeyEvent.VK_LEFT)){
                this.direction = Direction.LEFT;
                move_left();
                this.isIdle = false;
                System.out.println(getX()+ ", " +  getY());

            }
            if (input.isKeyDown(KeyEvent.VK_RIGHT)){
                this.direction = Direction.RIGHT;
                move_right();
                this.isIdle = false;
                System.out.println(getX()+ ", " +  getY());

            }
            if (input.isKeyUp(KeyEvent.VK_UP) && input.isKeyUp(KeyEvent.VK_DOWN) && input.isKeyUp(KeyEvent.VK_LEFT) && input.isKeyUp(KeyEvent.VK_RIGHT)){
                this.isIdle = true;
            }
        }
        spriteCounter+=1;
        if (spriteCounter > 10 * (isIdle ? 3: 1)){
            spriteNum = 1;
        }
        if( spriteCounter > 20 * (isIdle ? 3: 1)){
            spriteNum = 2;
        }
        if (spriteCounter > 30 * (isIdle ? 3: 1)){
            spriteNum = 3;
        }
        if (spriteCounter > 40 * (isIdle ? 3: 1)){
            spriteNum = 0;
            spriteCounter = 0;
        }
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void draw(Graphics2D g2){
        if (isIdle){
            spriteNum = 1;
        }
        if (this.direction == Direction.UP){
            switch (spriteNum){
                case 0:
                    pos = dataMap.get("sprite13");
                    break;
                case 1:
                    pos = dataMap.get("sprite14");
                    break;
                case 2:
                    pos = dataMap.get("sprite15");
                    break;
                case 3:
                    pos = dataMap.get("sprite16");
                    break;
            }
        } else if (this.direction == Direction.DOWN){
            switch (spriteNum){
                case 0:
                    pos = dataMap.get("sprite1");
                    break;
                case 1:
                    pos = dataMap.get("sprite2");
                    break;
                case 2:
                    pos = dataMap.get("sprite3");
                    break;
                case 3:
                    pos = dataMap.get("sprite4");
                    break;
            }
        } else if (this.direction == Direction.LEFT){
            switch (spriteNum){
                case 0:
                    pos = dataMap.get("sprite5");
                    break;
                case 1:
                    pos = dataMap.get("sprite6");
                    break;
                case 2:
                    pos = dataMap.get("sprite7");
                    break;
                case 3:
                    pos = dataMap.get("sprite8");
                    break;
            }
        } else if (this.direction == Direction.RIGHT){
            switch (spriteNum){
                case 0:
                    pos = dataMap.get("sprite9");
                    break;
                case 1:
                    pos = dataMap.get("sprite10");
                    break;
                case 2:
                    pos = dataMap.get("sprite11");
                    break;
                case 3:
                    pos = dataMap.get("sprite12");
                    break;
            }
        }
        this.image = Image.read(imagePath, Integer.parseInt(pos.get(0)), Integer.parseInt(pos.get(1)), Integer.parseInt(pos.get(2)), Integer.parseInt(pos.get(3)));
        g2.drawImage(image, getX(), getY(), getWidth(), getHeight(), null);
    }

}

