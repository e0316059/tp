package seedu.schedar.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.schedar.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.schedar.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.schedar.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.schedar.logic.parser.CliSyntax.PREFIX_TASK_DATE;
import static seedu.schedar.logic.parser.CliSyntax.PREFIX_TASK_TIME;
import static seedu.schedar.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.schedar.testutil.Assert.assertThrows;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.schedar.commons.core.index.Index;
import seedu.schedar.logic.CommandHistory;
import seedu.schedar.logic.commands.exceptions.CommandException;
import seedu.schedar.model.Model;
import seedu.schedar.model.TaskManager;
import seedu.schedar.model.task.Task;
import seedu.schedar.model.task.TitleOrDescriptionContainsKeywordsPredicate;
import seedu.schedar.testutil.EditEventDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_TITLE_PROJECT = "Do tP tasks";
    public static final String VALID_TITLE_LECTURE = "Attend lecture";
    public static final String VALID_DESCRIPTION_PROJECT = "Complete tP tasks for current week";
    public static final String VALID_DESCRIPTION_LECTURE = "Weekly Zoom lecture for CS2103";
    public static final String VALID_PRIORITY_PROJECT = "High";
    public static final String VALID_PRIORITY_LECTURE = "Medium";
    public static final String VALID_TASKDATE_PROJECT = "2020-12-31";
    public static final String VALID_TASKDATE_LECTURE = "2020-10-31";
    public static final String VALID_TASKTIME_PROJECT = "16:00";
    public static final String VALID_TASKTIME_LECTURE = "10:00";
    public static final String VALID_TAG_PROJECT = "project";
    public static final String VALID_TAG_LECTURE = "lecture";

    public static final String TITLE_DESC_PROJECT = " " + PREFIX_TITLE + VALID_TITLE_PROJECT;
    public static final String TITLE_DESC_LECTURE = " " + PREFIX_TITLE + VALID_TITLE_LECTURE;
    public static final String DESCRIPTION_DESC_PROJECT = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_PROJECT;
    public static final String DESCRIPTION_DESC_LECTURE = " " + PREFIX_DESCRIPTION + VALID_DESCRIPTION_LECTURE;
    public static final String PRIORITY_DESC_PROJECT = " " + PREFIX_PRIORITY + VALID_PRIORITY_PROJECT;
    public static final String PRIORITY_DESC_LECTURE = " " + PREFIX_PRIORITY + VALID_PRIORITY_LECTURE;
    public static final String TASKDATE_DESC_PROJECT = " " + PREFIX_TASK_DATE + VALID_TASKDATE_PROJECT;
    public static final String TASKDATE_DESC_LECTURE = " " + PREFIX_TASK_DATE + VALID_TASKDATE_LECTURE;
    public static final String TASKTIME_DESC_PROJECT = " " + PREFIX_TASK_TIME + VALID_TASKTIME_PROJECT;
    public static final String TASKTIME_DESC_LECTURE = " " + PREFIX_TASK_TIME + VALID_TASKTIME_LECTURE;
    public static final String TAG_DESC_PROJECT = " " + PREFIX_TAG + VALID_TAG_PROJECT;
    public static final String TAG_DESC_LECTURE = " " + PREFIX_TAG + VALID_TAG_LECTURE;

    public static final String INVALID_TITLE_DESC = " " + PREFIX_TITLE + "Do your homework!";
    public static final String INVALID_DESCRIPTION_DESC = " " + PREFIX_DESCRIPTION;
    public static final String INVALID_PRIORITY_DESC = " " + PREFIX_PRIORITY + "Nope";
    public static final String INVALID_TASKDATE_DESC = " " + PREFIX_TASK_DATE + "2020/12/31";
    public static final String INVALID_TASKTIME_DESC = " " + PREFIX_TASK_TIME + "12 NOON";
    public static final String INVALID_TAG_DESC = " " + PREFIX_TAG + "lect*";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final EditEventCommand.EditEventDescriptor DESC_PROJECT;
    public static final EditEventCommand.EditEventDescriptor DESC_LECTURE;

    static {
        DESC_PROJECT = new EditEventDescriptorBuilder().withTitle(VALID_TITLE_PROJECT)
                .withDescription(VALID_DESCRIPTION_PROJECT).withPriority(VALID_PRIORITY_PROJECT)
                .withEventDate(VALID_TASKDATE_PROJECT).withEventTime(VALID_TASKTIME_PROJECT)
                .withTags(VALID_TAG_PROJECT).build();
        DESC_LECTURE = new EditEventDescriptorBuilder().withTitle(VALID_TITLE_LECTURE)
                .withDescription(VALID_DESCRIPTION_LECTURE).withPriority(VALID_PRIORITY_LECTURE)
                .withEventDate(VALID_TASKDATE_LECTURE).withEventTime(VALID_TASKTIME_LECTURE)
                .withTags(VALID_TAG_LECTURE).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel} <br>
     * - the {@code actualCommandHistory} remains unchanged.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandHistory actualCommandHistory,
                                            CommandResult expectedCommandResult, Model expectedModel) {
        CommandHistory expectedCommandHistory = new CommandHistory(actualCommandHistory);
        try {
            CommandResult result = command.execute(actualModel, actualCommandHistory);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
            assertEquals(expectedCommandHistory, actualCommandHistory);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandHistory, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandHistory actualCommandHistory,
                                            String expectedMessage, Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, actualCommandHistory, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the task manager, filtered task list and selected task in {@code actualModel} remain unchanged
     * - {@code actualCommandHistory} remains unchanged.
     */
    public static void assertCommandFailure(Command command, Model actualModel, CommandHistory actualCommandHistory,
                                            String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        TaskManager expectedTaskManager = new TaskManager(actualModel.getTaskManager());
        List<Task> expectedFilteredList = new ArrayList<>(actualModel.getFilteredTaskList());

        CommandHistory expectedCommandHistory = new CommandHistory(actualCommandHistory);

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel, actualCommandHistory));
        assertEquals(expectedTaskManager, actualModel.getTaskManager());
        assertEquals(expectedFilteredList, actualModel.getFilteredTaskList());
        assertEquals(expectedCommandHistory, actualCommandHistory);
    }
    /**
     * Updates {@code model}'s filtered list to show only the task at the given {@code targetIndex} in the
     * {@code model}'s task manager.
     */
    public static void showTaskAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredTaskList().size());

        Task task = model.getFilteredTaskList().get(targetIndex.getZeroBased());
        final String[] splitTitle = task.getTitle().title.split("\\s+");
        model.updateFilteredTaskList(new TitleOrDescriptionContainsKeywordsPredicate(Arrays.asList(splitTitle[0])));

        assertEquals(1, model.getFilteredTaskList().size());
    }

    /**
     * Deletes the first task in {@code model}'s filtered list from {@code model}'s task manager.
     */
    public static void deleteFirstTask(Model model) {
        Task firstTask = model.getFilteredTaskList().get(0);
        model.deleteTask(firstTask);
        model.commitTaskManager();
    }

}
