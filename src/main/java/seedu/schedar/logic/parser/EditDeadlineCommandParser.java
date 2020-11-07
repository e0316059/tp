package seedu.schedar.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.schedar.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.schedar.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.schedar.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.schedar.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.schedar.logic.parser.CliSyntax.PREFIX_TASK_DATE;
import static seedu.schedar.logic.parser.CliSyntax.PREFIX_TITLE;

import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.Set;

import seedu.schedar.commons.core.index.Index;
import seedu.schedar.logic.commands.EditDeadlineCommand;
import seedu.schedar.logic.parser.exceptions.ParseException;
import seedu.schedar.model.tag.Tag;

/**
 * Parses input arguments and creates a new EditCommand object
 */
public class EditDeadlineCommandParser implements Parser<EditDeadlineCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the EditCommand
     * and returns an EditCommand object for execution.
     * @throws ParseException if the user input does not conform the expected format
     */
    public EditDeadlineCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap =
                ArgumentTokenizer.tokenize(args, PREFIX_TITLE, PREFIX_DESCRIPTION, PREFIX_PRIORITY,
                        PREFIX_TASK_DATE, PREFIX_TAG);

        Index index;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());
        } catch (ParseException pe) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    EditDeadlineCommand.MESSAGE_USAGE), pe);
        }

        EditDeadlineCommand.EditDeadlineDescriptor editDeadlineDescriptor =
                new EditDeadlineCommand.EditDeadlineDescriptor();
        if (argMultimap.getValue(PREFIX_TITLE).isPresent()) {
            editDeadlineDescriptor.setTitle(ParserUtil.parseTitle(argMultimap.getValue(PREFIX_TITLE).get()));
        }
        if (argMultimap.getValue(PREFIX_DESCRIPTION).isPresent()) {
            editDeadlineDescriptor.setDescription(ParserUtil
                    .parseDescription(argMultimap.getValue(PREFIX_DESCRIPTION).get()));
        }
        if (argMultimap.getValue(PREFIX_PRIORITY).isPresent()) {
            editDeadlineDescriptor.setPriority(ParserUtil.parsePriority(argMultimap.getValue(PREFIX_PRIORITY).get()));
        }
        if (argMultimap.getValue(PREFIX_TASK_DATE).isPresent()) {
            editDeadlineDescriptor.setTaskDeadline(ParserUtil
                    .parseTaskDate(argMultimap.getValue(PREFIX_TASK_DATE).get()));
        }
        parseTagsForEdit(argMultimap.getAllValues(PREFIX_TAG)).ifPresent(editDeadlineDescriptor::setTagList);

        if (!editDeadlineDescriptor.isAnyFieldEdited()) {
            throw new ParseException(EditDeadlineCommand.MESSAGE_NOT_EDITED);
        }

        return new EditDeadlineCommand(index, editDeadlineDescriptor);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>} if {@code tags} is non-empty.
     * If {@code tags} contain only one element which is an empty string, it will be parsed into a
     * {@code Set<Tag>} containing zero tags.
     */
    private Optional<Set<Tag>> parseTagsForEdit(Collection<String> tags) throws ParseException {
        assert tags != null;

        if (tags.isEmpty()) {
            return Optional.empty();
        }
        Collection<String> tagSet = tags.size() == 1 && tags.contains("") ? Collections.emptySet() : tags;
        return Optional.of(ParserUtil.parseTags(tagSet));
    }

}
