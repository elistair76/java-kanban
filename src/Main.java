import manager.InMemoryTaskManager;
import manager.Managers;
import manager.TaskManager;
import status.Status;
import tasks.Epic;
import tasks.Subtask;
import tasks.Task;

public class Main {

    public static void main(String[] args) {
        System.out.println("Поехали!");
        InMemoryTaskManager taskManager = (InMemoryTaskManager) Managers.getDefault();

        Task task1 = new Task("TaskName_1", "TaskDescription_1", Status.NEW);
        Task task2 = new Task("TaskName_2", "TaskDescription_2", Status.IN_PROGRESS);
        Task task3 = new Task("TaskName_3", "TaskDescription_3", Status.DONE);

        taskManager.addTask(task1);
        taskManager.addTask(task2);
        taskManager.addTask(task3);
        taskManager.getTaskById(task1.getId());
        taskManager.getTaskById(task2.getId());
        taskManager.getTaskById(task3.getId());
        taskManager.getTaskById(task3.getId());
        taskManager.getTaskById(task3.getId());

        Epic epic1 = new Epic("EpicName_1", "EpicDescription_1");
        Epic epic2 = new Epic("EpicName_2", "EpicDescription_2");
        Epic epic3 = new Epic("EpicName_3", "EpicDescription_3");
        Epic epic4 = new Epic("EpicName_4", "EpicDescription_4");

        taskManager.addEpic(epic1);
        taskManager.addEpic(epic2);
        taskManager.addEpic(epic3);
        taskManager.addEpic(epic4);
        taskManager.getEpicById(epic1.getId());

        Subtask subtask1 = new Subtask("SubtaskName_1_1", "SubtaskDescription_1_1", Status.NEW, epic1.getId());
        Subtask subtask2 = new Subtask("SubtaskName_1_2", "SubtaskDescription_1_2", Status.NEW, epic1.getId());

        Subtask subtask3 = new Subtask("SubtaskName_2_1", "SubtaskDescription_2_1", Status.DONE, epic2.getId());
        Subtask subtask4 = new Subtask("SubtaskName_2_2", "SubtaskDescription_2_2", Status.DONE, epic2.getId());

        Subtask subtask5 = new Subtask("SubtaskName_3_1", "SubtaskDescription_3_1", Status.NEW, epic3.getId());
        Subtask subtask6 = new Subtask("SubtaskName_3_2", "SubtaskDescription_3_2", Status.DONE, epic3.getId());

        taskManager.addSubtask(subtask1);
        taskManager.addSubtask(subtask2);
        taskManager.addSubtask(subtask3);
        taskManager.addSubtask(subtask4);
        taskManager.addSubtask(subtask5);
        taskManager.addSubtask(subtask6);
        taskManager.getSubtaskById(subtask1.getId());
        taskManager.getSubtaskById(subtask2.getId());
        taskManager.getSubtaskById(subtask3.getId());
        taskManager.getSubtaskById(subtask4.getId());
        taskManager.getSubtaskById(subtask5.getId());
        taskManager.getSubtaskById(subtask6.getId());
        taskManager.getSubtaskById(subtask1.getId());
        taskManager.getSubtaskById(subtask2.getId());
        taskManager.getTaskById(task1.getId());

        printAllTasks(taskManager);

        final Task task = taskManager.getTaskById(task1.getId());
        task.setStatus(Status.DONE);
        taskManager.updateTask(task1);
        printAllTasks(taskManager);

        System.out.println("CHANGE SUBTASK STATUS");
        subtask1.setStatus(Status.DONE);
        taskManager.updateSubtask(subtask1);
        printAllTasks(taskManager);

        System.out.println("DELETE SUBTASK");
        taskManager.deleteSubtaskById(subtask1.getId());
        taskManager.updateEpic(epic1);
        printAllTasks(taskManager);

        System.out.println("DELETE: " + task1.getName());
        taskManager.deleteTask(task1.getId());
        System.out.println("DELETE: " + epic1.getName());
        taskManager.deleteEpic(epic1.getId());
        printAllTasks(taskManager);

        System.out.println("Delete all tasks:");
        taskManager.deleteAllTasks();
        printAllTasks(taskManager);
        printHistory(taskManager);
    }

    public static void printAllTasks(TaskManager taskManager) {
        System.out.println("Задачи:");
        for (Task t : taskManager.getTasks()) {
            System.out.println(t.toString());
        }

        System.out.println("Эпики:");
        for (Epic e : taskManager.getEpics()) {
            System.out.println(e.toString());
            for (Subtask sub : taskManager.getSubtasksByEpicId(e.getId())) {
                System.out.println("--> " + sub.toString());
            }
        }

        System.out.println("Подзадачи:");
        for (Subtask sub : taskManager.getSubtasks()) {
            System.out.println(sub.toString());
        }
    }

    public static void printHistory(TaskManager taskManager) {

        System.out.println("История:");
        for (Task t : taskManager.getHistory()) {
            System.out.println(t);
        }
    }

}