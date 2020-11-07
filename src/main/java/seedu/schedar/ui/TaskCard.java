package seedu.schedar.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.schedar.model.task.Deadline;
import seedu.schedar.model.task.Event;
import seedu.schedar.model.task.Task;

/**
 * An UI component that displays information of a {@code Person}.
 */
public class TaskCard extends UiPart<Region> {

    private static final String FXML = "TaskListCard.fxml";

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
    @FXML
    private Label id;
    @FXML
    private Label priority;
    @FXML
    private Label description;
    @FXML
    private Label status;
    @FXML
    private FlowPane date;
    @FXML
    private FlowPane time;
    @FXML
    private FlowPane tags;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public TaskCard(Task task, int displayedIndex) {
        super(FXML);
        this.task = task;
        id.setText(displayedIndex + ". ");
        title.setText(task.getTitle().title);
        priority.setText(task.getPriority().level.toString());
        description.setText(task.getDescription().value);
        status.setText(task.getStatus().status.toString());
        if (task instanceof Deadline) {
            Label dateLabel = new Label();
            dateLabel.setText("Deadline: " + ((Deadline) task).getDeadlineDate().toString());
            date.getChildren().add(dateLabel);
        } else if (task instanceof Event) {
            Label dateLabel = new Label();
            dateLabel.setText("Date: " + ((Event) task).getEventDate());
            date.getChildren().add(dateLabel);
            Label timeLabel = new Label();
            timeLabel.setText("Time: " + ((Event) task).getEventTime().toString());
            time.getChildren().add(timeLabel);
        }
        task.getTags().stream()
                .sorted(Comparator.comparing(tag -> tag.tagName))
                .forEach(tag -> tags.getChildren().add(new Label(tag.tagName)));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TaskCard)) {
            return false;
        }

        // state check
        TaskCard card = (TaskCard) other;
        return id.getText().equals(card.id.getText())
                && task.equals(card.task);
    }
}
