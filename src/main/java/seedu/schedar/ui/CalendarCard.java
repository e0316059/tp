package seedu.schedar.ui;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.schedar.model.task.Task;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class CalendarCard extends UiPart<Region> {

    private static final String FXML = "CalendarDateCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     */

    public final Task task;

    @FXML
    private HBox cardPane;
    @FXML
    private Label title;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public CalendarCard(Task task) {
        super(FXML);
        this.task = task;
        title.setText(task.getTitle().title);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof CalendarCard)) {
            return false;
        }

        // state check
        CalendarCard card = (CalendarCard) other;
        return title.getText().equals(card.title.getText())
                && task.equals(card.task);
    }
}
