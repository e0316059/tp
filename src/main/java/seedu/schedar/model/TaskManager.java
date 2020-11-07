package seedu.schedar.model;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;
import java.util.List;

import javafx.collections.ObservableList;
import seedu.schedar.model.task.Task;
import seedu.schedar.model.task.UniqueTaskList;

public class TaskManager implements ReadOnlyTaskManager {

    private final UniqueTaskList tasks;

    {
        tasks = new UniqueTaskList();
    }

    public TaskManager() {}

    /**
     * Creates a TaskManager using the Tasks in the {@code toBeCopied}
     */
    public TaskManager(ReadOnlyTaskManager toBeCopied) {
        this();
        resetData(toBeCopied);
    }

    /// list overwrite operations

    /**
     * Replaces the contents of the task list with {@code tasks}.
     * {@code tasks} must not contain duplicate tasks.
     */
    public void setTasks(List<Task> tasks) {
        this.tasks.setTasks(tasks);
    }

    /**
     * Resets the existing data of this {@code TaskManager} with {@code newData}.
     */
    public void resetData(ReadOnlyTaskManager newData) {
        requireNonNull(newData);

        setTasks(newData.getTaskList());
    }

    //// task-level operations

    /**
     * Returns true if a task with the same identity as {@code task} exists in the task manager.
     */
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return tasks.contains(task);
    }

    /**
     * Adds a task to the task manager.
     * The task must not already exist in the task manager.
     */
    public void addTask(Task t) {
        tasks.add(t);
    }

    /**
     * Replaces the given task {@code target} in the list with {@code editedTask}.
     * {@code target} must exist in the task manager.
     * The task identity of {@code editedTask} must not be the same as another existing task in the task manager.
     */
    public void setTask(Task target, Task editedTask) {
        requireNonNull(editedTask);

        tasks.setTask(target, editedTask);
    }

    /**
     * Removes {@code key} from this {@code TaskManager}.
     * {@code key} must exist in the task manager.
     */
    public void removeTask(Task key) {
        tasks.remove(key);
    }

    /**
     * Sorts the task list according to {@code comparator}.
     */
    public void sort(Comparator<Task> comparator) {
        tasks.sort(comparator);
    }

    /**
     * Stores the most recently deleted task {@code key} in this {@code TaskManager}.
     */
    public void addRecentDeletedTask(Task key) {
        tasks.addRecentDeletedTask(key);
    }

    /**
     * Retrieves the most recently deleted task {@code key} in this {@code TaskManager}.
     */
    public void retrieveRecentDeletedTask() {
        tasks.retrieveRecentDeletedTask();
    }

    /**
     * Marks {@code key} as done in this {@code TaskManager}.
     * {@code key} must exist in the task manager.
     */
    public void doneTask(Task key) {
        key.markDone();
        tasks.setTask(key, key);
    }

    //// util methods

    @Override
    public String toString() {
        return tasks.asUnmodifiableObservableList().size() + " tasks";
        // TODO: refine later
    }

    @Override
    public ObservableList<Task> getTaskList() {
        return tasks.asUnmodifiableObservableList();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof TaskManager // instanceof handles nulls
                && tasks.equals(((TaskManager) other).tasks));
    }

    @Override
    public int hashCode() {
        return tasks.hashCode();
    }
}
