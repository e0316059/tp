package seedu.schedar.logic;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.schedar.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.schedar.logic.commands.CommandTestUtil.DESCRIPTION_DESC_PROJECT;
import static seedu.schedar.logic.commands.CommandTestUtil.PRIORITY_DESC_PROJECT;
import static seedu.schedar.logic.commands.CommandTestUtil.TAG_DESC_PROJECT;
import static seedu.schedar.logic.commands.CommandTestUtil.TITLE_DESC_PROJECT;
import static seedu.schedar.logic.commands.CommandTestUtil.VALID_TAG_PROJECT;
import static seedu.schedar.testutil.Assert.assertThrows;
import static seedu.schedar.testutil.TypicalTasks.TODO_PROJECT;

import java.io.IOException;
import java.nio.file.Path;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import seedu.schedar.logic.commands.AddTodoCommand;
import seedu.schedar.logic.commands.CommandResult;
import seedu.schedar.logic.commands.DeleteCommand;
import seedu.schedar.logic.commands.ListCommand;
import seedu.schedar.logic.commands.exceptions.CommandException;
import seedu.schedar.logic.parser.exceptions.ParseException;
import seedu.schedar.model.Model;
import seedu.schedar.model.ModelManager;
import seedu.schedar.model.ReadOnlyTaskManager;
import seedu.schedar.model.UserPrefs;
import seedu.schedar.model.task.ToDo;
import seedu.schedar.storage.JsonTaskManagerStorage;
import seedu.schedar.storage.JsonUserPrefsStorage;
import seedu.schedar.storage.StorageManager;
import seedu.schedar.testutil.ToDoBuilder;

public class LogicManagerTest {
    private static final IOException DUMMY_IO_EXCEPTION = new IOException("dummy exception");

    @TempDir
    public Path temporaryFolder;

    private Model model = new ModelManager();
    private Logic logic;

    @BeforeEach
    public void setUp() {
        JsonTaskManagerStorage taskManagerStorage =
                new JsonTaskManagerStorage(temporaryFolder.resolve("taskManager.json"));
        JsonUserPrefsStorage userPrefsStorage = new JsonUserPrefsStorage(temporaryFolder.resolve("userPrefs.json"));
        StorageManager storage = new StorageManager(taskManagerStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);
    }

    @Test
    public void execute_invalidCommandFormat_throwsParseException() {
        String invalidCommand = "uicfhmowqewca";
        assertParseException(invalidCommand, MESSAGE_UNKNOWN_COMMAND);
    }

    @Test
    public void execute_commandExecutionError_throwsCommandException() {
        String deleteCommand = "delete 9";
        // Update universal invalid index message
        assertCommandException(deleteCommand, DeleteCommand.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validCommand_success() throws Exception {
        String listCommand = ListCommand.COMMAND_WORD;
        assertCommandSuccess(listCommand, ListCommand.MESSAGE_SUCCESS, model);
    }

    @Test
    public void execute_storageThrowsIoException_throwsCommandException() {
        // Setup LogicManager with JsonTaskManagerIoExceptionThrowingStub
        JsonTaskManagerStorage taskManagerStorage =
                new JsonTaskManagerIoExceptionThrowingStub(temporaryFolder.resolve("ioExceptionTaskManager.json"));

        JsonUserPrefsStorage userPrefsStorage =
                new JsonUserPrefsStorage(temporaryFolder.resolve("ioExceptionUserPrefs.json"));
        StorageManager storage = new StorageManager(taskManagerStorage, userPrefsStorage);
        logic = new LogicManager(model, storage);

        // Execute add command
        String addTodoCommand = AddTodoCommand.COMMAND_WORD + TITLE_DESC_PROJECT + DESCRIPTION_DESC_PROJECT
                + PRIORITY_DESC_PROJECT + TAG_DESC_PROJECT;
        ToDo expectedTodo = new ToDoBuilder(TODO_PROJECT).withTags(VALID_TAG_PROJECT).build();
        ModelManager expectedModel = new ModelManager();
        expectedModel.addTask(expectedTodo);
        String expectedMessage = LogicManager.FILE_OPS_ERROR_MESSAGE + DUMMY_IO_EXCEPTION;
        assertCommandFailure(addTodoCommand, CommandException.class, expectedMessage, expectedModel);
    }

    @Test
    public void getFilteredPersonList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, () -> logic.getFilteredTaskList().remove(0));
    }

    /**
     * Executes the command and confirms that
     * - no exceptions are thrown <br>
     * - the feedback message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandSuccess(String inputCommand, String expectedMessage,
            Model expectedModel) throws CommandException, ParseException {
        CommandResult result = logic.execute(inputCommand);
        assertEquals(expectedMessage, result.getFeedbackToUser());
        assertEquals(expectedModel, model);
    }

    /**
     * Executes the command, confirms that a ParseException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertParseException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, ParseException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that a CommandException is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandException(String inputCommand, String expectedMessage) {
        assertCommandFailure(inputCommand, CommandException.class, expectedMessage);
    }

    /**
     * Executes the command, confirms that the exception is thrown and that the result message is correct.
     * @see #assertCommandFailure(String, Class, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage) {
        Model expectedModel = new ModelManager(model.getTaskManager(), new UserPrefs());
        assertCommandFailure(inputCommand, expectedException, expectedMessage, expectedModel);
    }

    /**
     * Executes the command and confirms that
     * - the {@code expectedException} is thrown <br>
     * - the resulting error message is equal to {@code expectedMessage} <br>
     * - the internal model manager state is the same as that in {@code expectedModel} <br>
     * @see #assertCommandSuccess(String, String, Model)
     */
    private void assertCommandFailure(String inputCommand, Class<? extends Throwable> expectedException,
            String expectedMessage, Model expectedModel) {
        assertThrows(expectedException, expectedMessage, () -> logic.execute(inputCommand));
        assertEquals(expectedModel, model);
    }

    /**
     * A stub class to throw an {@code IOException} when the save method is called.
     */
    private static class JsonTaskManagerIoExceptionThrowingStub extends JsonTaskManagerStorage {
        private JsonTaskManagerIoExceptionThrowingStub(Path filePath) {
            super(filePath);
        }

        @Override
        public void saveTaskManager(ReadOnlyTaskManager taskManager, Path filePath) throws IOException {
            throw DUMMY_IO_EXCEPTION;
        }
    }
}
