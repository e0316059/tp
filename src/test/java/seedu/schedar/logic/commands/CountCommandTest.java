package seedu.schedar.logic.commands;

import static seedu.schedar.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.schedar.testutil.TypicalTasks.getTypicalTaskManager;

import org.junit.jupiter.api.Test;

import seedu.schedar.logic.CommandHistory;
import seedu.schedar.model.Model;
import seedu.schedar.model.ModelManager;
import seedu.schedar.model.UserPrefs;

public class CountCommandTest {

    private Model model = new ModelManager(getTypicalTaskManager(), new UserPrefs());
    private CommandHistory commandHistory = new CommandHistory();

    @Test
    public void execute_count_success() {
        long count = model.count();
        CountCommand countCommand = new CountCommand();

        String expectedMessage = CountCommand.MESSAGE_SUCCESS + count;

        ModelManager expectedModel = new ModelManager(model.getTaskManager(), new UserPrefs());
        expectedModel.count();

        assertCommandSuccess(countCommand, model, commandHistory, expectedMessage, expectedModel);
    }
}

