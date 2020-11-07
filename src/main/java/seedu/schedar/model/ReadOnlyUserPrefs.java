package seedu.schedar.model;

import java.nio.file.Path;

import seedu.schedar.commons.core.GuiSettings;

/**
 * Unmodifiable view of user prefs.
 */
public interface ReadOnlyUserPrefs {

    GuiSettings getGuiSettings();

    Path getTaskManagerFilePath();
}
