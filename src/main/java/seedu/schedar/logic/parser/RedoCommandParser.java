package seedu.schedar.logic.parser;

import seedu.schedar.logic.commands.RedoCommand;

public class RedoCommandParser implements Parser<RedoCommand> {
    /**
     * Returns a RedoCommand object for execution.
     */
    public RedoCommand parse(String args) {
        return new RedoCommand();
    }
}
