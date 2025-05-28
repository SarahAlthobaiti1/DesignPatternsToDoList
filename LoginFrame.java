/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.designpatternsproject;


import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// This class defines the GUI frame for the login screen.
public class LoginFrame extends JFrame {
    private JButton backArrowButton;
    private JLabel background;

    public LoginFrame() {
        // General settings
        setTitle("Login");
        setSize(505, 400); // Set the size of the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Centers the window on the screen

        // Create a JLabel for the background image
        background = new JLabel(new ImageIcon(getClass().getResource("/images/photo2.jpg")));
        background.setLayout(null); // Set layout to null for free placement of components
        background.setBounds(0, 0, getWidth(), getHeight()); // Ensure background covers the entire window
        add(background); // Add background to frame

        // ==== Top panel for "<" button ====
        ImageIcon BackIcon = new ImageIcon(new ImageIcon(getClass().getResource("/images/back.png"))
                .getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));

        backArrowButton = new JButton(BackIcon);
        backArrowButton.setBorderPainted(false);  // Remove the border of the button to make it look like an image
        backArrowButton.setContentAreaFilled(false);  // Remove the background fill
        backArrowButton.setBounds(10, 10, 40, 40);  // Set the position and size of the button on the background panel
        background.add(backArrowButton); // Add the backArrowButton to the background panel

        // ==== Main form panel for login ====
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(3, 2, 15, 15)); 
        formPanel.setOpaque(false); // Make formPanel transparent

        // Add form components
        JLabel userLabel = new JLabel("Username:");    // Create a label for the username field
        userLabel.setFont(new Font("Serif", Font.BOLD, 20));
        userLabel.setForeground(Color.WHITE); // text color
        JTextField userText = new JTextField(); // Create a text field for entering the username

        JLabel passLabel = new JLabel("Password:");    // Create a label for the password field
        passLabel.setFont(new Font("Serif", Font.BOLD, 20));
        passLabel.setForeground(Color.WHITE); // text color
        JPasswordField passText = new JPasswordField(); // Create a password field for entering the password

        // Create a JButton for login
        JButton loginButton = new JButton("Login");
        loginButton.setFont(new Font("Serif", Font.BOLD, 16));
        loginButton.setForeground(Color.WHITE);
        loginButton.setBackground(new Color(1, 1, 20));

        // Add components to the formPanel
        formPanel.add(userLabel);
        formPanel.add(userText);
        formPanel.add(passLabel);
        formPanel.add(passText);
        formPanel.add(new JLabel()); // empty space for alignment
        formPanel.add(loginButton);

        // Set the formPanel's position and size
        formPanel.setBounds(50, 100, 400, 150); 
        background.add(formPanel); // Add formPanel to the background label

        setVisible(true);

        // Action for login button
        loginButton.addActionListener(new ActionListener() {  // Add an ActionListener to the loginButton to handle the click event

            public void actionPerformed(ActionEvent e) {
                
                // Retrieve and trim the values entered by the user for each field
                String username = userText.getText().trim();
                String password = new String(passText.getPassword());

                // Check if any field is empty and show an error message
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in both fields.");
                } 
                // If all checks pass, show a success message 
                else {
                    JOptionPane.showMessageDialog(null, "Login successful! Welcome " + username);
                    new JHome(); // Open the home frame after login
                    dispose(); // Close the current window
                }
            } // end actionPerformed
        }); // end loginButton action

        // Add an ActionListener to the backArrowButton to handle the click event for the back arrow
        backArrowButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new JWelcome(); // Open the JWelcome frame when the back arrow is clicked
                dispose(); // Close the current window/frame after navigating back
            } // end actionPerformed
        }); // end backArrowButton action
        
        
    } // end constructor
} // end class
