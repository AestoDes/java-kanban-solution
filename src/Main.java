import tasktracker.manager.InMemoryTaskManager;
import tasktracker.manager.TaskManager;
import tasktracker.model.Epic;
import tasktracker.model.Subtask;
import tasktracker.model.Task;
import tasktracker.model.TaskStatus;

public class Main {
    public static void main(String[] args) {
        TaskManager manager = new InMemoryTaskManager();

        // Создание задач
        Task task1 = new Task("Задача 1", "Описание задачи 1", manager.generateId(), TaskStatus.NEW);
        Task task2 = new Task("Задача 2", "Описание задачи 2", manager.generateId(), TaskStatus.IN_PROGRESS);
        manager.createTask(task1);
        manager.createTask(task2);

        // Создание эпика и подзадач
        Epic epic1 = new Epic("Эпик 1", "Описание эпика 1", manager.generateId(), TaskStatus.NEW);
        manager.createEpic(epic1);

        Subtask subtask1 = new Subtask("Подзадача 1", "Описание подзадачи 1", manager.generateId(), TaskStatus.NEW, epic1.getId());
        Subtask subtask2 = new Subtask("Подзадача 2", "Описание подзадачи 2", manager.generateId(), TaskStatus.DONE, epic1.getId());
        manager.createSubtask(subtask1);
        manager.createSubtask(subtask2);

        // Печать задач, эпиков и подзадач
        System.out.println("Все задачи: " + manager.getAllTasks());
        System.out.println("Все эпики: " + manager.getAllEpics());
        System.out.println("Подзадачи эпика 1: " + manager.getSubtasksOfEpic(epic1.getId()));

        // Обновление статуса подзадачи
        subtask1.setStatus(TaskStatus.DONE);
        manager.updateSubtask(subtask1);

        // Проверка статуса эпика
        System.out.println("Статус эпика 1 после обновления подзадачи: " + epic1.getStatus());

        // Удаление задачи и эпика
        manager.deleteTaskById(task1.getId());
        manager.deleteEpicById(epic1.getId());

        // Печать после удаления
        System.out.println("Все задачи после удаления: " + manager.getAllTasks());
        System.out.println("Все эпики после удаления: " + manager.getAllEpics());
    }
}
