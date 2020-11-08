package seedu.schedar.logic.parser;

import seedu.schedar.logic.commands.UndoCommand;

public class UndoCommandParser implements Parser<UndoCommand> {
    /**
     * Returns a UndoCommand object for execution.
     */
    public UndoCommand parse(String args) {
        return new UndoCommand();
    }
}
