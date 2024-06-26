package main.ui.page;

import main.GamePanel;
import main.helper.Constant;
import main.helper.Session;
import main.helper.SoundManager;
import main.ui.MainFrame;
import main.ui.components.Button;

import javax.swing.*;
import java.awt.*;

public class MainMenuPage extends JPanel {
    private MainFrame mainFrame;
    private GamePanel gamePanel;
    private JLabel coinLabel;
    private JPanel cardPanel;
    private LoginPage loginPage;
    private LeaderboardPage leaderboardPage;

    public MainMenuPage(MainFrame mainFrame, GamePanel gamePanel, JPanel cardPanel, LoginPage loginPage, LeaderboardPage leaderboardPage, ShopPage shopPage) {
        this.mainFrame = mainFrame;
        this.gamePanel = gamePanel;
        this.cardPanel = cardPanel;
        this.loginPage = loginPage;
        this.leaderboardPage = leaderboardPage;

        setLayout(null);

        // Create a background panel
        BackgroundPanel backgroundPanel = new BackgroundPanel("res/bg.png");
        backgroundPanel.setBounds(0, 0, Constant.SCREEN_WIDTH, Constant.SCREEN_HEIGHT);
        add(backgroundPanel);

        backgroundPanel.setLayout(null);

        // Create and add the "Play" button with custom image
        Button playButton = new Button("res/menu-play.png", "res/menu-play-hover.png", "res/menu-play.png");
        playButton.setBounds(160, 410, 300, 78);
        backgroundPanel.add(playButton);

        // Create and add the "Shop" button with custom image
        Button shopButton = new Button("res/menu-store.png", "res/menu-store-hover.png", "res/menu-store.png");
        shopButton.setBounds(160, 510, 300, 80);
        backgroundPanel.add(shopButton);

        Button leaderboardButton = new Button("res/menu-leaderboard.png", "res/menu-leaderboard-hover.png", "res/menu-leaderboard.png");
        leaderboardButton.setBounds(160, 610, 300, 80);
        backgroundPanel.add(leaderboardButton);

        // Add action listener for the "Play" button
        playButton.addActionListener(e -> {
            // Code to start the game goes here
            if (Session.getUser()!= null){
                gamePanel.getPlayer().setSpeed(Session.getUserRepository().getSpeed(Session.getUser().getId()) + 1);
                gamePanel.getPlayer().setMagnet(Session.getUserRepository().getMagnet(Session.getUser().getId()) + 1);
            }
            gamePanel.spawnCollectables();
            gamePanel.setGameOver(false);
            SoundManager.soundManager.playMainMusic();
            cardPanel.add(loginPage, "Login");
            mainFrame.switchToPage("Game");
        });

        // Add action listener for the "Leaderboard" button
        leaderboardButton.addActionListener(e -> {
            System.out.println("aa");
            leaderboardPage.updateScoreData();
            mainFrame.switchToPage("Leaderboard");
        });

        shopButton.addActionListener(ActionListener -> {
            if (Session.getUser() != null){
                shopPage.updateSession();
                mainFrame.switchToPage("Shop");
            } else {
                JOptionPane.showMessageDialog(null, "Please login first");
                SoundManager.soundManager.stopMainMusic();
                mainFrame.switchToPage("Login");
            }
        });
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
            g.drawImage(backgroundImage, -10, -30, this.getWidth(), this.getHeight(), this);
        }
    }
}
