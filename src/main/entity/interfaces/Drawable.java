package main.entity.interfaces;

import java.awt.*;

public interface Drawable {
    default void draw(Graphics2D g2){};
    default void draw(Graphics2D g2, int offsetY){};
}
