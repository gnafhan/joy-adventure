package main.ui;
import main.ui.components.Button;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame {

    private int coinCount = 1000; // Example starting coin count
    private JLabel coinLabel;

    public MainMenu() {
        // Set the title of the window
        setTitle(" GUI Main Menu");

        // Set the size of the window
        setSize(800, 600);

        // Set the layout of the window to null to use absolute positioning
        setLayout(null);

        // Create a background panel
        BackgroundPanel backgroundPanel = new BackgroundPanel("res/background.png");
        backgroundPanel.setBounds(0, 0, 800, 600);
        add(backgroundPanel);

        // Create and add the coin logo
        JLabel coinLogo = new JLabel(new ImageIcon("coin_logo.png"));
        coinLogo.setBounds(10, 10, 50, 50); // Adjust size and position as needed
        backgroundPanel.add(coinLogo);

        // Create and add the "Play" button with custom image
        Button playButton = new Button("res/button.png", "res/hover.png", "res/button.png");
        playButton.setBounds(300, 200, 200, 80);
        backgroundPanel.add(playButton);

        // Create and add the "Shop" button with custom image
        Button shopButton = new Button("res/store_button.png", "res/hover_store_button.png", "res/store_button.png");
        shopButton.setBounds(300, 300, 200, 80);
        backgroundPanel.add(shopButton);

        // Add action listener for the "Play" button
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Code to start the game goes here
                System.out.println("Play button clicked!");
            }
        });

        // Add action listener for the "Shop" button
        shopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Open the shop window
                new ShopWindow(coinCount);
            }
        });

        // Set the default close operation and make the window visible
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
    }

    // Background panel class to draw the background image
    class BackgroundPanel extends JPanel {
        private Image backgroundImage;

        public BackgroundPanel(String imagePath) {
            backgroundImage = new ImageIcon(imagePath).getImage();
        }

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            g.drawImage(backgroundImage, 0, 0, this.getWidth(), this.getHeight(), this);
        }
    }

    public static void main(String[] args) {
        // Create the main menu window
        new MainMenu();
    }
}
