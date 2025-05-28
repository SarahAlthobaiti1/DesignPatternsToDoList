package com.mycompany.designpatternsproject;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.swing.DefaultListModel;

/**
 * Concrete Strategy class that sorts tasks by their priority
 * using the Strategy design pattern.
 */ 

public class SortByPriority implements SortingStrategy {
	
	 /**
     * Sorts the given DefaultListModel of task strings by priority in the order:
     * High -> Medium -> Low.
     *
     * 
     */
	
    @Override
    public void sort(DefaultListModel<String> taskListModel) {
    	
    	// Convert DefaultListModel to a standard List for sorting
        List<String> tasks = Collections.list(taskListModel.elements());
        
        // Sort the tasks based on their priority level
        tasks.sort(Comparator.comparingInt(task -> {
        	
        	// Extract the priority part of the task string
            String priority = task.split(" \\| ")[2].trim();
            
            // Assign numeric values to priorities for sorting
            switch (priority) {
                case "Priority: High": return 1;
                case "Priority: Medium": return 2;
                case "Priority: Low": return 3;
                default: return 4;
            }
        })); // Sorts by priority
        taskListModel.clear();
        for (String task : tasks) {
            taskListModel.addElement(task);
        }
    }
}

