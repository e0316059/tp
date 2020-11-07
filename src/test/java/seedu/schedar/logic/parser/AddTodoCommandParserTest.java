package seedu.schedar.logic.parser;

import static seedu.schedar.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.schedar.logic.commands.CommandTestUtil.DESCRIPTION_DESC_LECTURE;
import static seedu.schedar.logic.commands.CommandTestUtil.DESCRIPTION_DESC_PROJECT;
import static seedu.schedar.logic.commands.CommandTestUtil.INVALID_DESCRIPTION_DESC;
import static seedu.schedar.logic.commands.CommandTestUtil.INVALID_PRIORITY_DESC;
import static seedu.schedar.logic.commands.CommandTestUtil.INVALID_TAG_DESC;
import static seedu.schedar.logic.commands.CommandTestUtil.INVALID_TITLE_DESC;
import static seedu.schedar.logic.commands.CommandTestUtil.PREAMBLE_NON_EMPTY;
import static seedu.schedar.logic.commands.CommandTestUtil.PREAMBLE_WHITESPACE;
import static seedu.schedar.logic.commands.CommandTestUtil.PRIORITY_DESC_LECTURE;
import static seedu.schedar.logic.commands.CommandTestUtil.PRIORITY_DESC_PROJECT;
import static seedu.schedar.logic.commands.CommandTestUtil.TAG_DESC_LECTURE;
import static seedu.schedar.logic.commands.CommandTestUtil.TAG_DESC_PROJECT;
import static seedu.schedar.logic.commands.CommandTestUtil.TITLE_DESC_LECTURE;
import static seedu.schedar.logic.commands.CommandTestUtil.TITLE_DESC_PROJECT;
import static seedu.schedar.logic.commands.CommandTestUtil.VALID_DESCRIPTION_PROJECT;
import static seedu.schedar.logic.commands.CommandTestUtil.VALID_PRIORITY_PROJECT;
import static seedu.schedar.logic.commands.CommandTestUtil.VALID_TAG_LECTURE;
import static seedu.schedar.logic.commands.CommandTestUtil.VALID_TAG_PROJECT;
import static seedu.schedar.logic.commands.CommandTestUtil.VALID_TITLE_PROJECT;
import static seedu.schedar.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.schedar.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.schedar.testutil.TypicalTasks.TODO_PROJECT;

import org.junit.jupiter.api.Test;

import seedu.schedar.logic.commands.AddTodoCommand;
import seedu.schedar.model.tag.Tag;
import seedu.schedar.model.task.Description;
import seedu.schedar.model.task.Priority;
import seedu.schedar.model.task.Title;
import seedu.schedar.model.task.ToDo;
import seedu.schedar.testutil.ToDoBuilder;

public class AddTodoCommandParserTest {
    private AddTodoCommandParser parser = new AddTodoCommandParser();

    @Test
    public void parse_allFieldsPresent_success() {
        ToDo expectedTodo = new ToDoBuilder(TODO_PROJECT).withTags(VALID_TAG_PROJECT).build();

        // whitespace only preamble
        assertParseSuccess(parser, PREAMBLE_WHITESPACE + TITLE_DESC_PROJECT + DESCRIPTION_DESC_PROJECT
                + PRIORITY_DESC_PROJECT + TAG_DESC_PROJECT, new AddTodoCommand(expectedTodo));

        // multiple titles - last title accepted
        assertParseSuccess(parser, TITLE_DESC_LECTURE + TITLE_DESC_PROJECT + DESCRIPTION_DESC_PROJECT
                + PRIORITY_DESC_PROJECT + TAG_DESC_PROJECT, new AddTodoCommand(expectedTodo));

        // multiple descriptions - last description accepted
        assertParseSuccess(parser, TITLE_DESC_PROJECT + DESCRIPTION_DESC_LECTURE + DESCRIPTION_DESC_PROJECT
                + PRIORITY_DESC_PROJECT + TAG_DESC_PROJECT, new AddTodoCommand(expectedTodo));

        // multiple priorities - last priority accepted
        assertParseSuccess(parser, TITLE_DESC_PROJECT + DESCRIPTION_DESC_PROJECT + PRIORITY_DESC_LECTURE
                + PRIORITY_DESC_PROJECT + TAG_DESC_PROJECT, new AddTodoCommand(expectedTodo));

        // multiple tags - all accepted
        ToDo expectedTodoWithMultipleTags = new ToDoBuilder(TODO_PROJECT).withTags(VALID_TAG_PROJECT, VALID_TAG_LECTURE)
                .build();
        assertParseSuccess(parser, TITLE_DESC_PROJECT + DESCRIPTION_DESC_PROJECT + PRIORITY_DESC_PROJECT
                + TAG_DESC_PROJECT + TAG_DESC_LECTURE, new AddTodoCommand(expectedTodoWithMultipleTags));
    }

    // TODO: Add tests for optional fields: parse_optionalFieldsMissing_success()

    @Test
    public void parse_compulsoryFieldMissing_failure() {
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTodoCommand.MESSAGE_USAGE);

        // missing title prefix
        assertParseFailure(parser, VALID_TITLE_PROJECT + DESCRIPTION_DESC_PROJECT + PRIORITY_DESC_PROJECT
                + TAG_DESC_PROJECT, expectedMessage);

        // missing description prefix
        // description is optional as of v1.3

        // missing priority prefix
        // priority is optional as of v1.3

        // all prefixes missing
        assertParseFailure(parser, VALID_TITLE_PROJECT + VALID_DESCRIPTION_PROJECT + VALID_PRIORITY_PROJECT
                + VALID_TAG_PROJECT, expectedMessage);
    }

    @Test
    public void parse_invalidValue_failure() {
        // invalid title
        assertParseFailure(parser, INVALID_TITLE_DESC + DESCRIPTION_DESC_PROJECT + PRIORITY_DESC_PROJECT
                + TAG_DESC_PROJECT, Title.MESSAGE_CONSTRAINTS);

        // invalid description
        assertParseFailure(parser, TITLE_DESC_PROJECT + INVALID_DESCRIPTION_DESC + PRIORITY_DESC_PROJECT
                + TAG_DESC_PROJECT, Description.MESSAGE_CONSTRAINTS);

        // invalid priority
        assertParseFailure(parser, TITLE_DESC_PROJECT + DESCRIPTION_DESC_PROJECT + INVALID_PRIORITY_DESC
                + TAG_DESC_PROJECT, Priority.MESSAGE_CONSTRAINTS);

        // invalid tag
        assertParseFailure(parser, TITLE_DESC_PROJECT + DESCRIPTION_DESC_PROJECT + PRIORITY_DESC_PROJECT
                + INVALID_TAG_DESC, Tag.MESSAGE_CONSTRAINTS);

        // two invalid values, only first value reported
        assertParseFailure(parser, INVALID_TITLE_DESC + INVALID_PRIORITY_DESC
                + TAG_DESC_PROJECT, Title.MESSAGE_CONSTRAINTS);

        // non-empty preamble
        assertParseFailure(parser, PREAMBLE_NON_EMPTY + TITLE_DESC_PROJECT + DESCRIPTION_DESC_PROJECT
                + PRIORITY_DESC_PROJECT + TAG_DESC_PROJECT,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddTodoCommand.MESSAGE_USAGE));
    }
}
