package seedu.schedar.logic.commands;

import seedu.schedar.model.Model;
import seedu.schedar.ui.UiManager;

/**
 * Format full help instructions for every command for display.
 */
public class HelpCommand extends Command {

    public static final String COMMAND_WORD = "help";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows program usage instructions.\n"
            + "Example: " + COMMAND_WORD;

    public static final String SHOWING_HELP_MESSAGE = "Opened help window.";

    private static String commandDescription = "";

    public HelpCommand() {}

    @Override
    public CommandResult execute(Model model) {
        UiManager.setCommandDescription(commandDescription);
        return new CommandResult(SHOWING_HELP_MESSAGE, true, false);
    }

    public String getCommandWord () {
        return commandDescription;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof HelpCommand)) {
            return false;
        }

        return true;
    }
}
