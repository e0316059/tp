package seedu.schedar.testutil;

import seedu.schedar.model.TaskManager;
import seedu.schedar.model.task.Task;

/**
 * A utility class to help with building TaskManager objects.
 * Example usage: <br>
 *     {@code TaskManager tm = new TaskManagerBuilder().withTask("Lecture", "Project").build();}
 */
public class TaskManagerBuilder {

    private TaskManager taskManager;

    public TaskManagerBuilder() {
        taskManager = new TaskManager();
    }

    public TaskManagerBuilder(TaskManager taskManager) {
        this.taskManager = taskManager;
    }

    /**
     * Adds a new {@code Task} to the {@code TaskManager} that we are building.
     */
    public TaskManagerBuilder withTask(Task task) {
        taskManager.addTask(task);
        return this;
    }

    public TaskManager build() {
        return taskManager;
    }
}
