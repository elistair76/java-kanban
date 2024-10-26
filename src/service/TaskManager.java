package service;

import model.Epic;
import model.Subtask;
import model.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TaskManager {
    private HashMap<Integer, Task> tasks;
    private HashMap<Integer, Epic> epics;
    private HashMap<Integer, Subtask> subtasks;
    private int nextId;

    public TaskManager() {
        tasks = new HashMap<>();
        epics = new HashMap<>();
        subtasks = new HashMap<>();
        nextId = 1;
    }

    public List<Task> getTasks() {
        return new ArrayList<>(tasks.values());
    }

    public List<Epic> getEpics() {
        return new ArrayList<>(epics.values());
    }

    public List<Subtask> getSubtasks() {
        return new ArrayList<>(subtasks.values());
    }

    public void deleteAllTasks() {
        tasks.clear();
    }

    public void deleteAllEpics() {
        subtasks.clear();
        epics.clear();
    }

    public void deleteAllSubtasks() {
        for (Epic epic : epics.values()) {
            epic.getSubtasksInEpic().clear();
            epic.updateStatus(this);

        }
        subtasks.clear();
    }

    public Task getTask(int id) {
        return tasks.get(id);
    }

    public Task getEpic(int id) {
        return epics.get(id);
    }

    public Task getSubtask(int id) {
        return subtasks.get(id);
    }

    public void addTask(Task task) {
        task.setId(nextId++);
        tasks.put(task.getId(), task);
    }

    public void addEpic(Epic epic) {
        epic.setId(nextId++);
        epics.put(epic.getId(), epic);
    }

    public void addSubtask(Subtask subtask) {
        Epic epic = epics.get(subtask.getIdEpic());

        if (epic == null) {
            return;
        }

        subtask.setId(nextId++);
        subtasks.put(subtask.getId(), subtask);
        epic.getSubtasksInEpic().add(subtask.getId());
        epic.updateStatus(this);
    }

    public void updateTask(int id, Task task) {
        if (tasks.containsKey(id)) {
            tasks.put(id, task);
        }
    }

    public void updateEpic(int id, Epic epic) {
        if (epics.containsKey(id)) {
            epics.put(id, epic);
        }
    }

    public void updateSubtask(int id, Subtask subtask) {
        if (subtasks.containsKey(id)) {
            subtasks.put(id, subtask);
            Epic epic = epics.get(subtask.getIdEpic());
            if (epic != null) {
                epic.updateStatus(this);
            }
        }
    }

    public void deleteTask(int id) {
       tasks.remove(id);
    }

    public void deleteEpic(int id) {
        Epic removedEpic = epics.remove(id);
        if (removedEpic != null) {
            for (Integer subtaskId : removedEpic.getSubtasksInEpic()) {
                subtasks.remove(subtaskId);
            }
        }
    }

    public void deleteSubtask(int id) {
        Subtask removedSubtask = subtasks.remove(id);
        if (removedSubtask != null) {
            Epic epic = epics.get(removedSubtask.getIdEpic());
            if (epic != null) {
                epic.getSubtasksInEpic().remove((Integer) id);
                epic.updateStatus(this);
            }
        }
    }


    public List<Subtask> getSubtasks(int idEpic) {
        Epic epic = epics.get(idEpic);
        if (epic == null) {
            return new ArrayList<>();
        }


        List<Subtask> subtasksList = new ArrayList<>();
        for (Integer subtaskId : epic.getSubtasksInEpic()) {
            Subtask subtask = subtasks.get(subtaskId);
            if (subtask != null) {
                subtasksList.add(subtask);
            }
        }
        return subtasksList;
    }
}