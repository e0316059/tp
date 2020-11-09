package seedu.schedar.ui;

import java.time.LocalDate;
import java.util.logging.Logger;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.scene.layout.Region;
import seedu.schedar.commons.core.LogsCenter;
import seedu.schedar.model.task.Deadline;
import seedu.schedar.model.task.Event;
import seedu.schedar.model.task.Task;
import seedu.schedar.model.task.ToDo;

/**
 * Panel containing the list of persons.
 */
public class MondayPanel extends UiPart<Region> {
    private static final String FXML = "MondayList.fxml";
    private final Logger logger = LogsCenter.getLogger(SundayPanel.class);

    @FXML
    private ListView<Task> dailyListView;

    private long getDayToday() {
        switch (LocalDate.now().getDayOfWeek().name()) {
            case "MONDAY": return 1;
            case "TUESDAY": return 2;
            case "WEDNESDAY": return 3;
            case "THURSDAY": return 4;
            case "FRIDAY": return 5;
            case "SATURDAY": return 6;
            default: return 0;
        }
    }

    private LocalDate getDay(long day) {
        return LocalDate.now().minusDays(getDayToday()).plusDays(day);
    }

    private boolean checkTaskDate(Task task, LocalDate date){
        if (task instanceof ToDo)
            return false;
        if (task instanceof Deadline)
            return ((Deadline) task).getDeadlineDate().date.equals(date);
        return ((Event) task).getEventDate().date.equals(date);
    }

    /**
     * Creates a {@code TaskListPanel} with the given {@code ObservableList}.
     */
    public MondayPanel(ObservableList<Task> taskList) {
        super(FXML);

        dailyListView.setItems(taskList.filtered(x->checkTaskDate(x, getDay(1))));
        dailyListView.setCellFactory(listView -> new CalendarViewCell());
    }

    /**
     * Custom {@code ListCell} that displays the graphics of a {@code Task} using a {@code TaskCard}.
     */
    class CalendarViewCell extends ListCell<Task> {

        @Override
        protected void updateItem(Task task, boolean empty) {
            if (task!=null && checkTaskDate(task, getDay(1))) {
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

}
