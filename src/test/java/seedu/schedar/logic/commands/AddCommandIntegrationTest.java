package seedu.schedar.logic.commands;

import static seedu.schedar.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.schedar.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.schedar.testutil.TypicalTasks.getTypicalTaskManager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.schedar.logic.CommandHistory;
import seedu.schedar.model.Model;
import seedu.schedar.model.ModelManager;
import seedu.schedar.model.UserPrefs;
import seedu.schedar.model.task.Event;
import seedu.schedar.model.task.Task;
import seedu.schedar.model.task.ToDo;
import seedu.schedar.testutil.ToDoBuilder;

/**
 * Contains integration tests (interaction with the Model) for {@code AddCommand}.
 */
public class AddCommandIntegrationTest {

    private Model model;
    private CommandHistory commandHistory = new CommandHistory();

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalTaskManager(), new UserPrefs());
    }

    @Test
    public void execute_newTask_success() {
        ToDo validTodo = new ToDoBuilder().build();

        Model expectedModel = new ModelManager(model.getTaskManager(), new UserPrefs());
        expectedModel.addTask(validTodo);

        assertCommandSuccess(new AddTodoCommand(validTodo), model, commandHistory,
                String.format(AddTodoCommand.MESSAGE_SUCCESS, validTodo), expectedModel);
    }

    @Test
    public void execute_duplicateTask_throwsCommandException() {
        Task taskInList = model.getTaskManager().getTaskList().get(0);

        // First task in list is of Event Type
        assertCommandFailure(new AddEventCommand((Event) taskInList), model, commandHistory,
                AddEventCommand.MESSAGE_DUPLICATE_TASK);
    }

}
