
package com.mycompany.designpatternsproject;

// Import the important packages for creating the GUI components
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// JWelcome is the main welcome screen (JFrame) that greets users when the application starts.
public class JWelcome extends JFrame {
    private JLabel welcomeLabel;
    private JButton createButton;
    private JButton loginButton;
    private JLabel background ;

    public JWelcome() {
        
        // General settings for window properties
        setTitle("Welcome"); // Set the title and size of the frame
        setSize(550, 400); // Sets the size of the window to 500 pixels wide and 400 pixels high
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);  // Ensures the application exits completely when the window is closed
        setLocationRelativeTo(null); // Centers the window on the screen
        setLayout(new BorderLayout()); // Sets the layout manager to BorderLayout for arranging components

        // Load and display background image over which components will be placed
        background = new JLabel(new ImageIcon(getClass().getResource("/images/photo2.jpg"))); // Loads an image from the /images folder and sets it as an icon in a JLabel to use as the background
        background.setLayout(new GridBagLayout()); // Sets the layout manager of the background label to GridBagLayout, which allows flexible placement and easy centering of components over the background image
        add(background); // Adds the background label (with image and layout) to the frame

        // Welcome text in center with image in background
        welcomeLabel = new JLabel("<html><div style='text-align: center;'>"
        + "Welcome<br>To-Do List Will Help You"
        + "</div></html>");      
        welcomeLabel.setFont(new Font("Serif", Font.BOLD, 28));
        welcomeLabel.setForeground(Color.WHITE); // text color
        background.add(welcomeLabel); // add the welcomeLabel to background 

        
        // Timer that waits 3 seconds before showing the buttons (Create Account and Log In)
        Timer timer = new Timer(3000, new ActionListener() {
           
            @Override
            public void actionPerformed(ActionEvent e) {
                // Remove welcome label
                background.remove(welcomeLabel);

                // Create buttons with resized Icon
                ImageIcon resizedIcon1 = new ImageIcon(new ImageIcon(getClass().getResource("/images/createAccount.png"))
                        .getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
                createButton = new JButton("Create New Account", resizedIcon1);
                createButton.setIconTextGap(10);

                ImageIcon resizedIcon2 = new ImageIcon(new ImageIcon(getClass().getResource("/images/Account.png"))
                        .getImage().getScaledInstance(30, 30, Image.SCALE_SMOOTH));
                loginButton = new JButton("  Log In", resizedIcon2);
                loginButton.setIconTextGap(10);
                loginButton.setHorizontalAlignment(SwingConstants.LEFT); // Align all to the left


               // customize createButton
                createButton.setBackground(new Color(245, 245, 220)); // Beige
                createButton.setForeground(new Color(0, 0, 139)); // Dark blue
                createButton.setPreferredSize(new Dimension(220, 50));
                createButton.setFont(new Font("Serif", Font.BOLD, 16));
                

                // customize loginButton
                loginButton.setBackground(new Color(245, 245, 220)); // Beige
                loginButton.setForeground(new Color(0, 0, 139)); // Dark blue
                loginButton.setPreferredSize(new Dimension(200, 50));
                loginButton.setFont(new Font("Serif", Font.BOLD, 16));

                
                // Set simple action listeners to open other frames
                
                // Action listener to open the Create Account frame when the button is clicked
                createButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new CreateAccountFrame(); // open CreateAccountFrame
                        dispose(); // close current frame
                    }
                });
                

                // Action listener to open the Log In frame when the button is clicked
                loginButton.addActionListener(new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent e) {
                        new LoginFrame(); // open LoginFrame
                        dispose(); // close current frame
                    }
                });

                // Create panel to hold buttons
                JPanel buttonPanel = new JPanel();
                buttonPanel.setOpaque(false); // transparent to show background image
                buttonPanel.setLayout(new GridLayout(2, 1, 30, 30)); // vertical layout to make button top of each other
               
                //add buttouns to buttonPanel
                buttonPanel.add(createButton);
                buttonPanel.add(loginButton);
                

                background.add(buttonPanel); 
                background.revalidate(); // refresh layout
                background.repaint();
            }
        });//ends 

        timer.setRepeats(false); // only run once
        timer.start(); // start the timer

        setVisible(true); // show the frame
    }//end construcor

}//end class
