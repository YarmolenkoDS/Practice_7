/*
 * Classname: LinkedListNode
 *
 * Date: 2018/10/28
 *
 * Author: Dmitrij Yarmolenko
 * E-mail: dsyarmolenko@gmail.com
 *
 */

package ua.edu.sumdu.ta.yarmolenko.pr6;

import ua.edu.sumdu.ta.yarmolenko.pr6.*;

/**
 * Class LinkedListNode describes the LinkedListNode data type
 */ 
public class LinkedListNode {
    LinkedListNode next;
    Task listItemData;

    /**
     * Constructor for creating a node of task list object
     *
     * @param task is an object of type task
     */	
    public LinkedListNode(Task task){
        this.listItemData = task;
        this.next = null;
    }
}