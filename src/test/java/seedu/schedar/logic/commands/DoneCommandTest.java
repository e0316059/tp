package seedu.schedar.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.schedar.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.schedar.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.schedar.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.schedar.testutil.TypicalTasks.getTypicalTaskManager;

import org.junit.jupiter.api.Test;

import seedu.schedar.commons.core.Messages;
import seedu.schedar.commons.core.index.Index;
import seedu.schedar.logic.CommandHistory;
import seedu.schedar.model.Model;
import seedu.schedar.model.ModelManager;
import seedu.schedar.model.UserPrefs;
import seedu.schedar.model.task.Task;


public class DoneCommandTest {

    private Model model = new ModelManager(getTypicalTaskManager(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Task taskToDone = model.getFilteredTaskList().get(INDEX_FIRST_TASK.getZeroBased());
        DoneCommand doneCommand = new DoneCommand(INDEX_FIRST_TASK);

        ModelManager expectedModel = new ModelManager(model.getTaskManager(), new UserPrefs());
        expectedModel.doneTask(taskToDone);
        expectedModel.commitTaskManager();

        String expectedMessage = String.format(DoneCommand.MESSAGE_DONE_TASK_SUCCESS, taskToDone);

        assertCommandSuccess(doneCommand, model, commandHistory, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredTaskList().size() + 1);
        DoneCommand doneCommand = new DoneCommand(outOfBoundIndex);

        assertCommandFailure(doneCommand, model, commandHistory, Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void testEquality() {
        DoneCommand doneCommand = new DoneCommand(INDEX_FIRST_TASK);
        assertEquals(doneCommand, new DoneCommand(INDEX_FIRST_TASK));
    }
}

