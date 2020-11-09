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
import seedu.schedar.model.task.Event;
import seedu.schedar.model.task.ToDo;
import seedu.schedar.testutil.EventBuilder;
import seedu.schedar.testutil.ToDoBuilder;

public class AddEventCommandTest {

    private static final CommandHistory EMPTY_COMMAND_HISTORY = new CommandHistory();

    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void constructor_nullEvent_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddEventCommand(null));
    }

    @Test
    public void execute_eventAcceptedByModel_addSuccessful() throws Exception {
        ModelStubAcceptingTaskAdded modelStub = new ModelStubAcceptingTaskAdded();
        Event validEvent = new EventBuilder().build();

        CommandResult commandResult = new AddEventCommand(validEvent).execute(modelStub, commandHistory);

        assertEquals(String.format(AddEventCommand.MESSAGE_SUCCESS, validEvent), commandResult.getFeedbackToUser());
        assertEquals(Arrays.asList(validEvent), modelStub.tasksAdded);
        assertEquals(EMPTY_COMMAND_HISTORY, commandHistory);
    }

    @Test
    public void execute_duplicateTask_throwsCommandException() {
        Event validEvent = new EventBuilder().build();
        AddEventCommand addEventCommand = new AddEventCommand(validEvent);
        ModelStub modelStub = new ModelStubWithTask(validEvent);

        assertThrows(CommandException.class, AddEventCommand.MESSAGE_DUPLICATE_TASK, ()
            -> addEventCommand.execute(modelStub, commandHistory));
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
}
