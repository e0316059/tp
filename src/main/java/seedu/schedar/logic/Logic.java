package seedu.schedar.logic;

import java.nio.file.Path;

import javafx.collections.ObservableList;
import seedu.schedar.commons.core.GuiSettings;
import seedu.schedar.logic.commands.CommandResult;
import seedu.schedar.logic.commands.exceptions.CommandException;
import seedu.schedar.logic.parser.exceptions.ParseException;
import seedu.schedar.model.ReadOnlyTaskManager;
import seedu.schedar.model.task.Task;

/**
 * API of the Logic component
 */
public interface Logic {
    /**
     * Executes the command and returns the result.
     * @param commandText The command as entered by the user.
     * @return the result of the command execution.
     * @throws CommandException If an error occurs during command execution.
     * @throws ParseException If an error occurs during parsing.
     */
    CommandResult execute(String commandText) throws CommandException, ParseException;

    /**
     * Returns the TaskManager.
     *
     * @see seedu.schedar.model.Model#getTaskManager()
     */
    ReadOnlyTaskManager getTaskManager();

    /** Returns an unmodifiable view of the filtered list of taskss */
    ObservableList<Task> getFilteredTaskList();

    /**
     * Returns the user prefs' task manager file path.
     */
    Path getTaskManagerFilePath();

    /**
     * Returns the user prefs' GUI settings.
     */
    GuiSettings getGuiSettings();

    /**
     * Set the user prefs' GUI settings.
     */
    void setGuiSettings(GuiSettings guiSettings);
}
