package seedu.schedar.testutil;

import static seedu.schedar.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.schedar.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.schedar.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.schedar.logic.parser.CliSyntax.PREFIX_TASK_DATE;
import static seedu.schedar.logic.parser.CliSyntax.PREFIX_TITLE;

import seedu.schedar.logic.commands.AddDeadlineCommand;
import seedu.schedar.model.task.Deadline;

/**
 * A utility class for Deadline.
 */
public class DeadlineUtil {

    /**
     * Returns an add command string for adding the {@code deadline}.
     */
    public static String getDeadlineCommand(Deadline deadline) {
        return AddDeadlineCommand.COMMAND_WORD + " " + getDeadlineDetails(deadline);
    }

    /**
     * Returns the part of command string for the given {@code deadline}'s details.
     */
    public static String getDeadlineDetails(Deadline deadline) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_TITLE + deadline.getTitle().title + " ");
        sb.append(PREFIX_DESCRIPTION + deadline.getDescription().value + " ");
        sb.append(PREFIX_PRIORITY + deadline.getPriority().level.toString() + " ");
        sb.append(PREFIX_TASK_DATE + deadline.getDeadlineDate().toString() + " ");
        deadline.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }
}
