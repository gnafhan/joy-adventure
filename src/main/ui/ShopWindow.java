package main.ui;

import javax.swing.*;
import java.awt.*;

public class ShopWindow extends JFrame {

    private int coinCount;
    private JLabel coinLabel;

    public ShopWindow(int initialCoins) {
        coinCount = initialCoins;

        // Set the title of the window
        setTitle("Shop");

        // Set the size of the window
        setSize(600, 400);

        // Set the layout of the window
        setLayout(null);

        // Create a background panel
        BackgroundPanel backgroundPanel = new BackgroundPanel("shop_background.png");
        backgroundPanel.setBounds(0, 0, 600, 400);
        add(backgroundPanel);

        // Create and add the coin logo
        JLabel coinLogo = new JLabel(new ImageIcon("coin_logo.png"));
        coinLogo.setBounds(450, 10, 50, 50); // Adjust size and position as needed
        backgroundPanel.add(coinLogo);

        // Create and add the coin count label
        coinLabel = new JLabel(String.valueOf(coinCount));
        coinLabel.setFont(new Font("Serif", Font.BOLD, 24)); // Adjust font as needed
        coinLabel.setForeground(Color.YELLOW); // Adjust color as needed
        coinLabel.setBounds(510, 10, 100, 50); // Adjust size and position as needed
        backgroundPanel.add(coinLabel);

        // Add buy buttons
        for (int i = 0; i < 6; i++) {
            JButton buyButton = new JButton("Buy");
            buyButton.setBounds(80 + (i % 3) * 150, 100 + (i / 3) * 120, 100, 30);
            backgroundPanel.add(buyButton);

            buyButton.addActionListener(e -> {
                // Deduct coins as an example
                updateCoinCount(coinCount - 100);
            });
        }

        // Set the default close operation and make the window visible
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

    private void updateCoinCount(int newCount) {
        coinCount = newCount;
        coinLabel.setText(String.valueOf(coinCount));
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
}

