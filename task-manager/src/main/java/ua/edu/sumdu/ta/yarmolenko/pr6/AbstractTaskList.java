/*
 * Classname: AbstractTaskList
 *
 * Date: 2018/10/22
 *
 * Author: Dmitrij Yarmolenko
 * E-mail: dsyarmolenko@gmail.com
 *
 */

package ua.edu.sumdu.ta.yarmolenko.pr6;

import ua.edu.sumdu.ta.yarmolenko.pr6.*;

import java.util.*;

/**
 * Abstract class AbstractTaskList describes the AbstractTaskList data type
 */
public abstract class AbstractTaskList implements Iterable<Task>, Cloneable/*, Serializable*/ {
    
    public final String START_OF_TASK_TITLE = "[EDUCTR][TA]";

    protected int counterOfTasksInList;

    private int numberOfListsCreated = 0;
 
    /**
     * Constructor of the AbstractTaskList class
     */
    public AbstractTaskList() {
        numberOfListsCreated++;
        this.counterOfTasksInList = 0;
    }

    /**
     * Method to creating the object copy of type AbstractTaskList
     *
     * @return the object copy of type AbstractTaskList
     */
    public AbstractTaskList clone() {
        try {
            return (AbstractTaskList) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError(); // Can't happen
        }
    }
    
    /**
     * Method to generate an integer object code for objects of type AbstractTaskList
     *
     * @return an integer object code for objects of type AbstractTaskList
     */
    public int hashCode() {
        final int prime = 31;
        int result = size();
        Iterator<Task> thisIterator = iterator();
        while (thisIterator.hasNext()) {
            Task thisElement = thisIterator.next();
            result = prime * result + 10;
        }
        return result;
    }

    /**
     * Method to comparing objects of type AbstractTaskList
     *
     * @return boolean value containing the result of comparing objects of type AbstractTaskList
     */	
    public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (obj == null || obj.getClass() != this.getClass()) {
            return false;
        }
        AbstractTaskList list = (AbstractTaskList) obj;
        if (list.size() != this.size()) {
            return false;
        }
        Iterator<Task> listIterator = list.iterator();
        Iterator<Task> thisIterator = iterator();
        while (thisIterator.hasNext()) {
            Task listElement = listIterator.next();
            Task thisElement = thisIterator.next();
            if (!thisElement.equals(listElement)) {
                return false;
            }
        }
		return true;
    }    
    
    /**
     * Used to iterate through the task list
     */
    public abstract Iterator<Task> iterator();

    /**
     * Method to get the number of created task lists
     *
     * @return the number of created task lists
     */	
    public int getNumberOfListsCreated() {
        return numberOfListsCreated;
    }

    /**
     * Method for adding non-unique tasks
     *
     * @param task is an object of type task added to task list
     */    
    public abstract void add(Task task);

    /**
     * Method to delete all tasks equal input
     *
     * @param task is an object of type task to be deleted in the task list
     */	    
    public abstract void remove(Task task);

    /**
     * Method to get the number of tasks in the current list
     *
     * @return the number of tasks in the current list
     */
    public int size() {
        return counterOfTasksInList;
    }

    /**
     * Method that returns a description of this current list
     *
     * @return string with a description of the current list
     */
    public String toString() {
        Iterator<Task> thisIterator = iterator();
        String result = "";
        while (thisIterator.hasNext()) {
            Task thisElement = thisIterator.next();
            result = result + thisElement.getTitle();
            if (thisIterator.hasNext()) {
                result = result +  ", ";
            }
        }
        return this.getClass().getSimpleName() + " [" + result + "]";
    }    
}