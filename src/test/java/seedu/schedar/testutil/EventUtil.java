package seedu.schedar.testutil;

import static seedu.schedar.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.schedar.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.schedar.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.schedar.logic.parser.CliSyntax.PREFIX_TASK_DATE;
import static seedu.schedar.logic.parser.CliSyntax.PREFIX_TASK_TIME;
import static seedu.schedar.logic.parser.CliSyntax.PREFIX_TITLE;

import seedu.schedar.logic.commands.AddEventCommand;
import seedu.schedar.model.task.Event;

/**
 * A utility class for Event.
 */
public class EventUtil {

    /**
     * Returns an add command string for adding the {@code event}.
     */
    public static String getEventCommand(Event event) {
        return AddEventCommand.COMMAND_WORD + " " + getEventDetails(event);
    }

    /**
     * Returns the part of command string for the given {@code event}'s details.
     */
    public static String getEventDetails(Event event) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_TITLE + event.getTitle().title + " ");
        sb.append(PREFIX_DESCRIPTION + event.getDescription().value + " ");
        sb.append(PREFIX_PRIORITY + event.getPriority().level.toString() + " ");
        sb.append(PREFIX_TASK_DATE + event.getEventDate().toString() + " ");
        sb.append(PREFIX_TASK_TIME + event.getEventTime().toString() + " ");
        event.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }
}
