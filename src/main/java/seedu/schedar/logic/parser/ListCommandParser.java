package seedu.schedar.logic.parser;

import seedu.schedar.logic.commands.ListCommand;

public class ListCommandParser {
    /**
     * Returns a ListCommand object for execution.
     */
    public ListCommand parse(String args) {
        return new ListCommand();
    }
}
