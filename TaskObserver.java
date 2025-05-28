package com.mycompany.designpatternsproject;

/**
 * Observer interface for listening to task list changes.
 * 
 * Classes that implement this interface can register themselves with
 * the TaskManager to get notified whenever the task list is updated.
 */

public interface TaskObserver {
	 
     // Called when the task list changes (task added or removed).
     void update();
}
