import com.nc.edu.ta.Vadim.pr3.DoubleLinkedTaskList;
import org.junit.jupiter.api.BeforeEach;

public class DoubleLinkedList extends TaskListTest {

    @BeforeEach
    public void before()
    {
        tasks = new DoubleLinkedTaskList();
    }
}
