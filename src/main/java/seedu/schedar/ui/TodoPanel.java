package seedu.schedar.ui;

import java.time.LocalDate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.schedar.commons.core.LogsCenter;
import seedu.schedar.model.task.Task;
import seedu.schedar.model.task.ToDo;

/**
 * Panel containing the list of persons.
 */
public class TodoPanel extends UiPart<Region> {
    private static final String FXML = "TodoList.fxml";
    private final Logger logger = LogsCenter.getLogger(SundayPanel.class);

    @FXML
    private ListView<Task> dailyListView;

    /**
     * Creates a {@code TaskListPanel} with the given {@code ObservableList}.
     */
    public TodoPanel(ObservableList<Task> taskList) {
        super(FXML);

        dailyListView.setItems(taskList);
        dailyListView.setCellFactory(listView -> new CalendarViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Task} using a {@code TaskCard}.
     */
    class CalendarViewCell extends ListCell<Task> {
        @Override
        protected void updateItem(Task task, boolean empty) {
            if (task != null && task instanceof ToDo) {
                super.updateItem(task, empty);
                if (empty || task == null) {
                    setGraphic(null);
                    setText(null);
                } else {
                    setGraphic(new CalendarCard(task).getRoot());
                }
            } else {
                super.updateItem(null, true);
                setGraphic(null);
                setText(null);
            }
        }
    }
}
