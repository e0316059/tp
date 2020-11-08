package seedu.schedar.logic.commands;

import static java.util.Objects.requireNonNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.schedar.testutil.Assert.assertThrows;

import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import javafx.collections.ObservableList;
import seedu.schedar.commons.core.GuiSettings;
import seedu.schedar.logic.CommandHistory;
import seedu.schedar.logic.commands.exceptions.CommandException;
import seedu.schedar.model.Model;
import seedu.schedar.model.ReadOnlyTaskManager;
import seedu.schedar.model.ReadOnlyUserPrefs;
import seedu.schedar.model.TaskManager;
import seedu.schedar.model.task.Deadline;
import seedu.schedar.model.task.Task;
import seedu.schedar.model.task.ToDo;
import seedu.schedar.testutil.DeadlineBuilder;
import seedu.schedar.testutil.ToDoBuilder;

public class AddDeadlineCommandTest {

    private static final CommandHistory EMPTY_COMMAND_HISTORY = new CommandHistory();

    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void constructor_nullDeadline_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddDeadlineCommand(null));
    }

    @Test
    public void execute_deadlineAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingTaskAdded modelStub = new ModelStubAcceptingTaskAdded();
        Deadline validDeadline = new DeadlineBuilder().build();

        CommandResult commandResult = new AddDeadlineCommand(validDeadline).execute(modelStub, commandHistory);

        assertEquals(String.format(AddDeadlineCommand.MESSAGE_SUCCESS, validDeadline),
                commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validDeadline), modelStub.tasksAdded);
        assertEquals(EMPTY_COMMAND_HISTORY, commandHistory);
    }

    @Test
    public void execute_duplicateTask_throwsCommandException() {
        Deadline validDeadline = new DeadlineBuilder().build();
        AddDeadlineCommand addDeadlineCommand = new AddDeadlineCommand(validDeadline);
        ModelStub modelStub = new ModelStubWithTask(validDeadline);

        assertThrows(CommandException.class, AddDeadlineCommand.MESSAGE_DUPLICATE_TASK, ()
            -> addDeadlineCommand.execute(modelStub, commandHistory));
    }

    @Test
    public void equals() {
        Deadline homework = new DeadlineBuilder().withTitle("Homework").build();
        Deadline project = new DeadlineBuilder().withTitle("Project").build();
        AddDeadlineCommand addHomeworkCommand = new AddDeadlineCommand(homework);
        AddDeadlineCommand addProjectCommand = new AddDeadlineCommand(project);

        // same object -> return true
        assertTrue(addHomeworkCommand.equals(addHomeworkCommand));

        // same values -> returns true
        AddDeadlineCommand addHomeworkCommandCopy = new AddDeadlineCommand(homework);
        assertTrue(addHomeworkCommand.equals(addHomeworkCommandCopy));

        // different types -> returns false
        ToDo validTodo = new ToDoBuilder().build();
        AddTodoCommand addTodoCommand = new AddTodoCommand(validTodo);
        assertFalse(addHomeworkCommand.equals(addTodoCommand));

        // null -> returns false
        assertFalse(addHomeworkCommand.equals(null));

        // different Deadlines -> returns false
        assertFalse(addHomeworkCommand.equals(addProjectCommand));
    }

    /**
     * A default model stub that have all of the methods failing.
     */
    private class ModelStub implements Model {
        @Override
        public void setUserPrefs(ReadOnlyUserPrefs userPrefs) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyUserPrefs getUserPrefs() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public GuiSettings getGuiSettings() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setGuiSettings(GuiSettings guiSettings) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public Path getTaskManagerFilePath() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTaskManagerFilePath(Path taskManagerFilePath) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addTask(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void doneTask(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void sortTask(Comparator<Task> comparator) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTaskManager(ReadOnlyTaskManager newData) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ReadOnlyTaskManager getTaskManager() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean hasTask(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void deleteTask(Task target) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void addRecentDeletedTask(Task task) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void retrieveRecentDeletedTask() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public long count() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canUndoTaskManager() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public boolean canRedoTaskManager() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void undoTaskManager() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void redoTaskManager() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void commitTaskManager() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void setTask(Task target, Task editedTask) {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public ObservableList<Task> getFilteredTaskList() {
            throw new AssertionError("This method should not be called.");
        }

        @Override
        public void updateFilteredTaskList(Predicate<Task> predicate) {
            throw new AssertionError("This method should not be called.");
        }
    }

    /**
     * A Model stub that contains a single task.
     */
    private class ModelStubWithTask extends ModelStub {
        private final Task task;

        ModelStubWithTask(Task task) {
            requireNonNull(task);
            this.task = task;
        }

        @Override
        public boolean hasTask(Task task) {
            requireNonNull(task);
            return this.task.isSameTask(task);
        }
    }

    /**
     * A Model stub that always accept the task being added.
     */
    private class ModelStubAcceptingTaskAdded extends ModelStub {
        final ArrayList<Task> tasksAdded = new ArrayList<>();

        @Override
        public boolean hasTask(Task task) {
            requireNonNull(task);
            return tasksAdded.stream().anyMatch(task::isSameTask);
        }

        @Override
        public void addTask(Task task) {
            requireNonNull(task);
            tasksAdded.add(task);
        }

        @Override
        public void commitTaskManager() {
            // called by {@code AddDeadlineCommand#execute()}
        }

        @Override
        public ReadOnlyTaskManager getTaskManager() {
            return new TaskManager();
        }
    }
}
