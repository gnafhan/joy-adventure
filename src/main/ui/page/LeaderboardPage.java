package main.ui.page;
import main.entity.LeaderboardScore;
import main.helper.Constant;
import main.helper.Session;
import main.ui.MainFrame;
import main.ui.components.Button;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;

public class LeaderboardPage extends JPanel {
    private Image backgroundImage;
    private MainFrame mainFrame;
    private Object[][] data;
    private JScrollPane scrollPane;

    public LeaderboardPage(MainFrame mainFrame) {
        // Load the background image
        this.mainFrame = mainFrame;
        try {
            backgroundImage = ImageIO.read(new File("res/leaderboard.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        // Set layout to null for absolute positioning
        setLayout(null);

        updateLeaderboard();

        // Create the button
        Button backButton = new Button("res/menu-default.png", "res/menu-hover.png", "res/menu-default.png");
        backButton.setBounds(90, Constant.SCREEN_HEIGHT-140, 105, 48);
        backButton.addActionListener(e -> mainFrame.switchToPage("MainMenu"));
        add(backButton);

        // Set preferred size for the panel
        setPreferredSize(new Dimension(backgroundImage.getWidth(null), backgroundImage.getHeight(null)));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Draw the background image
        g.drawImage(backgroundImage, 0, 0, this);
    }

    public void updateLeaderboard(){
        ArrayList<LeaderboardScore> scores = Session.getUserRepository().getLeaderboard();
        String[] columnNames = {"Rank", "Name", "Coin"};
        Object[][] data = new Object[scores.size()][3];
        for (int i = 0; i < scores.size(); i++) {
            LeaderboardScore score = scores.get(i);
            data[i][0] = i + 1;
            data[i][1] = score.getUsername();
            data[i][2] = score.getCoinCount();
        }
        JTable table = new JTable(new DefaultTableModel(data, columnNames));
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBounds(170, 370, 300, 100);
        this.scrollPane = scrollPane;
        add(scrollPane);
    }

    public void updateScoreData(){
        remove(scrollPane);
        updateLeaderboard();
    }
}
