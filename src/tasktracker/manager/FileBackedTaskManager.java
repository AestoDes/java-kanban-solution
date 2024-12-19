package tasktracker.manager;

import tasktracker.model.Epic;
import tasktracker.model.Subtask;
import tasktracker.model.Task;
import tasktracker.model.TaskStatus;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class FileBackedTaskManager extends InMemoryTaskManager {

    private final File file;

    public FileBackedTaskManager(File file) {
        this.file = file;
    }

    // Метод сохранения данных в файл
    private void save() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("id,type,name,status,description,epic\n"); // Заголовок CSV

            for (Task task : getAllTasks()) {
                writer.write(toString(task) + "\n");
            }
            for (Epic epic : getAllEpics()) {
                writer.write(toString(epic) + "\n");
            }
            for (Subtask subtask : getAllSubtasks()) {
                writer.write(toString(subtask) + "\n");
            }
        } catch (IOException e) {
            throw new ManagerSaveException("Error saving to file", e);
        }
    }

    // Метод конвертации задачи в строку CSV
    private String toString(Task task) {
        return String.format("%d,%s,%s,%s,%s,%s",
                task.getId(),
                task instanceof Epic ? "EPIC" :
                        task instanceof Subtask ? "SUBTASK" : "TASK",
                task.getTitle(),
                task.getStatus(),
                task.getDescription(),
                task instanceof Subtask ? ((Subtask) task).getEpicId() : "");
    }

    // Метод конвертации строки CSV в задачу
    private Task fromString(String value) {
        String[] fields = value.split(",");
        int id = Integer.parseInt(fields[0]);
        String type = fields[1];
        String title = fields[2];
        TaskStatus status = TaskStatus.valueOf(fields[3]);
        String description = fields[4];

        if ("EPIC".equals(type)) {
            return new Epic(title, description, id, status);
        } else if ("SUBTASK".equals(type)) {
            int epicId = Integer.parseInt(fields[5]);
            return new Subtask(title, description, id, status, epicId);
        } else {
            return new Task(title, description, id, status);
        }
    }

    // Метод загрузки менеджера из файла
    public static FileBackedTaskManager loadFromFile(File file) {
        FileBackedTaskManager manager = new FileBackedTaskManager(file);
        Map<Integer, Subtask> subtasks = new HashMap<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            reader.readLine(); // Пропускаем заголовок
            String line;

            while ((line = reader.readLine()) != null) {
                Task task = manager.fromString(line);
                if (task instanceof Epic) {
                    manager.createEpic((Epic) task);
                } else if (task instanceof Subtask) {
                    subtasks.put(task.getId(), (Subtask) task);
                } else {
                    manager.createTask(task);
                }
            }

            // Восстановление связи подзадач с эпиками
            for (Subtask subtask : subtasks.values()) {
                manager.createSubtask(subtask);
                Epic epic = manager.getEpic(subtask.getEpicId());
                if (epic != null) {
                    epic.addSubtaskId(subtask.getId());
                }
            }

        } catch (IOException e) {
            throw new ManagerSaveException("Error loading from file", e);
        }

        return manager;
    }

    // Переопределение методов для сохранения после каждой модификации
    @Override
    public void createTask(Task task) {
        super.createTask(task);
        save();
    }

    @Override
    public void createEpic(Epic epic) {
        super.createEpic(epic);
        save();
    }

    @Override
    public void createSubtask(Subtask subtask) {
        super.createSubtask(subtask);
        save();
    }

    @Override
    public void updateTask(Task task) {
        super.updateTask(task);
        save();
    }

    @Override
    public void deleteTaskById(int id) {
        super.deleteTaskById(id);
        save();
    }

    @Override
    public void deleteEpicById(int id) {
        super.deleteEpicById(id);
        save();
    }

    @Override
    public void deleteSubtaskById(int id) {
        super.deleteSubtaskById(id);
        save();
    }
}
