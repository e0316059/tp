package seedu.schedar.logic.commands;

import static seedu.schedar.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.schedar.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.schedar.logic.commands.CommandTestUtil.deleteFirstTask;
import static seedu.schedar.testutil.TypicalTasks.getTypicalTaskManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.schedar.logic.CommandHistory;
import seedu.schedar.model.Model;
import seedu.schedar.model.ModelManager;
import seedu.schedar.model.UserPrefs;

public class RedoCommandTest {

    private final Model model = new ModelManager(getTypicalTaskManager(), new UserPrefs());
    private final Model expectedModel = new ModelManager(getTypicalTaskManager(), new UserPrefs());
    private final CommandHistory commandHistory = new CommandHistory();

    @BeforeEach
    public void setUp() {
        // set up of both models' undo/redo history
        deleteFirstTask(model);
        deleteFirstTask(model);
        model.undoTaskManager();
        model.undoTaskManager();

        deleteFirstTask(expectedModel);
        deleteFirstTask(expectedModel);
        expectedModel.undoTaskManager();
        expectedModel.undoTaskManager();
    }

    @Test
    public void execute() {
        // multiple redoable states in model
        expectedModel.redoTaskManager();
        assertCommandSuccess(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_SUCCESS, expectedModel);

        // single redoable state in model
        expectedModel.redoTaskManager();
        assertCommandSuccess(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_SUCCESS, expectedModel);

        // no redoable state in model
        assertCommandFailure(new RedoCommand(), model, commandHistory, RedoCommand.MESSAGE_FAILURE);
    }
}
