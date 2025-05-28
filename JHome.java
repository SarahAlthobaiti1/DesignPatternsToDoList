/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.designpatternsproject;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;


// JHome class represents the main home screen GUI with buttons to navigate to other services
public class JHome extends JFrame {

    public JHome() {

        // General frame settings
        setTitle("Home");
        setSize(550, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Load and set background image
        JLabel background = new JLabel(new ImageIcon(getClass().getResource("/images/photo2.jpg")));
        background.setLayout(new GridBagLayout());

        add(background);

        // Main panel with 6 buttons
        JPanel mainPanel = new JPanel(new GridLayout(2, 3, 10, 10));
        mainPanel.setOpaque(false);

        // Add 6 panels (each with image and button)
        mainPanel.add(createStyledPanel("Task", "/images/Task.png", "Task"));
        mainPanel.add(createStyledPanel("Help", "/images/Help.png", "Help"));
        mainPanel.add(createStyledPanel("About Us", "/images/AboutIs.png", "AboutUs"));
        mainPanel.add(createStyledPanel("Settings", "/images/Settings.png", "Settings"));
        mainPanel.add(createStyledPanel("Manage Account", "/images/management.png", "ManageAccount"));
        mainPanel.add(createStyledPanel("Reminders", "/images/Reminders.png", "Reminders"));

        // Add the main panel to the background
        background.add(mainPanel);

        setVisible(true); // Show the window
    }

    // Method to create a styled panel with an image and a button
    private JPanel createStyledPanel(String buttonText, String imagePath, String frameIdentifier) {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(new Color(245, 245, 220)); // Beige
        panel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 8));

        JLabel imageLabel = new JLabel(new ImageIcon(getClass().getResource(imagePath)));

        JButton button = new JButton(buttonText);
        button.setPreferredSize(new Dimension(200, 30));
        button.setFont(new Font("Serif", Font.BOLD, 16));
        button.setBorderPainted(false);
        button.setContentAreaFilled(false);

        if (buttonText.equals("Task")) {
          button.setForeground(Color.BLUE);
      } else if (buttonText.equals("About Us")) {
          button.setForeground(Color.BLUE);
      }

        // Action when button is clicked
        button.addActionListener(e -> {
            // If-else to handle the frame navigation based on the string identifier
            if (frameIdentifier.equals("Task")) {
            	
               new JTask(new TaskManager());
                JHome.this.dispose(); // Dispose of the current home frame
            } else if (frameIdentifier.equals("AboutUs")) {
                new JAboutUs();
                JHome.this.dispose(); // Dispose of the current home frame
            } else {
                JOptionPane.showMessageDialog(this, buttonText + " clicked!");
            }
        });

        panel.add(imageLabel, BorderLayout.NORTH); // Add image to top
        panel.add(button, BorderLayout.SOUTH); // Add button to bottom
        return panel; // Return the complete panel
    }
}
