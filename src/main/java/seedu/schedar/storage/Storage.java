package seedu.schedar.storage;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;

import seedu.schedar.commons.exceptions.DataConversionException;
import seedu.schedar.model.ReadOnlyTaskManager;
import seedu.schedar.model.ReadOnlyUserPrefs;
import seedu.schedar.model.UserPrefs;

/**
 * API of the Storage component
 */
public interface Storage extends TaskManagerStorage, UserPrefsStorage {

    @Override
    Optional<UserPrefs> readUserPrefs() throws DataConversionException, IOException;

    @Override
    void saveUserPrefs(ReadOnlyUserPrefs userPrefs) throws IOException;

    @Override
    Path getTaskManagerFilePath();

    @Override
    Optional<ReadOnlyTaskManager> readTaskManager() throws DataConversionException, IOException;

    @Override
    void saveTaskManager(ReadOnlyTaskManager taskManager) throws IOException;

}
