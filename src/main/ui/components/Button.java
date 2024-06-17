package main.ui.components;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Button extends JButton {

    private ImageIcon normalIcon;
    private ImageIcon pressedIcon;
    private ImageIcon releasedIcon;

    public Button(String normalImage, String pressedImage, String releasedImage) {
        normalIcon = new ImageIcon(normalImage);
        pressedIcon = new ImageIcon(pressedImage);
        releasedIcon = new ImageIcon(releasedImage);

        setIcon(normalIcon);
        setBorderPainted(false);
        setFocusPainted(false);
        setContentAreaFilled(false);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                setIcon(pressedIcon);
                // Start animation
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 5; i++) {
                            setLocation(getX(), getY() + 1);
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }
                    }
                }).start();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                setIcon(releasedIcon);
                // End animation
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        for (int i = 0; i < 5; i++) {
                            setLocation(getX(), getY() - 1);
                            try {
                                Thread.sleep(10);
                            } catch (InterruptedException ex) {
                                ex.printStackTrace();
                            }
                        }
                        setIcon(normalIcon);
                    }
                }).start();
            }
        });
    }
}
