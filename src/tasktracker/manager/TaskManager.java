package tasktracker.manager;



import tasktracker.model.Task;

import tasktracker.model.Epic;

import tasktracker.model.Subtask;

import java.util.List;



public interface TaskManager {

    int generateId();



    void createTask(Task task);

    void createEpic(Epic epic);

    void createSubtask(Subtask subtask);



    Task getTask(int id);

    Epic getEpic(int id);

    Subtask getSubtask(int id);



    void updateTask(Task task);

    void updateSubtask(Subtask subtask);



    List<Task> getAllTasks();

    List<Epic> getAllEpics();

    List<Subtask> getSubtasksOfEpic(int epicId);



    void deleteTaskById(int id);

    void deleteEpicById(int id);



    List<Task> getHistory();

}