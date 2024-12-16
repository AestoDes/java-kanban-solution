import tasktracker.manager.InMemoryTaskManager;
import tasktracker.manager.TaskManager;
import tasktracker.model.Epic;
import tasktracker.model.Subtask;
import tasktracker.model.Task;
import tasktracker.model.TaskStatus;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = new InMemoryTaskManager();

        Task task = new Task("Task 1", "Description 1", manager.generateId(), TaskStatus.NEW);
        manager.createTask(task);

        Epic epic = new Epic("Epic 1", "Epic Description", manager.generateId(), TaskStatus.NEW);
        manager.createEpic(epic);

        Subtask subtask = new Subtask("Subtask 1", "Description 1", manager.generateId(), TaskStatus.NEW, epic.getId());
        manager.createSubtask(subtask);

        System.out.println("Tasks: " + manager.getAllTasks());
        System.out.println("Epics: " + manager.getAllEpics());
        System.out.println("Subtasks: " + manager.getAllSubtasks());
    }
}
