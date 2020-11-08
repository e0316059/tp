package seedu.schedar.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.schedar.logic.CommandHistory;
import seedu.schedar.logic.commands.exceptions.CommandException;
import seedu.schedar.model.Model;
import seedu.schedar.model.task.Task;

/**
 * Retrieves the recently deleted Task.
 * Only one task which is the most recently deleted can be retrieved.
 */

public class RetrieveCommand extends Command {

    public static final String COMMAND_WORD = "retrieve";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Retrieves the recent deleted task to the task manager. ";

    public static final String MESSAGE_SUCCESS = "The most recent deleted task has been added to the task list.";

    public static final String MESSAGE_CANNOT_RETRIEVE = "There is no recent deleted task!";

    public static final String MESSAGE_DUPLICATE_TASK = "Retrieve would result in duplicate tasks!";

    /**
     * Creates a RetrieveCommand to retrieve the most recently deleted task.
     */
    public RetrieveCommand() {
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        if (model.getRecentDeletedTask() == null) {
            throw new CommandException(MESSAGE_CANNOT_RETRIEVE);
        }

        Task recentlyDeleted = model.getRecentDeletedTask();
        if (model.hasTask(recentlyDeleted)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }
        model.retrieveRecentDeletedTask();

        return new CommandResult(MESSAGE_SUCCESS + recentlyDeleted);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RetrieveCommand); // instanceof handles nulls
    }
}
