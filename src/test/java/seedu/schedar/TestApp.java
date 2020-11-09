package seedu.schedar;

import java.io.IOException;
import java.nio.file.Path;
import java.util.function.Supplier;

import javafx.stage.Screen;
import javafx.stage.Stage;
import seedu.schedar.commons.core.Config;
import seedu.schedar.commons.core.GuiSettings;
import seedu.schedar.commons.exceptions.DataConversionException;
import seedu.schedar.model.Model;
import seedu.schedar.model.ModelManager;
import seedu.schedar.model.ReadOnlyTaskManager;
import seedu.schedar.model.TaskManager;
import seedu.schedar.model.UserPrefs;
import seedu.schedar.storage.JsonTaskManagerStorage;
import seedu.schedar.storage.UserPrefsStorage;
import systemtests.ModelHelper;

/**
 * This class is meant to override some properties of MainApp so that it will be suited for
 * testing
 */
public class TestApp extends MainApp {

    private Supplier<ReadOnlyTaskManager> initialDataSupplier;
    private Path saveFileLocation;
    private Path prefFileLocation;

    /**
     * Creates a {@code TestApp}.
     */
    public TestApp(Supplier<ReadOnlyTaskManager> initialDataSupplier, Path saveFileLocation, Path prefFileLocation) {
        super();
        this.initialDataSupplier = initialDataSupplier;
        this.saveFileLocation = saveFileLocation;
        this.prefFileLocation = prefFileLocation;

        // If some initial local data has been provided, write those to the file
        if (initialDataSupplier.get() != null) {
            JsonTaskManagerStorage jsonTaskManagerStorage = new JsonTaskManagerStorage(saveFileLocation);
            try {
                jsonTaskManagerStorage.saveTaskManager(initialDataSupplier.get());
            } catch (IOException ioe) {
                throw new AssertionError(ioe);
            }
        }
    }

    @Override
    protected Config initConfig(Path configFilePath) {
        Config config = super.initConfig(configFilePath);
        config.setUserPrefsFilePath(prefFileLocation);
        return config;
    }

    @Override
    protected UserPrefs initPrefs(UserPrefsStorage storage) {
        UserPrefs userPrefs = super.initPrefs(storage);
        double x = Screen.getPrimary().getVisualBounds().getMinX();
        double y = Screen.getPrimary().getVisualBounds().getMinY();
        userPrefs.setGuiSettings(new GuiSettings(600.0, 600.0, (int) x, (int) y));
        userPrefs.setTaskManagerFilePath(saveFileLocation);
        return userPrefs;
    }

    /**
     * Returns a defensive copy of the schedar book data stored inside the storage file.
     */
    public TaskManager readStorageTaskManager() {
        try {
            return new TaskManager(storage.readTaskManager().get());
        } catch (DataConversionException dce) {
            throw new AssertionError("Data is not in the TaskManager format.", dce);
        } catch (IOException ioe) {
            throw new AssertionError("Storage file cannot be found.", ioe);
        }
    }

    /**
     * Returns the file path of the storage file.
     */
    public Path getStorageSaveLocation() {
        return storage.getTaskManagerFilePath();
    }

    /**
     * Returns a defensive copy of the model.
     */
    public Model getModel() {
        Model copy = new ModelManager((model.getTaskManager()), new UserPrefs());
        ModelHelper.setFilteredList(copy, model.getFilteredTaskList());
        return copy;
    }

    @Override
    public void start(Stage primaryStage) {
        ui.start(primaryStage);
    }

    public static void main(String[] args) {
        launch(args);
    }

}
