package seedu.schedar.logic.parser;

import seedu.schedar.logic.commands.ExitCommand;

public class ExitCommandParser {
    /**
     * Returns a ExitCommand object for execution.
     */
    public ExitCommand parse(String args) {
        return new ExitCommand();
    }
}
