package com.mycompany.designpatternsproject;

import java.util.ArrayList;
import java.util.List;

/**
 * Manages tasks and notifies observers when tasks are added or removed.
 * Implements the core of the Observer pattern.
 */

public class TaskManager {
	// List to store task strings
    private List<String> tasks = new ArrayList<>();
    
    // List of registered observers
    private List<TaskObserver> observers = new ArrayList<>();
 
    //Adds an observer that will be notified of task list changes.
    public void addObserver(TaskObserver observer) {
        observers.add(observer);
    }

    //Removes an observer from the notification list.
    public void removeObserver(TaskObserver observer) {
        observers.remove(observer);
    }

    //Notifies all registered observers that the task list has changed.
    private void notifyObservers() {
        for (TaskObserver observer : observers) {
            observer.update();
        }
    }

    //Adds a task to the list and notifies observers.
    public void addTask(String task) {
        tasks.add(task);
        notifyObservers();// Notify GUI or other listeners
    }

    //Removes a task at a specific index and notifies observers.
    public void removeTask(int task) {
        tasks.remove(task);
        notifyObservers(); // Notify GUI or other listeners
    }

    //Returns the current list of tasks.
    public List<String> getTasks() {
        return tasks;
    }
}

