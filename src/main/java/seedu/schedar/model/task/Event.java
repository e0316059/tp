package seedu.schedar.model.task;

import static seedu.schedar.commons.util.CollectionUtil.requireAllNonNull;

import java.util.Objects;
import java.util.Set;

import seedu.schedar.model.tag.Tag;

public class Event extends Task {

    public static final String TASK_TYPE = "EVENT";

    protected final TaskDate eventDate;
    protected final TaskTime eventTime;

    /**
     * Constructor without DoneStatus.
     * Every field must be present and not null.
     */
    public Event(Title title, Description description, Priority priority,
                 TaskDate eventDate, TaskTime eventTime, Set<Tag> tags) {
        super(title, description, priority, tags);
        requireAllNonNull(eventDate, eventTime);
        this.eventDate = eventDate;
        this.eventTime = eventTime;
    }

    /**
     * Constructor with DoneStatus.
     * Every field must be present and not null.
     */
    public Event(Title title, Description description, Priority priority,
                 TaskDate eventDate, TaskTime eventTime, DoneStatus status, Set<Tag> tags) {
        super(title, description, priority, status, tags);
        this.eventDate = eventDate;
        this.eventTime = eventTime;
    }

    public TaskDate getEventDate() {
        return eventDate;
    }

    public TaskTime getEventTime() {
        return eventTime;
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

        if (!(otherTask instanceof Event)) {
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

        if (!(other instanceof Event)) {
            return false;
        }

        Event otherTask = (Event) other;
        return otherTask.getTitle().equals(getTitle())
                && otherTask.getDescription().equals(getDescription())
                && otherTask.getPriority().equals(getPriority())
                && otherTask.getStatus().equals(getStatus())
                && otherTask.getEventDate().equals(getEventDate())
                && otherTask.getEventTime().equals(getEventTime())
                && otherTask.getTags().equals(getTags());
    }

    @Override
    public int hashCode() {
        // use this method for custom fields hashing instead of implementing your own
        return Objects.hash(title, description, priority, eventDate, eventTime, tags);
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
                .append(" Event Date: ")
                .append(getEventDate())
                .append(" Event Time: ")
                .append(getEventTime())
                .append(" Tags: ");
        getTags().forEach(builder::append);
        return builder.toString();
    }

}
