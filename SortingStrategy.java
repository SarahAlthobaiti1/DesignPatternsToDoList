package com.mycompany.designpatternsproject;

import javax.swing.DefaultListModel;
/**
 * Strategy interface for defining different sorting algorithms
 * that can be applied to a list of tasks.
 */
public interface SortingStrategy {
	
	void sort(DefaultListModel<String> taskListModel);
}
