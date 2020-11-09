package seedu.schedar.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.schedar.logic.CommandHistory;
import seedu.schedar.model.Model;

/**
 * Counts all tasks in the task manager and displays the total number to the user.
 */
public class CountCommand extends Command {

    public static final String COMMAND_WORD = "count";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Displays the total number of tasks in ScheDar.\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Total number of tasks are: ";

    private static long total;

    @Override
    public CommandResult execute(Model model, CommandHistory commandHistory) {
        requireNonNull(model);

        total = model.count();
        return new CommandResult(MESSAGE_SUCCESS + total);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof CountCommand); // instanceof handles nulls
    }
}
