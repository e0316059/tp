package seedu.schedar.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.schedar.logic.CommandHistory;
import seedu.schedar.logic.commands.exceptions.CommandException;
import seedu.schedar.model.Model;

/**
 * Reverts the {@code model}'s task manager to its previously undone state.
 */
public class RedoCommand extends Command {

    public static final String COMMAND_WORD = "redo";
    public static final String MESSAGE_SUCCESS = "Redo success!";
    public static final String MESSAGE_FAILURE = "No more commands to redo!";

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (!model.canRedoTaskManager()) {
            throw new CommandException(MESSAGE_FAILURE);
        }

        model.redoTaskManager();
        model.updateFilteredTaskList(Model.PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}

