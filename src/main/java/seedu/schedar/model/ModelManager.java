package seedu.schedar.model;

import static java.util.Objects.requireNonNull;
import static seedu.schedar.commons.util.CollectionUtil.requireAllNonNull;

import java.nio.file.Path;
import java.util.Comparator;
import java.util.function.Predicate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import seedu.schedar.commons.core.GuiSettings;
import seedu.schedar.commons.core.LogsCenter;
import seedu.schedar.model.task.Task;

/**
 * Represents the in-memory model of the task manager data.
 */
public class ModelManager implements Model {
    private static final Logger logger = LogsCenter.getLogger(ModelManager.class);

    private final VersionedTaskManager versionedTaskManager;
    private final UserPrefs userPrefs;
    private final FilteredList<Task> filteredTasks;

    /**
     * Initializes a ModelManager with the given versionedTaskManager and userPrefs.
     */
    public ModelManager(ReadOnlyTaskManager taskManager, ReadOnlyUserPrefs userPrefs) {
        super();
        requireAllNonNull(taskManager, userPrefs);

        logger.fine("Initializing with task manager: " + taskManager + " and user prefs " + userPrefs);

        this.versionedTaskManager = new VersionedTaskManager(taskManager);
        this.userPrefs = new UserPrefs(userPrefs);
        filteredTasks = new FilteredList<>(this.versionedTaskManager.getTaskList());
    }

    public ModelManager() {
        this(new TaskManager(), new UserPrefs());
    }

    //=========== UserPrefs ==================================================================================

    @Override
    public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
        requireNonNull(userPrefs);
        this.userPrefs.resetData(userPrefs);
    }

    @Override
    public ReadOnlyUserPrefs getUserPrefs() {
        return userPrefs;
    }

    @Override
    public GuiSettings getGuiSettings() {
        return userPrefs.getGuiSettings();
    }

    @Override
    public void setGuiSettings(GuiSettings guiSettings) {
        requireNonNull(guiSettings);
        userPrefs.setGuiSettings(guiSettings);
    }

    @Override
    public Path getTaskManagerFilePath() {
        return userPrefs.getTaskManagerFilePath();
    }

    @Override
    public void setTaskManagerFilePath(Path taskManagerFilePath) {
        requireNonNull(taskManagerFilePath);
        userPrefs.setTaskManagerFilePath(taskManagerFilePath);
    }

    //=========== TaskManager ================================================================================

    @Override
    public void setTaskManager(ReadOnlyTaskManager taskManager) {
        this.versionedTaskManager.resetData(taskManager);
    }

    @Override
    public ReadOnlyTaskManager getTaskManager() {
        return versionedTaskManager;
    }

    @Override
    public boolean hasTask(Task task) {
        requireNonNull(task);
        return versionedTaskManager.hasTask(task);
    }

    @Override
    public void deleteTask(Task target) {
        versionedTaskManager.removeTask(target);
    }

    @Override
    public void addRecentDeletedTask(Task task) {
        versionedTaskManager.addRecentDeletedTask(task);
    }

    public void retrieveRecentDeletedTask() {
        versionedTaskManager.retrieveRecentDeletedTask();
    }

    @Override
    public void addTask(Task task) {
        versionedTaskManager.addTask(task);
        updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
    }

    @Override
    public void sortTask(Comparator<Task> comparator) {
        versionedTaskManager.sort(comparator);
    }

    @Override
    public void doneTask(Task task) {
        versionedTaskManager.doneTask(task);
    }

    @Override
    public void setTask(Task target, Task editedTask) {
        requireAllNonNull(target, editedTask);

        versionedTaskManager.setTask(target, editedTask);
    }

    //=========== Filtered Task List Accessors =============================================================

    /**
     * Returns an unmodifiable view of the list of {@code Task} backed by the internal list of
     * {@code versionedTaskManager}
     */
    @Override
    public ObservableList<Task> getFilteredTaskList() {
        return filteredTasks;
    }

    @Override
    public void updateFilteredTaskList(Predicate<Task> predicate) {
        requireNonNull(predicate);
        filteredTasks.setPredicate(predicate);
    }

    @Override
    public boolean equals(Object obj) {
        // short circuit if same object
        if (obj == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(obj instanceof ModelManager)) {
            return false;
        }

        // state check
        ModelManager other = (ModelManager) obj;
        return versionedTaskManager.equals(other.versionedTaskManager)
                && userPrefs.equals(other.userPrefs)
                && filteredTasks.equals(other.filteredTasks);
    }

    //=========== Undo/Redo =================================================================================

    @Override
    public boolean canUndoTaskManager() {
        return versionedTaskManager.canUndo();
    }

    @Override
    public boolean canRedoTaskManager() {
        return versionedTaskManager.canRedo();
    }

    @Override
    public void undoTaskManager() {
        versionedTaskManager.undo();
    }

    @Override
    public void redoTaskManager() {
        versionedTaskManager.redo();
    }

    @Override
    public void commitTaskManager() {
        versionedTaskManager.commit();
    }

}
