package seedu.schedar.testutil;

import static seedu.schedar.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.schedar.logic.parser.CliSyntax.PREFIX_PRIORITY;
import static seedu.schedar.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.schedar.logic.parser.CliSyntax.PREFIX_TITLE;

import seedu.schedar.logic.commands.AddTodoCommand;
import seedu.schedar.model.task.ToDo;

/**
 * A utility class for ToDo.
 */
public class ToDoUtil {

    /**
     * Returns an add command string for adding the {@code ToDo}.
     */
    public static String getTodoCommand(ToDo toDo) {
        return AddTodoCommand.COMMAND_WORD + " " + getTodoDetails(toDo);
    }

    /**
     * Returns the part of command string for the given {@code ToDo}'s details.
     */
    public static String getTodoDetails(ToDo toDo) {
        StringBuilder sb = new StringBuilder();
        sb.append(PREFIX_TITLE + toDo.getTitle().title + " ");
        sb.append(PREFIX_DESCRIPTION + toDo.getDescription().value + " ");
        sb.append(PREFIX_PRIORITY + toDo.getPriority().toString() + " ");
        toDo.getTags().stream().forEach(
            s -> sb.append(PREFIX_TAG + s.tagName + " ")
        );
        return sb.toString();
    }
}
