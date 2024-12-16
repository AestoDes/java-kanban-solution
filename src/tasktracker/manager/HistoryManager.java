package tasktracker.manager;

import tasktracker.model.Task;

import java.util.List;

public interface HistoryManager {
    void add(Task task);

    List<Task> getHistory();

    void remove(int id); // Новый метод для удаления задачи по ID
}
