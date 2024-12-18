import org.junit.jupiter.api.Test;
import tasktracker.manager.FileBackedTaskManager;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class FileBackedTaskManagerTest {

    @Test
    public void shouldLoadFromEmptyFile() throws IOException {
        // Создаём временный пустой файл
        File file = File.createTempFile("empty_tasks", ".csv");
        file.deleteOnExit(); // Удаляется после завершения теста

        // Загружаем менеджер из пустого файла
        FileBackedTaskManager loadedManager = FileBackedTaskManager.loadFromFile(file);

        // Проверяем, что данные пустые
        assertTrue(loadedManager.getAllTasks().isEmpty(), "Tasks list should be empty");
        assertTrue(loadedManager.getAllEpics().isEmpty(), "Epics list should be empty");
        assertTrue(loadedManager.getAllSubtasks().isEmpty(), "Subtasks list should be empty");
    }
}
