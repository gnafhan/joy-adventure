package main.ui;

import main.GamePanel;
import main.entity.Direction;
import main.helper.Constant;
import main.helper.KeyHandler;
import main.ui.page.LoginPage;
import main.ui.page.MainMenuPage;
import main.ui.page.ShopPage;

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
        setTitle("Steampunk GUI");
        setSize(Constant.SCREEN_WIDTH, Constant.SCREEN_HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        this.keyHandler = new KeyHandler();
        cardPanel.addKeyListener(this.keyHandler);


        // Create pages
        MainMenuPage mainMenuPage = new MainMenuPage(this);
        ShopPage shopPage = new ShopPage(this);
        GamePanel gamePanel = new GamePanel(this);
        LoginPage loginPage = new LoginPage(this);

        cardPanel.setFocusable(true);
        requestFocusInWindow();

        cardPanel.add(mainMenuPage, "MainMenu");
        cardPanel.add(shopPage, "Shop");
        cardPanel.add(gamePanel, "Game");
        cardPanel.add(loginPage, "Login");

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

    public static void main(String[] args) {
        new MainFrame();
    }
}

