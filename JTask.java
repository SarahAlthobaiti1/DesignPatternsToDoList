/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
// This class defines the Task Manager screen where users can add and delete tasks.
package com.mycompany.designpatternsproject;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.Image;

import javax.swing.DefaultListCellRenderer;
import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextField;

public class JTask extends JFrame implements TaskObserver {
    // Define UI components
    private DefaultListModel<String> taskListModel;
    private JList<String> taskList;
    private JTextField taskField;
    private JTextField dateField;
    private JComboBox<String> priorityComboBox;
    private TaskManager taskManager;
    private SortingStrategy sortingStrategy;
    
    public JTask(TaskManager manager) {
    	
    	this.taskManager = manager;
        taskManager.addObserver(this);// Observe TaskManager
        
        // Frame settings
        setTitle("Task Manager");
        setSize(550, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        // Set background image
        JLabel background = new JLabel(new ImageIcon(getClass().getResource("/images/photo2.jpg")));
        background.setBounds(0, 0, 550, 400);
        background.setLayout(null);
        add(background);

          // ==== Top panel for "<" button ====
        ImageIcon backIcon = new ImageIcon(new ImageIcon(getClass().getResource("/images/back.png"))
                .getImage().getScaledInstance(40, 40, Image.SCALE_SMOOTH));
        JButton backButton = new JButton(backIcon);
        backButton.setBounds(10, 10, 40, 40);// Set the position and size of the button
        backButton.setBorderPainted(false); // Remove the border of the button
        backButton.setContentAreaFilled(false); // Remove the background fill
        background.add(backButton);

        // Go back to Home screen
        backButton.addActionListener(e -> {
            new JHome(); // Open Home screen
            dispose(); // Close the current window
        });
        
        // Task label
        JLabel taskLabel = new JLabel("Task:");
        taskLabel.setBounds(50, 35, 100, 20);
        taskLabel.setForeground(Color.white);
        background.add(taskLabel);

        // Task input field
        taskField = new JTextField();
        taskField.setBounds(50, 60, 200, 30);
        background.add(taskField);

        // Date label
        JLabel dateLabel = new JLabel("Date:");
        dateLabel.setBounds(270, 35, 100, 20);
        dateLabel.setForeground(Color.white);
        background.add(dateLabel);

        // Date input field with placeholder
        dateField = new JTextField("2025-05-12");
        dateField.setBounds(270, 60, 120, 30);
        dateField.setForeground(Color.GRAY);
        background.add(dateField);

        // Placeholder behavior for date field
        dateField.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent e) {
                if (dateField.getText().equals("2025-05-12")) {
                    dateField.setText("");
                    dateField.setForeground(Color.BLACK);
                }
            }

            public void focusLost(java.awt.event.FocusEvent e) {
                if (dateField.getText().isEmpty()) {
                    dateField.setText("2025-05-12");
                    dateField.setForeground(Color.GRAY);
                }
            }
        });

        // Priority label 
        JLabel priorityLabel = new JLabel("Priority:");
        priorityLabel.setBounds(400, 35, 100, 20);
        priorityLabel.setForeground(Color.white);
        background.add(priorityLabel);

        // Priority dropdown (High, Medium, Low)
        priorityComboBox = new JComboBox<>(new String[]{"High", "Medium", "Low"});
        priorityComboBox.setBounds(400, 60, 100, 30);
        background.add(priorityComboBox);

        // Add Task button (
        JButton addButton = new JButton("Add Task");
        addButton.setBounds(215 , 100, 120, 30); 
        background.add(addButton);

        // Task list with scroll
        taskListModel = new DefaultListModel<>();
        taskList = new JList<>(taskListModel);
        taskList.setCellRenderer(new TaskRenderer()); // Custom color renderer
        JScrollPane scrollPane = new JScrollPane(taskList);
        scrollPane.setBounds(50, 150, 400, 150);
        background.add(scrollPane);

        // Delete button
        JButton deleteButton = new JButton("Delete Selected");
        deleteButton.setBounds(200, 320, 150, 30);
        background.add(deleteButton);
        
        // Sort Button
        JButton sortButton = new JButton("Sort by Priority");
        sortButton.setBounds(370, 320, 150, 30);
        background.add(sortButton);

        // Action to add task
        addButton.addActionListener(e -> {
            String taskText = taskField.getText().trim();
            String dateText = dateField.getText().trim();
            String priority = (String) priorityComboBox.getSelectedItem();

            // Check for empty fields
            if (taskText.isEmpty() || dateText.isEmpty() || dateText.equals("2025-05-12")) {
                JOptionPane.showMessageDialog(this, "Please enter the task and date.");
                return;
            }

            // New task string using factory
            String task = TaskFactory.createTask(taskText, dateText, priority);
            taskManager.addTask(task);

            // Reset fields
            taskField.setText("");
            dateField.setText("2025-05-12");
            dateField.setForeground(Color.GRAY);
            priorityComboBox.setSelectedIndex(0);
        });

        // Action to delete selected task
        deleteButton.addActionListener(e -> {
            int selectedIndex = taskList.getSelectedIndex();
            if (selectedIndex != -1) {
            	taskManager.removeTask(selectedIndex);
            }
        });
        // Action to Sort task based on priority
        sortButton.addActionListener(e -> {
            sortingStrategy = new SortByPriority(); // Set desired strategy
            sortingStrategy.sort(taskListModel);    // Apply strategy
        });

        // Show the frame
        setVisible(true);
        update();
    }
    
    /**
     * Updates the task list in the GUI whenever the TaskManager notifies a change.
     */
    @Override
    public void update() {
    	// Clear the current list displayed in the GUI
        taskListModel.clear();
        // Get the updated list of tasks from the TaskManager and repopulate the list model
        for (String task : taskManager.getTasks()) {
            taskListModel.addElement(task); // Add each task to the GUI list
        }
    }

    // Renderer to color items based on priority
    private class TaskRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value, int index,
                                                      boolean isSelected, boolean cellHasFocus) {
            JLabel label = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);

            String task = value.toString();

            // Color based on priority
            if (task.contains("Priority: High")) {
                label.setForeground(Color.RED);
            } else if (task.contains("Priority: Medium")) {
                label.setForeground(Color.ORANGE);
            } else if (task.contains("Priority: Low")) {
                label.setForeground(new Color(0, 150, 0));
            }

            if (isSelected) {
                label.setBackground(new Color(220, 220, 255));
            } else {
                label.setBackground(Color.WHITE);
            }

            label.setFont(label.getFont().deriveFont(Font.PLAIN));
            return label;
        }
    }
}