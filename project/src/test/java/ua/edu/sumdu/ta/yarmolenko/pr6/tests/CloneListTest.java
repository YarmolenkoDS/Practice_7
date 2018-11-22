package ua.edu.sumdu.ta.yarmolenko.pr6.tests;

import ua.edu.sumdu.ta.yarmolenko.pr6.*;

import java.util.*;

import org.junit.*;
import static org.junit.Assert.*;

public class CloneListTest extends AbstractTaskListTest {

    public CloneListTest(Class<? extends AbstractTaskList> tasksClass) {
        super(tasksClass);
    }
    
    @Test
    public void testClone() {
        Task[] elements = { task("A"), task("B"), task("C") };
        addAll(elements);
        AbstractTaskList clone = (AbstractTaskList) tasks.clone();
        
        assertEquals(getTitle(), tasks.size(), clone.size());
        AbstractTaskList ti = tasks;
        AbstractTaskList ci = clone;

        for(int i=0; i< ti.size(); i++){
            assertEquals(getTitle(), ti.clone(), ci.clone());
        }
        assertNotSame(getTitle(), tasks, clone);
        
        clone.add(task("D"));
        assertEquals(getTitle(), clone.size() - 1, tasks.size());
        
        clone.remove(task("D"));
        assertContains(elements);
    }
}
