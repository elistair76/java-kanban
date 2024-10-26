package model;

import service.TaskManager;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {
    private ArrayList<Integer> subtaskIds;

    public Epic(String name, String description) {
        super(name, description, TaskStatus.NEW);
        subtaskIds = new ArrayList<>();
    }

    public ArrayList<Integer> getSubtasksInEpic() {
        return subtaskIds;
    }

    public void updateStatus(TaskManager taskManager) {
        List<Subtask> subtasks = taskManager.getSubtasks(this.getId());
        if (subtasks.isEmpty()) {
            this.setStatus(TaskStatus.NEW);
            return;
        }

        boolean allDone = true;
        boolean anyInProgress = false;

        for (Subtask subtask : subtasks) {
            if (subtask.getStatus() == TaskStatus.IN_PROGRESS) {
                anyInProgress = true;
                break;
            }
            if (subtask.getStatus() != TaskStatus.DONE) {
                allDone = false;
            }
        }

        if (allDone) {
            this.setStatus(TaskStatus.DONE);
        } else if (anyInProgress) {
            this.setStatus(TaskStatus.IN_PROGRESS);
        } else {
            this.setStatus(TaskStatus.NEW);
        }
    }

    @Override
    public String toString() {
        return "model.model.Epic{" +
                "name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", id=" + getId() +
                ", status=" + getStatus() +
                ", subtasksInEpic=" + subtaskIds +
                '}';
    }

}