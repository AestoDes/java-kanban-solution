package tasktracker.manager;



import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;

import tasktracker.model.Task;

import tasktracker.model.TaskStatus;



import static org.junit.jupiter.api.Assertions.*;



public class InMemoryTaskManagerTest {



    private TaskManager taskManager;



    @BeforeEach

    public void setUp() {

        taskManager = new InMemoryTaskManager();

    }



    @Test

    public void addTaskTest() {

        Task task = new Task("Test Task", "Test Description", taskManager.generateId(), TaskStatus.NEW);

        taskManager.createTask(task);



        assertEquals(1, taskManager.getAllTasks().size());

        assertEquals(task, taskManager.getAllTasks().getFirst());

    }



    @Test

    public void getHistoryTest() {

        Task task = new Task("Test Task", "Test Description", taskManager.generateId(), TaskStatus.NEW);

        taskManager.createTask(task);

        taskManager.getTask(task.getId());



        assertEquals(1, taskManager.getHistory().size());

        assertEquals(task, taskManager.getHistory().getFirst());

    }

}