package model;

public class Subtask extends Task {
    private int epicId;

    public Subtask(String name, String description, TaskStatus status, int idEpic) {
        super(name, description, status);
        this.epicId = idEpic;
    }

    public int getIdEpic() {
        return epicId;
    }

    @Override
    public String toString() {
        return "model.model.Subtask{" +
                "name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", id=" + getId() +
                ", status=" + getStatus() +
                ", idEpic" + epicId +
                '}';
    }
}