/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.designpatternsproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


// A simple "About Us" window for the To-Do List application.
public class JAboutUs extends JFrame {

    public JAboutUs() {
        // General setting
        setTitle("About Us");
        setSize(550, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());

        // Set background image
        JLabel background = new JLabel(new ImageIcon(getClass().getResource("/images/photo2.jpg")));
        background.setLayout(null); // Use null layout for free placement of components
        add(background);

        // ==== Top panel for "<" button ====
        ImageIcon BackIcon = new ImageIcon(new ImageIcon(getClass().getResource("/images/back.png"))
                .getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));

        JButton backArrowButton = new JButton(BackIcon);
        backArrowButton.setBorderPainted(false);  // Remove the border of the button
        backArrowButton.setContentAreaFilled(false);  // Remove the background fill
        backArrowButton.setBounds(10, 10, 40, 40);  // Set the position and size of the button
        background.add(backArrowButton);

        // Content panel with border and background color
        JPanel panel = new JPanel(new BorderLayout());
        panel.setPreferredSize(new Dimension(400, 250)); // Set custom size for the panel
        panel.setBackground(new Color(245, 245, 220)); // Light beige
        panel.setBorder(BorderFactory.createLineBorder(Color.DARK_GRAY, 8)); // Thick border

        // Load the image and resize it
        JLabel imageLabel = new JLabel(new ImageIcon(new ImageIcon(getClass().getResource("/images/AboutIs.png"))
                .getImage().getScaledInstance(100, 100, Image.SCALE_SMOOTH)));

        panel.add(imageLabel, BorderLayout.NORTH); // Add resized image to the top of the panel

        // Descriptive text using HTML for line breaks and width control
        JLabel textLabel = new JLabel("<html><body style='width:400px;'>"
                + "This To-Do List app is a simple and easy way to manage your daily<br>tasks. "
                + "It helps you add, edit, delete, and check off tasks so you can<br>stay organized and focused.<br><br>"
                + "The app is built using Java and has a user-friendly GUI that anyone<br>can use. "
                + "Whether you're managing schoolwork, personal goals, or<br>daily reminders, "
                + "this app is here to help you stay on track."
                + "</body></html>");
        panel.add(textLabel, BorderLayout.CENTER); // Add text to the center of the panel

        // Position the panel manually on the background label 
        panel.setBounds(75, 75, 400, 250); // Set the position and size of the panel
        background.add(panel); // Add the panel to the background

        // Add an ActionListener to the backArrowButton to handle the click event for the back arrow
        backArrowButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new JHome(); // Open the JHome frame when the back arrow is clicked
                dispose(); // Close the current window/frame after navigating back
            }
        });

        // Show the window
        setVisible(true);
    }
}
