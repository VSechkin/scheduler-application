

import com.nc.edu.ta.Vadim.pr3.Task;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class TaskTest {

    @BeforeAll
    public static void testDescription() {
        System.out.println("--- description test ---");
        Task task = new Task("Inactive repeated", 10, 100, 5);
        System.out.println(task);
        task = new Task("Active one-time", 50);
        task.setActive(true);
        System.out.println(task);
        System.out.println("------------------------");
    }

    @Test
    public void testTitle() {
        Task task = new Task("test", 0);
        assertEquals("test", task.getTitle());
        task.setTitle("other");
        assertEquals("other", task.getTitle());
    }
    @Test
    public void testActive() {
        Task task = new Task("test", 0);
        assertFalse(task.isActive());
        task.setActive(true);
        assertTrue(task.isActive());
    }
    @Test
    public void testConstructorNonrepeated() {
        Task task = new Task("test", 10);
        assertFalse( task.isActive(), "active");
        assertEquals( 10, task.getTime(),"time");
        assertEquals( 10, task.getStartTime(),"start");
        assertEquals( 10, task.getEndTime(), "end");
        assertFalse(task.isRepeated(),"repeated");
		assertEquals(0, task.getRepeatInterval(), "repeatInterval");
    }

    @Test
    public void testConstructorRepeated() {
        Task task = new Task("test", 10, 100, 5);
        assertFalse(task.isActive(), "active");
        assertEquals(10, task.getTime(), "time");
        assertEquals(10, task.getStartTime(), "start");
        assertEquals( 100, task.getEndTime(), "end");
        assertTrue(task.isRepeated(), "repeated");
        assertEquals( 5, task.getRepeatInterval(), "repeatInterval");
    }

    @Test
    public void  testTimeNonRepeated() {
        Task task = new Task("test", 0, 100, 15);
        task.setTime(50);
        assertEquals(50, task.getTime(), "time");
        assertEquals( 50, task.getStartTime(), "start");
        assertEquals( 50, task.getEndTime(), "end");
        assertFalse( task.isRepeated(), "repeated");
		assertEquals( 0, task.getRepeatInterval(), "repeatInterval");
    }

    @Test
    public void testTimeRepeated() {
        Task task = new Task("test", 10);
        task.setTime(5, 20, 1);
        assertEquals( 5, task.getTime(), "time");
        assertEquals( 5, task.getStartTime(), "start");
        assertEquals( 20, task.getEndTime(), "end");
        assertTrue( task.isRepeated(), "repeated");
        assertEquals( 1, task.getRepeatInterval(), "repeatInterval");
    }

    @Test
    public void testNextNonRepeated() {
        Task task = new Task("some", 10);
        task.setActive(true);
        assertEquals(10, task.nextTimeAfter(0));
        assertEquals(10, task.nextTimeAfter(9));
        assertEquals(-1, task.nextTimeAfter(10));
        assertEquals(-1, task.nextTimeAfter(100));
    }
	
    @Test
    public void testNextRepeative() {
        Task task = new Task("some", 10, 100, 20);
        task.setActive(true);
        assertEquals(10, task.nextTimeAfter(0));
        assertEquals(10, task.nextTimeAfter(9));
        assertEquals(30, task.nextTimeAfter(10));
        assertEquals(50, task.nextTimeAfter(30));
        assertEquals(50, task.nextTimeAfter(40));
        assertEquals(-1, task.nextTimeAfter(95));
        assertEquals(-1, task.nextTimeAfter(100));
    }
	
    @Test
    public void testNextInactive() {
        Task task = new Task("some", 10);
        task.setActive(false);
        assertEquals(-1, task.nextTimeAfter(0));
    }
	
	@Test
    public void testToStringNonRepeated() {
        Task task = new Task("some", 10);
        task.setActive(false);
        assertEquals("Task \"some\" is inactive", task.toString());
		task.setActive(true);
        assertEquals("Task \"some\" at 10", task.toString());
    }
	
    @Test
    public void testToStringRepeated() {
        Task task = new Task("some", 10, 100, 20);
        task.setActive(false);
        assertEquals("Task \"some\" is inactive", task.toString());
		task.setActive(true);
        assertEquals("Task \"some\" from 10 to 100 every 20 seconds", task.toString());
    }
	
}