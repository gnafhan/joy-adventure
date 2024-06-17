package main.ui.page;

import main.ui.MainFrame;

import javax.swing.*;
import java.awt.*;

public class ShopPage extends JPanel {
    private MainFrame mainFrame;
    private JLabel coinLabel;

    public ShopPage(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(null);

        // Create a background panel
        BackgroundPanel backgroundPanel = new BackgroundPanel("res/background.png");
        backgroundPanel.setBounds(0, 0, 800, 600);
        add(backgroundPanel);

        // Create and add the coin logo
        JLabel coinLogo = new JLabel(new ImageIcon("coin_logo.png"));
        coinLogo.setBounds(450, 10, 50, 50); // Adjust size and position as needed
        backgroundPanel.add(coinLogo);

        // Create and add the coin count label
        coinLabel = new JLabel(String.valueOf(mainFrame.getCoinCount()));
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
                mainFrame.updateCoinCount(mainFrame.getCoinCount() - 100);
            });
        }

        // Create and add the back button
        JButton backButton = new JButton("Back");
        backButton.setBounds(350, 500, 100, 30);
        backgroundPanel.add(backButton);

        // Add action listener for the back button
        backButton.addActionListener(e -> mainFrame.switchToPage("MainMenu"));
    }

    public void updateCoinLabel() {
        coinLabel.setText(String.valueOf(mainFrame.getCoinCount()));
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
