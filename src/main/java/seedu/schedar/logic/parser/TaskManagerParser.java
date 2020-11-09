package seedu.schedar.logic.parser;

import static seedu.schedar.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.schedar.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import seedu.schedar.logic.commands.AddDeadlineCommand;
import seedu.schedar.logic.commands.AddEventCommand;
import seedu.schedar.logic.commands.AddTodoCommand;
import seedu.schedar.logic.commands.Command;
import seedu.schedar.logic.commands.CountCommand;
import seedu.schedar.logic.commands.DeleteCommand;
import seedu.schedar.logic.commands.DoneCommand;
import seedu.schedar.logic.commands.EditDeadlineCommand;
import seedu.schedar.logic.commands.EditEventCommand;
import seedu.schedar.logic.commands.EditTodoCommand;
import seedu.schedar.logic.commands.ExitCommand;
import seedu.schedar.logic.commands.FindCommand;
import seedu.schedar.logic.commands.HelpCommand;
import seedu.schedar.logic.commands.ListCommand;
import seedu.schedar.logic.commands.RedoCommand;
import seedu.schedar.logic.commands.RetrieveCommand;
import seedu.schedar.logic.commands.SortCommand;
import seedu.schedar.logic.commands.UndoCommand;
import seedu.schedar.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class TaskManagerParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");
        switch (commandWord) {

        case AddTodoCommand.COMMAND_WORD:
            return new AddTodoCommandParser().parse(arguments);

        case AddDeadlineCommand.COMMAND_WORD:
            return new AddDeadlineCommandParser().parse(arguments);

        case AddEventCommand.COMMAND_WORD:
            return new AddEventCommandParser().parse(arguments);

        case CountCommand.COMMAND_WORD:
            return new CountCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case DoneCommand.COMMAND_WORD:
            return new DoneCommandParser().parse(arguments);

        case EditDeadlineCommand.COMMAND_WORD:
            return new EditDeadlineCommandParser().parse(arguments);

        case EditEventCommand.COMMAND_WORD:
            return new EditEventCommandParser().parse(arguments);

        case EditTodoCommand.COMMAND_WORD:
            return new EditTodoCommandParser().parse(arguments);

        case HelpCommand.COMMAND_WORD:
            return new HelpCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommandParser().parse(arguments);

        case ExitCommand.COMMAND_WORD:
            return new ExitCommandParser().parse(arguments);

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case RedoCommand.COMMAND_WORD:
            return new RedoCommandParser().parse(arguments);

        case RetrieveCommand.COMMAND_WORD:
            return new RetrieveCommandParser().parse(arguments);

        case SortCommand.COMMAND_WORD:
            return new SortCommandParser().parse(arguments);

        case UndoCommand.COMMAND_WORD:
            return new UndoCommandParser().parse(arguments);

        default:
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);

        }
    }
}
