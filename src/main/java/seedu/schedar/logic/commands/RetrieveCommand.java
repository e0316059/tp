package seedu.schedar.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.schedar.logic.CommandHistory;
import seedu.schedar.logic.commands.exceptions.CommandException;
import seedu.schedar.model.Model;

/**
 * Retrieves the recently deleted Task.
 */

public class RetrieveCommand extends Command {

    public static final String COMMAND_WORD = "retrieve";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Retrieves the recent deleted task to the task manager. ";

    public static final String MESSAGE_SUCCESS = "The most recent deleted task has been added to the task list.";

    /**
     * Creates an AddTodoCommand to add the specified {@code Task}
     */
    public RetrieveCommand() {
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);

        model.retrieveRecentDeletedTask();
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RetrieveCommand); // instanceof handles nulls
    }
}
