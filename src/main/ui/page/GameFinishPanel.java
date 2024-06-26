package main.ui.page;

import main.GamePanel;
import main.helper.Constant;
import main.helper.Session;
import main.ui.MainFrame;

import javax.swing.*;
import java.awt.*;

public class GameFinishPanel extends JPanel {
    private Image backgroundImage;
    private GamePanel gamePanel;
    private MainFrame mainFrame;

    public GameFinishPanel(GamePanel gamePanel, MainFrame mainFrame) {
        this.gamePanel = gamePanel;
        this.mainFrame = mainFrame;
        // Path ke gambar yang ingin ditampilkan saat game selesai
        backgroundImage = new ImageIcon("res/berhasil_1.png").getImage();
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

        Graphics2D g2d1 = (Graphics2D) g;
        g2d.setColor(Color.WHITE); // Atur warna teks
        g2d.setFont(new Font("Serif", Font.BOLD, 24)); // Atur font dan ukuran teks
        int dbCoin = Session.getUser().getDBCoinCount();
        String message1 = Integer.toString(dbCoin);
        int x1 = (getWidth() - g2d.getFontMetrics().stringWidth(message)) / 2;
        int y1 = (int) Math.round(getHeight() / 1.7);
        g2d.drawString(message1, x1, y1);

        Rectangle backButton = new Rectangle(95, Constant.SCREEN_HEIGHT-170, 105, 48);
        this.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (backButton.contains(evt.getPoint())) {
                    mainFrame.switchToPage("MainMenu");
                }
            }
        });
    }
}

