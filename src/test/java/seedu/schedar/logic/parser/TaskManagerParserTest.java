package seedu.schedar.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.schedar.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.schedar.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.schedar.logic.commands.CommandTestUtil.VALID_TAG_PROJECT;
import static seedu.schedar.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.schedar.logic.commands.AddDeadlineCommand;
import seedu.schedar.logic.commands.AddEventCommand;
import seedu.schedar.logic.commands.AddTodoCommand;
import seedu.schedar.logic.commands.ExitCommand;
import seedu.schedar.logic.commands.HelpCommand;
import seedu.schedar.logic.commands.ListCommand;
import seedu.schedar.logic.parser.exceptions.ParseException;
import seedu.schedar.model.task.Deadline;
import seedu.schedar.model.task.Event;
import seedu.schedar.model.task.ToDo;
import seedu.schedar.testutil.DeadlineBuilder;
import seedu.schedar.testutil.DeadlineUtil;
import seedu.schedar.testutil.EventBuilder;
import seedu.schedar.testutil.EventUtil;
import seedu.schedar.testutil.ToDoBuilder;
import seedu.schedar.testutil.ToDoUtil;

public class TaskManagerParserTest {

    private final TaskManagerParser parser = new TaskManagerParser();

    @Test
    public void parseCommand_addTodo() throws Exception {
        ToDo todo = new ToDoBuilder().withTags(VALID_TAG_PROJECT).build();
        AddTodoCommand command = (AddTodoCommand) parser.parseCommand(ToDoUtil.getTodoCommand(todo));
        assertEquals(new AddTodoCommand(todo), command);
    }

    @Test
    public void parseCommand_addDeadline() throws Exception {
        Deadline deadline = new DeadlineBuilder().withTags(VALID_TAG_PROJECT).build();
        AddDeadlineCommand command =
                (AddDeadlineCommand) parser.parseCommand(DeadlineUtil.getDeadlineCommand(deadline));
        assertEquals(new AddDeadlineCommand(deadline), command);
    }

    @Test
    public void parseCommand_addEvent() throws Exception {
        Event event = new EventBuilder().withTags(VALID_TAG_PROJECT).build();
        AddEventCommand command = (AddEventCommand) parser.parseCommand(EventUtil.getEventCommand(event));
        assertEquals(new AddEventCommand(event), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " lol") instanceof ExitCommand);
    }

    // @Test
    // public void parseCommand_help() throws Exception {
    //     assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
    //     assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " lol") instanceof  HelpCommand);
    // }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " lol") instanceof ListCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
