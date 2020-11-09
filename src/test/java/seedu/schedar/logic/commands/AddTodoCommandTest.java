package seedu.schedar.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.schedar.logic.commands.AddCommandTestUtil.ModelStub;
import static seedu.schedar.logic.commands.AddCommandTestUtil.ModelStubAcceptingTaskAdded;
import static seedu.schedar.logic.commands.AddCommandTestUtil.ModelStubWithTask;
import static seedu.schedar.testutil.Assert.assertThrows;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.schedar.logic.CommandHistory;
import seedu.schedar.logic.commands.exceptions.CommandException;
import seedu.schedar.model.task.Deadline;
import seedu.schedar.model.task.ToDo;
import seedu.schedar.testutil.DeadlineBuilder;
import seedu.schedar.testutil.ToDoBuilder;

public class AddTodoCommandTest {

    private static final CommandHistory EMPTY_COMMAND_HISTORY = new CommandHistory();

    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void constructor_nullToDo_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddTodoCommand(null));
    }

    @Test
    public void execute_todoAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingTaskAdded modelStub = new ModelStubAcceptingTaskAdded();
        ToDo validTodo = new ToDoBuilder().build();

        CommandResult commandResult = new AddTodoCommand(validTodo).execute(modelStub, commandHistory);

        assertEquals(String.format(AddTodoCommand.MESSAGE_SUCCESS, validTodo), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validTodo), modelStub.tasksAdded);
        assertEquals(EMPTY_COMMAND_HISTORY, commandHistory);
    }

    @Test
    public void execute_duplicateTask_throwsCommandException() {
        ToDo validTodo = new ToDoBuilder().build();
        AddTodoCommand addTodoCommand = new AddTodoCommand(validTodo);
        ModelStub modelStub = new ModelStubWithTask(validTodo);

        assertThrows(CommandException.class, AddTodoCommand.MESSAGE_DUPLICATE_TASK, ()
            -> addTodoCommand.execute(modelStub, commandHistory));
    }

    @Test
    public void equals() {
        ToDo eat = new ToDoBuilder().withTitle("Eat").build();
        ToDo sleep = new ToDoBuilder().withTitle("Sleep").build();
        AddTodoCommand addEatCommand = new AddTodoCommand(eat);
        AddTodoCommand addSleepCommand = new AddTodoCommand(sleep);

        // same object -> return true
        assertTrue(addEatCommand.equals(addEatCommand));

        // same values -> returns true
        AddTodoCommand addEatCommandCopy = new AddTodoCommand(eat);
        assertTrue(addEatCommand.equals(addEatCommandCopy));

        // different types -> returns false
        Deadline validDeadline = new DeadlineBuilder().build();
        AddDeadlineCommand addDeadlineCommand = new AddDeadlineCommand(validDeadline);
        assertFalse(addEatCommand.equals(addDeadlineCommand));

        // null -> returns false
        assertFalse(addEatCommand.equals(null));

        // different ToDo -> returns false
        assertFalse(addEatCommand.equals(addSleepCommand));
    }
}
