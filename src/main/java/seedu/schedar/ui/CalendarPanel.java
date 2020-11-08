package seedu.schedar.ui;

import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.schedar.commons.core.LogsCenter;
import seedu.schedar.model.task.Task;

/**
 * Panel containing the list of persons.
 */
public class CalendarPanel extends UiPart<Region> {
    private static final String FXML = "CalendarList.fxml";
    private final Logger logger = LogsCenter.getLogger(CalendarPanel.class);

    @FXML
    private ListView<Task> taskListView1;

    /**
     * Creates a {@code TaskListPanel} with the given {@code ObservableList}.
     */
    public CalendarPanel(ObservableList<Task> taskList) {
        super(FXML);
        taskListView1.setItems(taskList);
        taskListView1.setCellFactory(listView -> new CalendarViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Task} using a {@code TaskCard}.
     */
    class CalendarViewCell extends ListCell<Task> {
        @Override
        protected void updateItem(Task task, boolean empty) {
            super.updateItem(task, empty);

            if (empty || task == null) {
                setGraphic(null);
                setText(null);
            } else {
                setGraphic(new CalendarCard(task).getRoot());
            }
        }
    }

}
