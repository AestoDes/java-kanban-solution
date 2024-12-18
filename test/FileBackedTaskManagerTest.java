import org.junit.jupiter.api.Test;
import tasktracker.manager.FileBackedTaskManager;

import java.io.File;

import static org.junit.jupiter.api.Assertions.*;

public class FileBackedTaskManagerTest {

    @Test
    public void shouldLoadFromEmptyFile() {
        File file = new File("empty_tasks.csv");
        FileBackedTaskManager loadedManager = FileBackedTaskManager.loadFromFile(file);

        assertTrue(loadedManager.getAllTasks().isEmpty(), "Tasks list should be empty");
        assertTrue(loadedManager.getAllEpics().isEmpty(), "Epics list should be empty");
        assertTrue(loadedManager.getAllSubtasks().isEmpty(), "Subtasks list should be empty");
    }
}
