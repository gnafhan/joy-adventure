package main.ui.page;

import main.GamePanel;
import main.helper.Session;
import main.ui.MainFrame;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ShopPage extends JPanel {
    private int coins ;
    private int item1Level;
    private int item2Level;
    private final int[] item1UpgradeCosts = {10, 20, 30};
    private final int[] item2UpgradeCosts = {15, 25, 35};

    private JLabel coinsLabel;
    private JButton item1UpgradeButton;
    private JButton item2UpgradeButton;
    private JButton backButton; // Back button
    private JPanel item1BarsPanel;
    private JPanel item2BarsPanel;
    private JPanel coinsPanel;
    private JLabel item1Image;
    private JLabel item2Image;
    private JLabel coinCountLabel;

    private Image backgroundImage;

    private GamePanel gamePanel;

    // Default button size
    private Dimension buttonSize = new Dimension(140, 40); // Increased button size

    private int item1SpeedBoost = 2; // Kecepatan tambahan dari Item 1

    public void updateSession(){
        coins = Session.getUser().getDBCoinCount(); // Jumlah koin yang dimiliki
        item1Level = Session.getUserRepository().getMagnet(Session.getUser().getId());
        item2Level = Session.getUserRepository().getSpeed(Session.getUser().getId());
        this.coinsPanel.remove(this.coinCountLabel);
        this.coinCountLabel = new JLabel("x" + coins);
        this.coinsPanel.add(this.coinCountLabel);
        updateUIComponents();

    }
    public ShopPage(MainFrame mainFrame, GamePanel gamePanel) {
        this.gamePanel = gamePanel;
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();

        // Load background image
        backgroundImage = new ImageIcon("res/shoppage.png").getImage();

        // Bagian untuk menampilkan jumlah koin
        JPanel coinsPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT)); // Align to the right
        coinsPanel.setOpaque(false);
        this.coinsPanel = coinsPanel;
        coinsLabel = new JLabel(new ImageIcon("res/coin.png"));
        JLabel coinCountLabel = new JLabel("x" + coins);
        coinsPanel.add(coinsLabel);
        this.coinCountLabel = coinCountLabel;
        coinsPanel.add(this.coinCountLabel);

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(1, 0, 30, 0); // Increased top and bottom insets
        gbc.anchor = GridBagConstraints.EAST;
        add(coinsPanel, gbc);

        // Bagian untuk item 1
        JPanel item1Panel = new JPanel(new BorderLayout());
        item1Panel.setOpaque(false);

        item1Image = new JLabel(new ImageIcon("res/magnet.png"));
        item1Panel.add(item1Image, BorderLayout.CENTER);

        JPanel item1BarsAndButtonPanel = new JPanel(new BorderLayout());
        item1BarsAndButtonPanel.setOpaque(false);

        // Panel with border for item 1 bars
        JPanel item1BarsBorderPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 1, 1));
        item1BarsBorderPanel.setOpaque(false);
        item1BarsBorderPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLUE), "MAGNET", TitledBorder.CENTER, TitledBorder.TOP));

        item1BarsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        item1BarsPanel.setOpaque(false);
        item1BarsBorderPanel.add(item1BarsPanel);
        item1BarsAndButtonPanel.add(item1BarsBorderPanel, BorderLayout.NORTH);

        item1UpgradeButton = new JButton();
        updateItem1UpgradeButtonText(); // Set initial text
        item1UpgradeButton.addActionListener(new UpgradeItem1Listener());
        item1UpgradeButton.setFont(new Font("Arial", Font.PLAIN, 12));
        item1UpgradeButton.setPreferredSize(buttonSize);
        item1BarsAndButtonPanel.add(item1UpgradeButton, BorderLayout.SOUTH);

        item1Panel.add(item1BarsAndButtonPanel, BorderLayout.SOUTH);

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(20, 0, 20, 0); // Increased top and bottom insets
        gbc.anchor = GridBagConstraints.NORTH;
        add(item1Panel, gbc);

        // Bagian untuk item 2
        JPanel item2Panel = new JPanel(new BorderLayout());
        item2Panel.setOpaque(false);

        item2Image = new JLabel(new ImageIcon("res/speedup.png"));
        item2Panel.add(item2Image, BorderLayout.CENTER);

        JPanel item2BarsAndButtonPanel = new JPanel(new BorderLayout());
        item2BarsAndButtonPanel.setOpaque(false);

        // Panel with border for item 2 bars
        JPanel item2BarsBorderPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 1, 1));
        item2BarsBorderPanel.setOpaque(false);
        item2BarsBorderPanel.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(Color.BLUE), "SPEED UP", TitledBorder.CENTER, TitledBorder.TOP));

        item2BarsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 5));
        item2BarsPanel.setOpaque(false);
        item2BarsBorderPanel.add(item2BarsPanel);
        item2BarsAndButtonPanel.add(item2BarsBorderPanel, BorderLayout.NORTH);

        item2UpgradeButton = new JButton();
        updateItem2UpgradeButtonText(); // Set initial text
        item2UpgradeButton.addActionListener(new UpgradeItem2Listener());
        item2UpgradeButton.setFont(new Font("Arial", Font.PLAIN, 12));
        item2UpgradeButton.setPreferredSize(buttonSize);
        item2BarsAndButtonPanel.add(item2UpgradeButton, BorderLayout.SOUTH);

        item2Panel.add(item2BarsAndButtonPanel, BorderLayout.SOUTH);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 1;
        gbc.insets = new Insets(20, 0, 20, 0); // Increased top and bottom insets
        gbc.anchor = GridBagConstraints.NORTH;
        add(item2Panel, gbc);

        // Bagian untuk tombol "Back"
        backButton = new JButton("Back");
        backButton.setFont(new Font("Arial", Font.PLAIN, 8));
        backButton.setPreferredSize(buttonSize);
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mainFrame.switchToPage("MainMenu");
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0, 0, 20, 0); // Set bottom inset for button positioning
        gbc.anchor = GridBagConstraints.SOUTH; // Center the button at the bottom
        add(backButton, gbc);

        updateBars();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(backgroundImage, 0, 0, getWidth(), getHeight(), this);
    }

    private void updateBars() {
        item1BarsPanel.removeAll();
        item2BarsPanel.removeAll();

        for (int i = 0; i < 3; i++) {
            JPanel item1Bar = new JPanel();
            item1Bar.setBackground(i < item1Level ? Color.GREEN : Color.WHITE);
            item1Bar.setPreferredSize(new Dimension(60, 40)); // Bars
            item1BarsPanel.add(item1Bar);

            JPanel item2Bar = new JPanel();
            item2Bar.setBackground(i < item2Level ? Color.GREEN : Color.WHITE);
            item2Bar.setPreferredSize(new Dimension(60, 40)); // Bars
            item2BarsPanel.add(item2Bar);
        }

        revalidate();
        repaint();
    }

    public void updateCoinLabel() {
    }

    private class UpgradeItem1Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (item1Level < 3 && coins >= item1UpgradeCosts[item1Level]) {
                coins -= item1UpgradeCosts[item1Level];
                item1Level++;
                Session.getUser().updateDBCoinCount(coins);
                Session.getUser().updateDBMagnet(item1Level);
                updateUIComponents();
            }
        }
    }

    private class UpgradeItem2Listener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (item2Level < 3 && coins >= item2UpgradeCosts[item2Level]) {
                coins -= item2UpgradeCosts[item2Level];
                item2Level++;
                Session.getUser().updateDBCoinCount(coins);
                Session.getUser().updateDBSpeed(item2Level);
                gamePanel.getPlayer().setSpeed(item2Level + 1);
                updateUIComponents();
            }
        }
    }

    private void updateUIComponents() {
        ((JLabel) coinsLabel.getParent().getComponent(1)).setText(" " + coins);

        updateBars();
        updateItem1UpgradeButtonText();
        updateItem2UpgradeButtonText();
    }

    private void updateItem1UpgradeButtonText() {
        if (item1Level < 3) {
            item1UpgradeButton.setText("<html><center><img src='file:res/coinmini.png'> " + item1UpgradeCosts[item1Level] + "</center></html>");
        } else {
            item1UpgradeButton.setText("<html><center>Max Level</center></html>");
            item1UpgradeButton.setEnabled(false);
        }
    }

    private void updateItem2UpgradeButtonText() {
        if (item2Level < 3) {
            item2UpgradeButton.setText("<html><center><img src='file:res/coinmini.png'> " + item2UpgradeCosts[item2Level] + "</center></html>");
        } else {
            item2UpgradeButton.setText("<html><center>Max Level Reached</center></html>");
            item2UpgradeButton.setEnabled(false);
        }
    }

    public void setButtonSize(Dimension size) {
        this.buttonSize = size;
        item1UpgradeButton.setPreferredSize(buttonSize);
        item2UpgradeButton.setPreferredSize(buttonSize);
        revalidate();
        repaint();
    }

    public void setBackButtonSize(Dimension size) {
        backButton.setPreferredSize(size);
        revalidate();
        repaint();
    }

//    public static void main(String[] args) {
//        JFrame frame = new JFrame("Shop Page");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        frame.setSize(621, 400); // Adjusted size to fit bars horizontally
//        ShopPage shopPage = new ShopPage(frame);
//        frame.add(shopPage);
//        frame.setVisible(true);
//    }
}
