package tasktracker.manager;

import tasktracker.model.Task;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {

    private static final int HISTORY_LIMIT = 10;

    private final LinkedList<Task> history = new LinkedList<>();

    @Override
    public void add(Task task) {
        if (task == null) {
            return;
        }
        history.removeIf(existingTask -> existingTask.getId() == task.getId());
        history.add(task);
        if (history.size() > HISTORY_LIMIT) {
            history.removeFirst();
        }
    }

    @Override
    public List<Task> getHistory() {
        return Collections.unmodifiableList(history);
    }

    @Override
    public void remove(int id) {
        history.removeIf(task -> task.getId() == id);
    }
}
