package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.task.TaskDateComparator;

public class SortCommand extends Command {
    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sort all tasks based on their time.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD;

    public static final String MESSAGE_DELETE_TASK_SUCCESS = "All results sorted";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortTask(new TaskDateComparator());
        return new CommandResult(MESSAGE_DELETE_TASK_SUCCESS);
    }
}
