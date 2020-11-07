package seedu.schedar.logic.commands;

import static seedu.schedar.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.schedar.logic.commands.CommandTestUtil.showPersonAtIndex;
import static seedu.schedar.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.schedar.testutil.TypicalTasks.getTypicalTaskManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.schedar.model.Model;
import seedu.schedar.model.ModelManager;
import seedu.schedar.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for ListCommand.
 */
public class ListCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTaskManager(), new UserPrefs());
        expectedModel = new ModelManager(model.getTaskManager(), new UserPrefs());
    }

    @Test
    public void execute_listIsNotFiltered_showsSameList() {
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }

    @Test
    public void execute_listIsFiltered_showsEverything() {
        showPersonAtIndex(model, INDEX_FIRST_TASK);
        assertCommandSuccess(new ListCommand(), model, ListCommand.MESSAGE_SUCCESS, expectedModel);
    }
}
