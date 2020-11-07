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
import seedu.schedar.logic.commands.exceptions.CommandException;
import seedu.schedar.model.Model;
import seedu.schedar.model.ReadOnlyTaskManager;
import seedu.schedar.model.ReadOnlyUserPrefs;
import seedu.schedar.model.TaskManager;
import seedu.schedar.model.task.Event;
import seedu.schedar.model.task.Task;
import seedu.schedar.model.task.ToDo;
import seedu.schedar.testutil.EventBuilder;
import seedu.schedar.testutil.ToDoBuilder;

public class AddEventCommandTest {

    @Test
    public void constructor_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddEventCommand(null));
    }

    @Test
    public void execute_eventAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingTaskAdded modelStub = new ModelStubAcceptingTaskAdded();
        Event validEvent = new EventBuilder().build();

        CommandResult commandResult = new AddEventCommand(validEvent).execute(modelStub);

        assertEquals(String.format(AddEventCommand.MESSAGE_SUCCESS, validEvent), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validEvent), modelStub.tasksAdded);
    }

    @Test
    public void execute_duplicateTask_throwsCommandException() {
        Event validEvent = new EventBuilder().build();
        AddEventCommand addEventCommand = new AddEventCommand(validEvent);
        ModelStub modelStub = new ModelStubWithTask(validEvent);

        assertThrows(CommandException.class, AddEventCommand.MESSAGE_DUPLICATE_TASK, ()
            -> addEventCommand.execute(modelStub));
    }

    @Test
    public void equals() {
        Event lecture = new EventBuilder().withTitle("Lecture").build();
        Event tutorial = new EventBuilder().withTitle("Tutorial").build();
        AddEventCommand addLectureCommand = new AddEventCommand(lecture);
        AddEventCommand addTutorialCommand = new AddEventCommand(tutorial);

        // same object -> return true
        assertTrue(addLectureCommand.equals(addLectureCommand));

        // same values -> returns true
        AddEventCommand addLectureCommandCopy = new AddEventCommand(lecture);
        assertTrue(addLectureCommand.equals(addLectureCommandCopy));

        // different types -> returns false
        ToDo validTodo = new ToDoBuilder().build();
        AddTodoCommand addTodoCommand = new AddTodoCommand(validTodo);
        assertFalse(addLectureCommand.equals(addTodoCommand));

        // null -> returns false
        assertFalse(addLectureCommand.equals(null));

        // different Events -> returns false
        assertFalse(addLectureCommand.equals(addTutorialCommand));
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
        public void sortTask(Comparator<Task> comparator) {
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
        public ReadOnlyTaskManager getTaskManager() {
            return new TaskManager();
        }
    }
}
