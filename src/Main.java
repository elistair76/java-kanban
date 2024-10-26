import model.Epic;
import model.Subtask;
import model.Task;
import model.TaskStatus;
import service.TaskManager;

public class Main {

    public static void main(String[] args) {
        System.out.println("Lets go!");
        TaskManager taskManager = new TaskManager();

        Task task1 = new Task("Task_1", "Task_desc_1", TaskStatus.NEW);
        Task task2 = new Task("Task_2", "Task_desc_2", TaskStatus.IN_PROGRESS);
        Task task3 = new Task("Task_3", "Task_desc_3", TaskStatus.DONE);

        taskManager.addTask(task1);
        taskManager.addTask(task2);
        taskManager.addTask(task3);

        Epic epic1 = new Epic("Epic_1", "Epic_desc_1");
        Epic epic2 = new Epic("Epic_2", "Epic_desc_2");

        taskManager.addEpic(epic1);
        taskManager.addEpic(epic2);

        Subtask subtask1 = new Subtask("Subtask_1_1", "Subtask_desc_1_1", TaskStatus.NEW, epic1.getId());
        Subtask subtask2 = new Subtask("Subtask_1_2", "Subtask_desc_1_2", TaskStatus.NEW, epic1.getId());
        Subtask subtask3 = new Subtask("Subtask_2_1", "Subtask_desc_2_1", TaskStatus.DONE, epic2.getId());
        Subtask subtask4 = new Subtask("Subtask_2_2", "Subtask_desc_2_2", TaskStatus.DONE, epic2.getId());

        taskManager.addSubtask(subtask1);
        taskManager.addSubtask(subtask2);
        taskManager.addSubtask(subtask3);
        taskManager.addSubtask(subtask4);
        printAllTasks(taskManager);

        final Task task = taskManager.getTask(task1.getId());
        task.setStatus(TaskStatus.DONE);
        taskManager.updateTask(task1.getId(), task1);
        printAllTasks(taskManager);

        System.out.println("DELETE: " + task1.getName());
        taskManager.deleteTask(task1.getId());
        System.out.println("DELETE: " + epic1.getName());
        taskManager.deleteEpic(epic1.getId());
        printAllTasks(taskManager);

        System.out.println("Delete all tasks:");
        taskManager.deleteAllTasks();
        printAllTasks(taskManager);

    }

    public static void printAllTasks(TaskManager taskManager) {
        System.out.println("Tasks:");
        for (Task t : taskManager.getTasks()) {
            System.out.println(t.toString());
        }

        System.out.println("Epics:");
        for (Epic e : taskManager.getEpics()) {
            System.out.println(e.toString());
            for (Subtask sub : taskManager.getSubtasks(e.getId())) {
                System.out.println("--> " + sub.toString());
            }
        }

        System.out.println("Subtasks:");
        for (Subtask sub : taskManager.getSubtasks()) {
            System.out.println(sub.toString());
        }
    }
}