package main.ui.page;

import main.helper.Constant;
import main.ui.MainFrame;
import main.ui.components.Button;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

public class LoginPage extends JPanel {
    public LoginPage(MainFrame mainFrame) {
        super(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.anchor = GridBagConstraints.WEST;

        Border customBorder = BorderFactory.createLineBorder(Color.GRAY, 2, true);
        JLabel usernameLabel = new JLabel("Username:");
        JTextField usernameField = new JTextField(15);
        usernameField.setBorder(customBorder);
        usernameField.setBackground(Color.WHITE);
        usernameField.setForeground(Color.BLACK);
        usernameField.setFont(new Font("Arial", Font.PLAIN, 14));

        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(15);
        passwordField.setBorder(customBorder);
        passwordField.setBackground(Color.WHITE);
        passwordField.setForeground(Color.BLACK);
        passwordField.setFont(new Font("Arial", Font.PLAIN, 14));

        gbc.gridx = 0;
        gbc.gridy = 0;
        this.add(usernameLabel, gbc);

        gbc.gridx = 1;
        this.add(usernameField, gbc);

        gbc.gridx = 0;
        gbc.gridy = 1;
        this.add(passwordLabel, gbc);

        gbc.gridx = 1;
        this.add(passwordField, gbc);

        JButton submitButton = new JButton("Submit");
        submitButton.setFont(new Font("Arial", Font.BOLD, 14));
        submitButton.setBackground(Color.LIGHT_GRAY);
        submitButton.setForeground(Color.BLACK);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        this.add(submitButton, gbc);

        submitButton.addActionListener(e -> {
            // Code to validate login credentials goes here
            // Get the username and password
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());

            // Print the username and password to the console
            System.out.println("Username: " + username);
            System.out.println("Password: " + password);

//            mainFrame.switchToPage("MainMenu");
        });
    }
}
