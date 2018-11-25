/*
 * Classname: LinkedTaskList
 *
 * Date: 2018/10/28
 *
 * Author: Dmitrij Yarmolenko
 * E-mail: dsyarmolenko@gmail.com
 *
 */

package ua.edu.sumdu.ta.yarmolenko.pr6;

import ua.edu.sumdu.ta.yarmolenko.pr6.*;

import java.util.*;


/**
 * Class LinkedTaskList describes the LinkedTaskList data type
 */
public class LinkedTaskList extends AbstractTaskList {

    private LinkedListNode firstElementOfList;

    /**
     * Constructor for creating a task list object
     */
    public LinkedTaskList() {
        super();
        this.firstElementOfList = new LinkedListNode(null);
    }

    /**
     * Used to iterate through the task list
     */
    public Iterator<Task> iterator() {
        Iterator<Task> it = new Iterator<Task>() {
            private LinkedListNode currentElementOfList = firstElementOfList;

            /*
             * Returns true if the iteration has more elements
             */
            public boolean hasNext() {
                return currentElementOfList != null;
            }
            
            /*
             * Returns the next element in the iteration
             */
             public Task next() {
                if (hasNext()) {
                    Task data = currentElementOfList.listItemData;
                    currentElementOfList = currentElementOfList.next;
                    return data;
                }
                return null;
            }
        };
        return it;
    }

    /**
     * Method for adding non-unique tasks
     *
     * @param task is an object of type task added to task list
     */	
    public void add(Task task) throws NullPointerException {
        if (task != null) {
            task.setTitle(START_OF_TASK_TITLE + task.getTitle());
            LinkedListNode lastElementOfList = new LinkedListNode(task);
            if (firstElementOfList.listItemData == null) {
                firstElementOfList = lastElementOfList;
                counterOfTasksInList++;
            } else {
                LinkedListNode currentElementOfList = firstElementOfList;
                while (currentElementOfList.next != null){
                    currentElementOfList = currentElementOfList.next;
                }
                currentElementOfList.next = lastElementOfList;
                counterOfTasksInList++; 
            }
        } else {
            throw new NullPointerException();
        }
    }

    /**
     * Method to delete all tasks equal input
     *
     * @param task is an object of type task to be deleted in the task list
     */	
    public void remove(Task task) throws NullPointerException {
        if (task == null) {
            throw new NullPointerException();
        }
        if (firstElementOfList.listItemData == null){
            throw new NullPointerException();
        }
        task.setTitle(START_OF_TASK_TITLE + task.getTitle());
        while (firstElementOfList.listItemData.equals(task)) {
            if (firstElementOfList.next != null) {
                firstElementOfList = firstElementOfList.next;
                counterOfTasksInList--;
            } else {
                firstElementOfList.listItemData = null;
                counterOfTasksInList--;
                return;
            }
        }
        LinkedListNode prevElementOfList = firstElementOfList;
        LinkedListNode currentElementOfList = firstElementOfList;
        while (prevElementOfList != null) {
            currentElementOfList = prevElementOfList.next;
            while (currentElementOfList != null && currentElementOfList.listItemData.equals(task)){
                counterOfTasksInList--;
                currentElementOfList = currentElementOfList.next;
            }
            prevElementOfList.next = currentElementOfList;
            prevElementOfList = currentElementOfList;
        }
    }

    /**
     * The method returns an array of tasks from the list whose notification time 
     *  is between from (exclusively) and to (inclusive)
     *
     * @param from the beginning of the time span
     * @param to the ending of the time span
     * @return an array of tasks from the list whose notification time 
     *  is between from (exclusively) and to (inclusive)
     */	
    public Task[] incoming(int from, int to) {
        Task[] taskListFromTo = new Task[size()];
        int indexFromTo = 0;
        LinkedListNode currentElementOfList = firstElementOfList;
        while (currentElementOfList != null){
            if ((currentElementOfList.listItemData.isActive() == true) 
                    && (currentElementOfList.listItemData.nextTimeAfter(from) <= to)
                    && (currentElementOfList.listItemData.nextTimeAfter(from) != -1)) {
                taskListFromTo[indexFromTo] = currentElementOfList.listItemData;
                indexFromTo ++;
            }
            currentElementOfList = currentElementOfList.next;
        }
        Task[] tempTaskListFromTo = new Task[indexFromTo];
        for (int i = 0; i < indexFromTo; i++) {
            tempTaskListFromTo[i] = taskListFromTo[i];
        }
        taskListFromTo = tempTaskListFromTo;
        return taskListFromTo;
    }
}