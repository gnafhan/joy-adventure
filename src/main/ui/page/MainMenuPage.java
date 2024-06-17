package main.ui.page;

import main.helper.Constant;
import main.ui.MainFrame;
import main.ui.components.Button;

import javax.swing.*;
import java.awt.*;

public class MainMenuPage extends JPanel {
    private MainFrame mainFrame;
    private JLabel coinLabel;

    public MainMenuPage(MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        setLayout(null);

        // Create a background panel
        BackgroundPanel backgroundPanel = new BackgroundPanel("res/background.png");
        backgroundPanel.setBounds(0, 0, Constant.SCREEN_WIDTH, Constant.SCREEN_HEIGHT);
        add(backgroundPanel);

//        Create a heading text to display the title of the game with margin top 300
        JLabel heading = new JLabel("\n  Game Nyebrang \n Jalan");
        heading.setFont(new Font("Arial", Font.BOLD, 50));
        heading.setForeground(Color.WHITE);
        heading.setBounds(300, 100, 300, 300);
        backgroundPanel.add(heading);


        // Create and add the "Play" button with custom image
        Button playButton = new Button("res/button.png", "res/hover.png", "res/button.png");
        playButton.setBounds(300, 200, 200, 80);
        backgroundPanel.add(playButton);

        // Create and add the "Shop" button with custom image
        Button shopButton = new Button("res/store_button.png", "res/hover_store_button.png", "res/store_button.png");
        shopButton.setBounds(300, 300, 200, 80);
        backgroundPanel.add(shopButton);

        // Add action listener for the "Play" button
        playButton.addActionListener(e -> {
            // Code to start the game goes here
            mainFrame.switchToPage("Game");
        });

        // Add action listener for the "Shop" button
        shopButton.addActionListener(e -> mainFrame.switchToPage("Shop"));
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
