package seedu.address.model;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;

import javafx.collections.ObservableList;
import seedu.address.commons.core.GuiSettings;
import seedu.address.model.task.Task;

/**
 * The API of the Model component.
 */
public interface Model {
    /** {@code Predicate} that always evaluate to true */
    Predicate<Task> PREDICATE_SHOW_ALL_TASKS = unused -> true;

    /**
     * Replaces user prefs data with the data in {@code userPrefs}.
     */
    void setUserPrefs(ReadOnlyUserPrefs userPrefs);

    /**
     * Returns the user prefs.
     */
    ReadOnlyUserPrefs getUserPrefs();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Sets the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);

    /**
     * Returns the user prefs' task manager file path.
     */
    Path getTaskManagerFilePath();

    /**
     * Sets the user prefs' task manager file path.
     */
    void setTaskManagerFilePath(Path taskManagerFilePath);

    /**
     * Replaces task manager data with the data in {@code taskManager}.
     */
    void setTaskManager(ReadOnlyTaskManager taskManager);

    /** Returns the TaskManager */
    ReadOnlyTaskManager getTaskManager();

    /**
     * Returns true if a task with the same identity as {@code task} exists in the task manager.
     */
    boolean hasTask(Task task);

    /**
     * Deletes the given task.
     * The task must exist in the task manager.
     */
    void deleteTask(Task target);

    void sortTask(Comparator<Task> comparator);

    /**
     * Adds the given task.
     * {@code task} must not already exist in the task manager.
     */
    void addTask(Task task);

    void doneTask(Task task);

    /**
     * Replaces the given task {@code target} with {@code editedTask}.
     * {@code target} must exist in the task manager.
     * The task identity of {@code editedTask} must not be the same as another existing task in the task manager.
     */
    void setTask(Task target, Task editedTask);

    /** Returns an unmodifiable view of the filtered task list */
    ObservableList<Task> getFilteredTaskList();

    /**
     * Updates the filter of the filtered task list to filter by the given {@code predicate}.
     * @throws NullPointerException if {@code predicate} is null.
     */
    void updateFilteredTaskList(Predicate<Task> predicate);
}
