/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.designpatternsproject;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// This class defines the GUI frame for creating a new user account.
public class CreateAccountFrame extends JFrame {
    private JButton backArrowButton;
    private JLabel background;

    public CreateAccountFrame() {
        //genral setting 
        setTitle("Create Account");
        setSize(550, 400); // Set the size of the window
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
        backArrowButton.setBorderPainted(false);  // Remove the border of  the button to make it look like an image
        backArrowButton.setContentAreaFilled(false);  // Remove the background fill
        backArrowButton.setBounds(10, 10, 40, 40);  // Set the position and size of the button on the background panel
        background.add(backArrowButton); // Add the backArrowButton to the background panel



        // ==== Main form panel ====
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new GridLayout(6, 2, 10, 10));
        formPanel.setOpaque(false); // Make formPanel transparent

        // Add form components
        JLabel userLabel = new JLabel("Username:");    // Create a label for the username field
        userLabel.setFont(new Font("Serif", Font.BOLD, 20));
        userLabel.setForeground(Color.WHITE); // text color
        JTextField userText = new JTextField();// Create a text field for entering the username
        

        JLabel emailLabel = new JLabel("Email:");// Create a label for the email field
        emailLabel.setFont(new Font("Serif", Font.BOLD, 20));
        emailLabel.setForeground(Color.WHITE); // text color
        JTextField emailText = new JTextField();// Create a text field for entering the email

        JLabel passLabel = new JLabel("Password:");// Create a label for the password field
        passLabel.setFont(new Font("Serif", Font.BOLD, 20));
        passLabel.setForeground(Color.WHITE); // text color
        JPasswordField passText = new JPasswordField();// Create a password field for entering the password

        JLabel confirmPassLabel = new JLabel("Confirm Password:");// Create a label for the confirm password field
        confirmPassLabel.setFont(new Font("Serif", Font.BOLD, 20));
        confirmPassLabel.setForeground(Color.WHITE); // text color
        JPasswordField confirmPassText = new JPasswordField();// Create a password field for confirming the password


        // Create label and set its properties
        JLabel genderLabel = new JLabel("Gender:");
        genderLabel.setFont(new Font("Serif", Font.BOLD, 20));
        genderLabel.setForeground(Color.WHITE);

        // Create male and female radio buttons
        JRadioButton maleButton = new JRadioButton("Male");
        maleButton.setFont(new Font("Serif", Font.BOLD, 16));
        maleButton.setOpaque(false);
        maleButton.setForeground(Color.WHITE);

        JRadioButton femaleButton = new JRadioButton("Female");
        femaleButton.setFont(new Font("Serif", Font.BOLD, 16));
        femaleButton.setOpaque(false);
        femaleButton.setForeground(Color.WHITE);

        // Group the radio buttons together (only one can be selected)
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleButton);
        genderGroup.add(femaleButton);

        // Create panel for the radio buttons and set it up
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        genderPanel.setOpaque(false);
      
       // Add the radio buttons to the panel
        genderPanel.add(maleButton);
        genderPanel.add(femaleButton);

       // Create a new JButton for account creation
        JButton createButton = new JButton("Create Account");
        createButton.setFont(new Font("Serif", Font.BOLD, 16));
        createButton.setForeground(Color.WHITE);
        createButton.setBackground(new Color(1, 1, 20));


        // Add components to the formPanel
        formPanel.add(userLabel);
        formPanel.add(userText);
        formPanel.add(emailLabel);
        formPanel.add(emailText);
        formPanel.add(passLabel);
        formPanel.add(passText);
        formPanel.add(confirmPassLabel);
        formPanel.add(confirmPassText);
        formPanel.add(genderLabel);
        formPanel.add(genderPanel);
        formPanel.add(new JLabel()); // empty
        formPanel.add(createButton);

        // Set the formPanel's position and size
        formPanel.setBounds(50, 70, 400, 250); // Position and size of the formPanel
        background.add(formPanel); // Add formPanel to the background label

        setVisible(true);

        
        // Action for create button
        createButton.addActionListener(new ActionListener() {  // Add an ActionListener to the createButton to handle the click event

            public void actionPerformed(ActionEvent e) {
                // Retrieve and trim the values entered by the user for each field
                String username = userText.getText().trim();
                String email = emailText.getText().trim();
                String password = new String(passText.getPassword());
                String confirmPassword = new String(confirmPassText.getPassword());
             
                // Determine gender based on which radio button is selected
               String gender = maleButton.isSelected() ? "Male" : (femaleButton.isSelected() ? "Female" : "");
               
                // Check if any field is empty and show an error message
                if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || gender.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Please fill in all fields.");
                
                // Check if the email contains '@' to validate it    
                } else if (!email.contains("@")) {
                    JOptionPane.showMessageDialog(null, "Invalid email address.");
               
                // Check if the passwords match   
                } else if (!password.equals(confirmPassword)) {
                    JOptionPane.showMessageDialog(null, "Passwords do not match.");
               
                // If all checks pass, create the account and show a success message
                } else {
                    JOptionPane.showMessageDialog(null, "Account created successfully!\n Welcome " + username);
                    new JHome(); // Open the home frame after account creation
                    dispose(); // Close the current window
                }
            }// end actionPerformed
        });//end createButton action

        // Add an ActionListener to the backArrowButton to handle the click event for the back arrow
        backArrowButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                new JWelcome(); // Open the JWelcome frame when the back arrow is clicked
                dispose();// Close the current window/frame after navigating back
            }//end actionPerformed
        });//end backArrowButton action
    
    }//end constructor
}//end class or feame
