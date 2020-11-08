package seedu.schedar.logic.parser;

import seedu.schedar.logic.commands.CountCommand;

public class CountCommandParser implements Parser<CountCommand> {
    /**
     * Returns a CountCommand object for execution.
     */
    public CountCommand parse(String args) {
        return new CountCommand();
    }
}
