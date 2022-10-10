import com.nc.edu.ta.Vadim.pr3.*;

import org.junit.jupiter.api.BeforeEach;

public class LinkedTaskListTest extends TaskListTest {

    @BeforeEach
    public void before()
    {
        tasks = new LinkedTaskList();
    }
}
