package seedu.schedar.model;

import javafx.collections.ObservableList;
import seedu.schedar.model.task.Task;

/**
 * Unmodifiable view of a task manager
 */
public interface ReadOnlyTaskManager {

    /**
     * Returns an unmodifiable view of the task list.
     * This list will not contain any duplicate tasks.
     */
    ObservableList<Task> getTaskList();
}
