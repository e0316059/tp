package seedu.schedar.model.task;

import java.util.Set;

import seedu.schedar.model.tag.Tag;

public class ToDo extends Task {

    public static final String TASK_TYPE = "TODO";

    /**
     * Every field must be present and not null.
     */
    public ToDo(Title title, Description description, Priority priority, Set<Tag> tags) {
        super(title, description, priority, tags);
    }

    public ToDo(Title title, Description description, Priority priority, DoneStatus status, Set<Tag> tags) {
        super(title, description, priority, status, tags);
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

        if (!(otherTask instanceof ToDo)) {
            return false;
        }

        return otherTask != null
                && otherTask.getTitle().equals(getTitle())
                && otherTask.getDescription().equals(getDescription());
    }
}
