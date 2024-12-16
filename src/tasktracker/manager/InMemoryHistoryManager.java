package tasktracker.manager;

import tasktracker.model.Task;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {

    private static final int HISTORY_LIMIT = 10; // Лимит истории
    private final LinkedList<Task> history = new LinkedList<>();

    @Override
    public void add(Task task) {
        if (task == null) {
            return; // Игнорируем null-задачи
        }

        // Удаляем задачу из истории, если она уже существует
        history.removeIf(existingTask -> existingTask.getId() == task.getId());

        // Добавляем задачу в конец истории
        history.add(task);

        // Ограничиваем размер истории
        if (history.size() > HISTORY_LIMIT) {
            history.removeFirst();
        }
    }

    @Override
    public List<Task> getHistory() {
        return Collections.unmodifiableList(new ArrayList<>(history));
    }

    @Override
    public void remove(int id) {
        history.removeIf(task -> task.getId() == id);
    }
}
