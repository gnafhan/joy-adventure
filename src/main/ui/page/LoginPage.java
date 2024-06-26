package main.ui.page;

import main.GamePanel;
import main.helper.Session;
import main.ui.MainFrame;
import main.ui.components.RoundedBorder;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginPage extends JPanel {

    private JTextField usernameField;
    private JButton startButton;
    private MainFrame mainFrame;
    private GamePanel gamePanel;

    public LoginPage(MainFrame mainFrame, GamePanel gamePanel) {
        this.mainFrame = mainFrame;
        this.gamePanel = gamePanel;
        setLayout(new GridBagLayout());
        setBackground(new Color(255, 253, 208)); // Background color

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(10, 10, 10, 10);

        // Title
        JLabel titleLabel = new JLabel("Joy Adventure");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 36));
        titleLabel.setHorizontalAlignment(JLabel.CENTER);
        titleLabel.setForeground(new Color(34, 139, 34)); // Text color

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(titleLabel, gbc);

        // Username label
        JLabel usernameLabel = new JLabel("Username");
        usernameLabel.setFont(new Font("Arial", Font.PLAIN, 18));
        usernameLabel.setForeground(new Color(34, 139, 34)); // Text color

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(usernameLabel, gbc);

        // Username text field
        usernameField = new JTextField("Masukkan username", 20);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 18));
        usernameField.setForeground(new Color(160, 160, 160)); // Placeholder color
        usernameField.setBorder(new RoundedBorder(10, new Color(34, 139, 34)));
        usernameField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (usernameField.getText().equals("Masukkan username")) {
                    usernameField.setText("");
                    usernameField.setForeground(new Color(0, 0, 0));
                }
            }

            public void focusLost(java.awt.event.FocusEvent evt) {
                if (usernameField.getText().isEmpty()) {
                    usernameField.setForeground(new Color(160, 160, 160));
                    usernameField.setText("Masukkan username");
                }
            }
        });

        gbc.gridx = 1;
        gbc.gridy = 1;
        add(usernameField, gbc);

        // Start button
        startButton = new JButton("START");
        startButton.setFont(new Font("Arial", Font.BOLD, 18));
        startButton.setBackground(new Color(255, 255, 255)); // Button background color
        startButton.setForeground(new Color(34, 139, 34)); // Button text color
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                if (!username.isEmpty() && !username.equals("Masukkan username")) {
                    Session.login(username);
                    if (gamePanel.getCoinsCollected() > 0) {
                        Session.getUser().setCoinCount(gamePanel.getCoinsCollected());
                    }
                    mainFrame.switchToPage("MainMenu");
                }
            }
        });

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        add(startButton, gbc);
    }
}


