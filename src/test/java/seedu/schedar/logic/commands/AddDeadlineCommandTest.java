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
}
