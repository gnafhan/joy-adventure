package main.helper;

import main.GamePanel;
import main.entity.Player;

public class GameCamera {

    private GamePanel game;
    private float xOffset, yOffset;

    public GameCamera(GamePanel game, float xOffset, float yOffset){
        this.game = game;
        this.xOffset = xOffset;
        this.yOffset = yOffset;
    }

    public void centerOnEntity(Player e){
        xOffset = e.getX() - game.getWidth() / 2 + e.getWidth() / 2;
        yOffset = e.getY() - game.getHeight() / 2 + e.getHeight() / 2;

        yOffset = Math.min(95, yOffset);
        yOffset = Math.max(-1220, yOffset);
    }

    public void move(float xAmt, float yAmt){
        xOffset += xAmt;
        yOffset += yAmt;
    }

    public float getxOffset() {
        return xOffset;
    }

    public void setxOffset(float xOffset) {
        this.xOffset = xOffset;
    }

    public int getyOffset() {
        return (int) yOffset;
    }

    public void setyOffset(float yOffset) {
        this.yOffset = yOffset;
    }

}