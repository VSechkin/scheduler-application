

import com.nc.edu.ta.Vadim.pr3.*;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;
import java.util.*;
import java.lang.reflect.Method;

import static org.junit.jupiter.api.Assertions.*;

abstract class TaskListTest {

    protected AbstractTaskList tasks;

    private static void assertEqualTasks(Task[] tasks, AbstractTaskList  list) {
        Task[] actual = new Task[list.size()];
        for (int i = 0; i < list.size(); i++) {
            actual[i] = list.getTask(i);
        }
        assertArrayEquals(tasks, actual);
    }

    private static void assertContains(Task[] expected, Task[] actual) {
        List<Task> expectedList = new ArrayList<>(Arrays.asList(expected));
        for (Task task : actual)
            if (expectedList.contains(task)) {
                expectedList.remove(task);
            } else {
                fail("Expected elements: " + Arrays.toString(expected) +
                        ", actual elements: " + Arrays.toString(actual));
            }
        if (! expectedList.isEmpty()) {
            fail("Expected elements: " + Arrays.toString(expected) +
                    ", actual elements: " + Arrays.toString(actual));
        }
    }

    @Test
    public void testAdd() {
        assertEquals(0, tasks.size());
        Task[] ts = {new Task("a",0), new Task("b",1), new Task("c",2)};
        for (Task t : ts) {
            tasks.add(t);
        }
        assertEqualTasks(ts, tasks);
    }
    @Test
    public void testRemove() {
        Task[] ts = {new Task("a",0), new Task("b",1), new Task("c",2)};
        tasks.add(ts[0]);
        Task t = new Task("some",0);
        tasks.add(t);
        tasks.add(ts[1]);
        tasks.add(ts[2]);
        tasks.remove(t);
        assertEqualTasks(ts, tasks);
    }

    @Test
    public void testRemoveLast() {
        Task[] ts = {new Task("a",0), new Task("b",1), new Task("c",2)};
        tasks.add(ts[0]);
        Task t = new Task("some",0);
        tasks.add(ts[1]);
        tasks.add(ts[2]);
        tasks.add(t);
        tasks.remove(t);
        assertEqualTasks(ts, tasks);
    }

    @Test
    public void testRemoveFirst() {
        Task[] ts = {new Task("a",0), new Task("b",1), new Task("c",2)};
        Task t = new Task("some",0);
        tasks.add(t);
        tasks.add(ts[0]);
        tasks.add(ts[1]);
        tasks.add(ts[2]);
        tasks.remove(t);
        assertEqualTasks(ts, tasks);
    }

    @Test
    public void testRemoveDouble() {
        Task[] ts = {new Task("a",0), new Task("b",1), new Task("c",2)};
        tasks.add(ts[0]);
        Task t = new Task("some",0);
        tasks.add(t);
        tasks.add(t);
        tasks.add(ts[1]);
        tasks.add(t);
        tasks.add(ts[2]);
        tasks.remove(t);
        assertEqualTasks(ts, tasks);
    }
    @Test
    public void testAddMoreTasks() {
        Task[] ts = new Task[100];
        for (int i = 0; i < 100; i++) {
            ts[i] = new Task("Task#"+ i, i);
            tasks.add(ts[i]);
        }
        assertEqualTasks(ts, tasks);
    }
    /**Additional method*/
    @Test
    public void testIncomingInactive() throws InvocationTargetException, IllegalAccessException {
        Method lMethod = null;
        try {
            lMethod = tasks.getClass().getMethod("incoming", int.class, int.class);
        } catch (NoSuchMethodException ex) {
            fail("TaskList has no incoming method ( additional task)");
        }

        Task[] ts = {new Task("a",0), new Task("b",1), new Task("c",2)};
        for (Task t : ts) {
            t.setActive(false);
            tasks.add(t);
        }
        assertArrayEquals(new Task[0], (Task[])lMethod.invoke(tasks,0, 1000));
    }

    @Test
    public void testAddNull() {
       assertEquals(0, tasks.size());
       try {
        tasks.add(null);
       } catch (IllegalArgumentException ex) {
        //correct for practice #4
       }
       assertEquals(0, tasks.size());
    }

    @Test
    public void testUpdateTitle() {
        assertEquals(0, tasks.size());
        String title = "[EDUCTR][TA] task";
        Task task = new Task(title, 0);
        tasks.add(task);
        assertEquals(title, tasks.getTask(0).getTitle());
    }

    @Test
    public void testRemoveNull() {
        ArrayTaskList tasks = new ArrayTaskList();
        Task[] ts = {new Task("a", 0), new Task("b", 1)};
        for (Task t : ts) {
            tasks.add(t);
        }
        try {
            tasks.remove(null);
        } catch (IllegalArgumentException ex) {
            //correct for practice #4
        }
        assertEqualTasks(ts, tasks);
    }

    /**Additional method*/
    @Test
    public void testIncoming() throws InvocationTargetException, IllegalAccessException {
        Method lMethod = null;
        try {
            lMethod = tasks.getClass().getMethod("incoming", int.class, int.class);
        } catch (NoSuchMethodException ex) {
            fail("TaskList has no incoming method ( additional task)");
        }

        Task[] incoming = {new Task("b", 20), new Task("c", 30), new Task("e", 0, 100, 5)};
        tasks.add(new Task("a", 10));
        tasks.add(incoming[0]);
        tasks.add(incoming[1]);
        tasks.add(new Task("d", 40));
        tasks.add(incoming[2]);
        for (int i = 0; i < tasks.size(); i++)
            tasks.getTask(i).setActive(true);
        assertContains(incoming, (Task[])lMethod.invoke(tasks,11, 36));
    }
}
