package com.mycompany.designpatternsproject;
/**
 * Factory class for creating task strings in a consistent format.
 * This follows the Factory design pattern to encapsulate task creation logic.
 */
public class TaskFactory {
	 public static String createTask(String description, String date, String priority) {
	        return description + " | " + date + " | Priority: " + priority;
	    }
}
