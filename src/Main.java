import tasktracker.manager.InMemoryTaskManager;
import tasktracker.manager.TaskManager;
import tasktracker.model.Epic;
import tasktracker.model.Subtask;
import tasktracker.model.Task;
import tasktracker.model.TaskStatus;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = new InMemoryTaskManager();

        Task task1 = new Task("Task 1", "Description 1", manager.generateId(), TaskStatus.NEW);
        manager.createTask(task1);

        Epic epic1 = new Epic("Epic 1", "Epic Description", manager.generateId(), TaskStatus.NEW);
        manager.createEpic(epic1);

        Subtask subtask1 = new Subtask("Subtask 1", "Description 1", manager.generateId(), TaskStatus.NEW, epic1.getId());
        manager.createSubtask(subtask1);

        System.out.println("Tasks: " + manager.getAllTasks());
        System.out.println("Epics: " + manager.getAllEpics());
        System.out.println("Subtasks of Epic: " + manager.getSubtasksOfEpic(epic1.getId()));
    }
}
