package seedu.schedar.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.schedar.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.schedar.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.schedar.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.schedar.logic.parser.CliSyntax.PREFIX_TASK_DATE;
import static seedu.schedar.logic.parser.CliSyntax.PREFIX_TASK_TIME;
import static seedu.schedar.logic.parser.CliSyntax.PREFIX_TITLE;

import seedu.schedar.logic.commands.exceptions.CommandException;
import seedu.schedar.model.Model;
import seedu.schedar.model.task.Event;

public class AddEventCommand extends Command {

    public static final String COMMAND_WORD = "event";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a event-type task to the task manager. "
            + "Parameters: "
            + PREFIX_TITLE + "TITLE "
            + PREFIX_DESCRIPTION + "DESCRIPTION "
            + PREFIX_PRIORITY + "PRIORITY "
            + PREFIX_TASK_DATE + "EVENT DATE "
            + PREFIX_TASK_TIME + "EVENT TIME "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_TITLE + "CS2103 party "
            + PREFIX_DESCRIPTION + "Coding Party! "
            + PREFIX_PRIORITY + "High "
            + PREFIX_TASK_DATE + "2020-10-01 "
            + PREFIX_TASK_TIME + "19:00 "
            + PREFIX_TAG + "cs2103 "
            + PREFIX_TAG + "project";

    public static final String MESSAGE_SUCCESS = "New event added: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the task manager";

    private final Event toAdd;

    /**
     * Creates an TodoCommand to add the specified {@code Task}
     */
    public AddEventCommand(Event task) {
        requireNonNull(task);
        toAdd = task;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTask(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        model.addTask(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddEventCommand // instanceof handles nulls
                && toAdd.equals(((AddEventCommand) other).toAdd));
    }
}
