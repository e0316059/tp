package seedu.schedar.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.schedar.model.Model;
import seedu.schedar.model.task.TitleOrDescriptionContainsKeywordsPredicate;

/**
 * Finds and lists all tasks in ScheDar whose title contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FindCommand extends Command {

    public static final String COMMAND_WORD = "find";

    public static final String MESSAGE_SUCCESS = "Finished searching. %1$d tasks listed!";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all tasks in which contains any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " cs2103";

    private final TitleOrDescriptionContainsKeywordsPredicate predicate;

    public FindCommand(TitleOrDescriptionContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredTaskList(predicate);
        return new CommandResult(
                String.format(MESSAGE_SUCCESS, model.getFilteredTaskList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FindCommand // instanceof handles nulls
                && predicate.equals(((FindCommand) other).predicate)); // state check
    }
}
