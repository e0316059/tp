package seedu.schedar.logic.commands;

import static seedu.schedar.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.schedar.testutil.TypicalTasks.getTypicalTaskManager;

import org.junit.jupiter.api.Test;

import seedu.schedar.model.Model;
import seedu.schedar.model.ModelManager;
import seedu.schedar.model.TaskManager;
import seedu.schedar.model.UserPrefs;

public class ClearCommandTest {

    @Test
    public void execute_emptyTaskManager_success() {
        Model model = new ModelManager();
        Model expectedModel = new ModelManager();

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_nonEmptyTaskManager_success() {
        Model model = new ModelManager(getTypicalTaskManager(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalTaskManager(), new UserPrefs());
        expectedModel.setTaskManager(new TaskManager());

        assertCommandSuccess(new ClearCommand(), model, ClearCommand.MESSAGE_SUCCESS, expectedModel);
    }

}
