package seedu.address.model.task;

import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Collections;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

import seedu.address.model.tag.Tag;

/**
 * Represents a Task in the task manager.
 * Guarantees: details are present and not null, field values are validated, immutable.
 */
public abstract class Task {

    // Identity fields
    protected final Title title;

    // Data fields
    protected final Description description;
    protected final Priority priority;
    protected DoneStatus status;
    protected final Set<Tag> tags = new HashSet<>();

    /**
     * Create new Task.
     * Every field must be present and not null.
     */
    public Task(Title title, Description description, Priority priority, Set<Tag> tags) {
        requireAllNonNull(title, priority, tags);
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = new DoneStatus();
        this.tags.addAll(tags);
    }

    /**
     * Create new Task with DoneStatus.
     * Every field must be present and not null.
     */
    public Task(Title title, Description description, Priority priority, DoneStatus status, Set<Tag> tags) {
        requireAllNonNull(title, priority, tags);
        this.title = title;
        this.description = description;
        this.priority = priority;
        this.status = status;
        this.tags.addAll(tags);
    }

    public Title getTitle() {
        return title;
    }

    public Description getDescription() {
        return description;
    }

    public Priority getPriority() {
        return priority;
    }

    public DoneStatus getStatus() {
        return status;
    }

    public void markDone() {
        this.status = new DoneStatus(1);
    }

    /**
     * Returns an immutable tag set, which throws {@code UnsupportedOperationException}
     * if modification is attempted.
     */
    public Set<Tag> getTags() {
        return Collections.unmodifiableSet(tags);
    }

    /**
     * Returns true if two tasks of the same title and description.
     * This defines a weaker notion of equality between two tasks.
     */
    public boolean isSameTask(Task otherTask) {
        if (otherTask == this) {
            return true;
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

        if (!(other instanceof Task)) {
            return false;
        }

        Task otherTask = (Task) other;
        return otherTask.getTitle().equals(getTitle())
                && otherTask.getDescription().equals(getDescription())
                && otherTask.getPriority().equals(getPriority())
                && otherTask.getStatus().equals(getStatus())
                && otherTask.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title, description, priority, tags);
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
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }
}
