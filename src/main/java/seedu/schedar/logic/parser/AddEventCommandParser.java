package seedu.schedar.logic.parser;

import static seedu.schedar.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.schedar.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.schedar.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.schedar.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.schedar.logic.parser.CliSyntax.PREFIX_TASK_DATE;
import static seedu.schedar.logic.parser.CliSyntax.PREFIX_TASK_TIME;
import static seedu.schedar.logic.parser.CliSyntax.PREFIX_TITLE;
import static seedu.schedar.logic.parser.ParserUtil.arePrefixesPresent;

import java.util.Set;

import seedu.schedar.logic.commands.AddEventCommand;
import seedu.schedar.logic.parser.exceptions.ParseException;
import seedu.schedar.model.tag.Tag;
import seedu.schedar.model.task.Description;
import seedu.schedar.model.task.Event;
import seedu.schedar.model.task.Priority;
import seedu.schedar.model.task.TaskDate;
import seedu.schedar.model.task.TaskTime;
import seedu.schedar.model.task.Title;

/**
 * Parses input arguments and creates a new AddEventCommand object
 */
public class AddEventCommandParser implements Parser<AddEventCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AddEventCommand
     * and returns a AddEventCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public AddEventCommand parse(String args) throws ParseException {
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TITLE, PREFIX_DESCRIPTION, PREFIX_PRIORITY,
                        PREFIX_TASK_DATE, PREFIX_TASK_TIME, PREFIX_TAG);

        if (!arePrefixesPresent(argMultimap, PREFIX_TITLE, PREFIX_TASK_DATE, PREFIX_TASK_TIME)
                || !argMultimap.getPreamble().isEmpty()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddEventCommand.MESSAGE_USAGE));
        }

        Title title = ParserUtil.parseTitle(argMultimap.getValue(PREFIX_TITLE).get());
        Description description = ParserUtil.parseDescription("No Description");
        if (arePrefixesPresent(argMultimap, PREFIX_DESCRIPTION)) {
            description = ParserUtil.parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get());
        }
        Priority priority = ParserUtil.parsePriority("No Priority Assigned");
        if (arePrefixesPresent(argMultimap, PREFIX_PRIORITY)) {
            priority = ParserUtil.parsePriority(argMultimap.getValue(PREFIX_PRIORITY).get());
        }
        TaskDate eventDate = ParserUtil.parseTaskDate(argMultimap.getValue(PREFIX_TASK_DATE).get());
        TaskTime eventTime = ParserUtil.parseTaskTime(argMultimap.getValue(PREFIX_TASK_TIME).get());
        Set<Tag> tagList = ParserUtil.parseTags(argMultimap.getAllValues(PREFIX_TAG));

        Event event = new Event(title, description, priority, eventDate, eventTime, tagList);

        return new AddEventCommand(event);
    }
}
