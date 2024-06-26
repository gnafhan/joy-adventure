package main.entity.interfaces;

import main.helper.GameCamera;

import java.awt.*;

public interface Drawable {
    default void draw(Graphics2D g2){};
    default void draw(Graphics2D g2, int offsetY){};
    default void draw(Graphics2D g2, GameCamera camera){};
}
