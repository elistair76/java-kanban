public class Subtask extends Task {
    private int idEpic;

    public Subtask(String name, String description, TaskStatus status, int idEpic) {
        super(name, description, status);
        this.idEpic = idEpic;
    }

    public int getIdEpic() {
        return idEpic;
    }

    @Override
    public String toString() {
        return "model.Subtask{" +
                "name='" + getName() + '\'' +
                ", description='" + getDescription() + '\'' +
                ", id=" + getId() +
                ", status=" + getStatus() +
                ", idEpic" + idEpic +
                '}';
    }
}