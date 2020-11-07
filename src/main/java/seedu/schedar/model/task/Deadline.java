package seedu.schedar.model.task;

import static java.util.Objects.requireNonNull;

import java.util.Objects;
import java.util.Set;

import seedu.schedar.model.tag.Tag;

public class Deadline extends Task {

    public static final String TASK_TYPE = "DEADLINE";

    protected final TaskDate deadlineDate;

    /**
     * Constructor without DoneStatus.
     * Every field must be present and not null.
     */
    public Deadline(Title title, Description description, Priority priority, TaskDate deadlineDate, Set<Tag> tags) {
        super(title, description, priority, tags);
        requireNonNull(deadlineDate);
        this.deadlineDate = deadlineDate;
    }

    /**
     * Constructor with DoneStatus.
     * Every field must be present and not null.
     */
    public Deadline(Title title, Description description, Priority priority, TaskDate deadlineDate,
                    DoneStatus status, Set<Tag> tags) {
        super(title, description, priority, status, tags);
        this.deadlineDate = deadlineDate;
    }

    public TaskDate getDeadlineDate() {
        return deadlineDate;
    }

    /**
     * Returns true if two tasks of the same title and description.
     * This defines a weaker notion of equality between two tasks.
     */
    @Override
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
        }

        if (!(otherTask instanceof Deadline)) {
            return false;
        }

        return otherTask != null
                && otherTask.getTitle().equals(getTitle())
                && otherTask.getDescription().equals(getDescription());
    }

    /**
     * Returns true if both tasks have the same identity and data fields.
     * This defines a stronger notion of equality between two tasks.
     */
    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof Deadline)) {
            return false;
        }

        Deadline otherTask = (Deadline) other;
        return otherTask.getTitle().equals(getTitle())
                && otherTask.getDescription().equals(getDescription())
                && otherTask.getPriority().equals(getPriority())
                && otherTask.getStatus().equals(getStatus())
                && otherTask.getDeadlineDate().equals(getDeadlineDate())
                && otherTask.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title, description, priority, deadlineDate, tags);
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append(getTitle())
                .append(" Description: ")
                .append(getDescription())
                .append(" Priority: ")
                .append(getPriority())
                .append(" Status: ")
                .append(getStatus())
                .append(" Deadline: ")
                .append(getDeadlineDate())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

}
