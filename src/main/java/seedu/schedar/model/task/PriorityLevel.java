package seedu.schedar.model.task;

public enum PriorityLevel {
    NONE("No Priority Assigned"),
    LOW("Low"),
    MEDIUM("Medium"),
    HIGH("High");

    private String label;

    private PriorityLevel(String label) {
        this.label = label;
    }

    @Override
    public String toString() {
        return label;
    }
}
