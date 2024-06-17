package main;

import main.helper.KeyHandler;
import main.ui.MainFrame;

import javax.swing.*;

public class Game {
    private JFrame gameFrame;
    private JPanel gamePanel;

    public void start(){
        createGamePanel();
        createGameFrame();
    }

    private void createGameFrame(){
        gameFrame = new JFrame("Game Nyebrangin Jalan");
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setResizable(false);

        gameFrame.add(gamePanel);
        gameFrame.pack();
        gameFrame.setVisible(true);
    }

    private void createGamePanel(){
        gamePanel = new GamePanel(new MainFrame());
    }
}
