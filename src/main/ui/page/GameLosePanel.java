package main.ui.page;

import main.GamePanel;
import main.helper.Constant;
import main.helper.Session;
import main.ui.MainFrame;
import main.ui.components.Button;

import javax.swing.*;
import java.awt.*;

public class GameLosePanel extends JPanel {
    private Image backgroundImage;
    private GamePanel gamePanel;
    private MainFrame mainFrame;

    public GameLosePanel(GamePanel gamePanel, MainFrame mainFrame) {
        this.mainFrame = mainFrame;
        // Path ke gambar yang ingin ditampilkan saat game selesai
        backgroundImage = new ImageIcon("res/gameOver.png").getImage();
        this.gamePanel = gamePanel;
        this.setPreferredSize(new Dimension(Constant.SCREEN_WIDTH, Constant.SCREEN_HEIGHT));
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        // Gambar latar belakang
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);

        // Tambahkan teks di atas latar belakang
        Graphics2D g2d = (Graphics2D) g;
        g2d.setColor(Color.WHITE); // Atur warna teks
        g2d.setFont(new Font("Serif", Font.BOLD, 24)); // Atur font dan ukuran teks
        String message = Integer.toString(gamePanel.getCoinsCollected());
        int x = (getWidth() - g2d.getFontMetrics().stringWidth(message)) / 2;
        int y = (int) Math.round(getHeight() / 2.3);
        g2d.drawString(message, x, y);

        // Tambahkan teks lainnya di latar belakang
        Graphics2D g2d1 = (Graphics2D) g;
        g2d1.setColor(Color.WHITE); // Atur warna teks
        g2d1.setFont(new Font("Serif", Font.BOLD, 24)); // Atur font dan ukuran teks
        int dbCoin = Session.getUser().getDBCoinCount();
        String message1 = Integer.toString(dbCoin);
        int x1 = (getWidth() - g2d1.getFontMetrics().stringWidth(message)) / 2;
        int y1 = (int) Math.round(getHeight() / 1.7);
        g2d1.drawString(message1, x1, y1);
        this.setFocusable(true);

//        create invisible touchable area to go back to main menu
        Rectangle backButton = new Rectangle(95, Constant.SCREEN_HEIGHT-170, 105, 48);
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (backButton.contains(evt.getPoint())) {
                    mainFrame.switchToPage("MainMenu");
                }
            }
        });
//        Button backButton = new Button("res/menu-default.png", "res/menu-hover.png", "res/menu-default.png");
//        backButton.setBounds(95, Constant.SCREEN_HEIGHT-170, 105, 48);
//        backButton.addActionListener(e -> mainFrame.switchToPage("MainMenu"));
//        add(backButton);
    }
}
