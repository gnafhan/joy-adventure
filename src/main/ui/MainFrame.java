package main.ui;

import main.GamePanel;
import main.entity.Direction;
import main.helper.Constant;
import main.helper.KeyHandler;
import main.helper.Session;
import main.helper.database.repository.UserRepository;
import main.ui.page.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

public class MainFrame extends JFrame {
    private CardLayout cardLayout;
    private JPanel cardPanel;
    private int coinCount = 1000;
    private KeyHandler keyHandler;



    public MainFrame() {
        Session.setUserRepository(new UserRepository());
        setTitle("Kang Paket Simulator");
        setSize(Constant.SCREEN_WIDTH, Constant.SCREEN_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        this.keyHandler = new KeyHandler();
        cardPanel.addKeyListener(this.keyHandler);



        // Create pages
        GamePanel gamePanel = new GamePanel(this);
        ShopPage shopPage = new ShopPage(this, gamePanel);
        LoginPage loginPage = new LoginPage(this, gamePanel);
        LeaderboardPage leaderboardPage = new LeaderboardPage(this);
        GameFinishPanel gameFinishPanel = new GameFinishPanel(gamePanel, this);
        GameLosePanel gameLosePanel = new GameLosePanel(gamePanel, this);
        MainMenuPage mainMenuPage = new MainMenuPage(this, gamePanel, cardPanel, loginPage, leaderboardPage, shopPage);



        cardPanel.setFocusable(true);
//        requestFocusInWindow();

        cardPanel.add(mainMenuPage, "MainMenu");
        cardPanel.add(gamePanel, "Game");
        cardPanel.add(shopPage, "Shop");
        cardLayout.show(cardPanel, "MainMenu");
        cardPanel.add(leaderboardPage, "Leaderboard");
        cardPanel.add(gameFinishPanel, "GameFinish");
        cardPanel.add(gameLosePanel, "GameLose");
//        cardPanel.add(loginPage, "Login");

        add(cardPanel);

        setResizable(false);
        setVisible(true);
    }

    public void switchToPage(String pageName) {
        cardLayout.show(cardPanel, pageName);
    }

    public int getCoinCount() {
        return coinCount;
    }

    public void updateCoinCount(int newCount) {
        coinCount = newCount;
        if (cardPanel.getComponent(0) instanceof MainMenuPage) {
            ((MainMenuPage) cardPanel.getComponent(0)).updateCoinLabel();
        }
        if (cardPanel.getComponent(1) instanceof ShopPage) {
            ((ShopPage) cardPanel.getComponent(1)).updateCoinLabel();
        }
    }

    public KeyHandler getKeyHandler() {
        return keyHandler;
    }

}

