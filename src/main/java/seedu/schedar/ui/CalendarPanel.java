package seedu.schedar.ui;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
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

    @FXML
    private ListView<Task> taskListView2;

    @FXML
    private ListView<Task> taskListView3;

    @FXML
    private ListView<Task> taskListView4;

    @FXML
    private ListView<Task> taskListView5;

    @FXML
    private ListView<Task> taskListView6;

    @FXML
    private ListView<Task> taskListView7;

    @FXML
    private ListView<Task> taskListView8;

    @FXML
    private ListView<Task> taskListView9;

    @FXML
    private ListView<Task> taskListView10;

    @FXML
    private ListView<Task> taskListView11;

    @FXML
    private ListView<Task> taskListView12;

    @FXML
    private ListView<Task> taskListView13;

    @FXML
    private ListView<Task> taskListView14;

    @FXML
    private ListView<Task> taskListView15;

    @FXML
    private ListView<Task> taskListView16;

    @FXML
    private ListView<Task> taskListView17;

    @FXML
    private ListView<Task> taskListView18;

    @FXML
    private ListView<Task> taskListView19;

    @FXML
    private ListView<Task> taskListView20;

    @FXML
    private ListView<Task> taskListView21;

    @FXML
    private ListView<Task> taskListView22;

    @FXML
    private ListView<Task> taskListView23;

    @FXML
    private ListView<Task> taskListView24;

    @FXML
    private ListView<Task> taskListView25;

    @FXML
    private ListView<Task> taskListView26;

    @FXML
    private ListView<Task> taskListView27;

    @FXML
    private ListView<Task> taskListView28;

    @FXML
    private ListView<Task> taskListView29;

    @FXML
    private ListView<Task> taskListView30;

    @FXML
    private ListView<Task> taskListView31;

    @FXML
    private ListView<Task> taskListView32;

    @FXML
    private ListView<Task> taskListView33;

    @FXML
    private ListView<Task> taskListView34;

    @FXML
    private ListView<Task> taskListView35;

    @FXML
    private ListView<Task> taskListView36;

    @FXML
    private ListView<Task> taskListView37;

    @FXML
    private ListView<Task> taskListView38;

    @FXML
    private ListView<Task> taskListView39;

    @FXML
    private ListView<Task> taskListView40;

    @FXML
    private ListView<Task> taskListView41;

    @FXML
    private ListView<Task> taskListView42;

    private List<ListView<Task>> viewList = new ArrayList<>();

    private void initViewList() {
        viewList.add(taskListView1);
        viewList.add(taskListView2);
        viewList.add(taskListView3);
        viewList.add(taskListView4);
        viewList.add(taskListView5);
        viewList.add(taskListView6);
        viewList.add(taskListView7);
        viewList.add(taskListView8);
        viewList.add(taskListView9);
        viewList.add(taskListView10);
        viewList.add(taskListView11);
        viewList.add(taskListView12);
        viewList.add(taskListView13);
        viewList.add(taskListView14);
        viewList.add(taskListView15);
        viewList.add(taskListView16);
        viewList.add(taskListView17);
        viewList.add(taskListView18);
        viewList.add(taskListView19);
        viewList.add(taskListView20);
        viewList.add(taskListView21);
        viewList.add(taskListView22);
        viewList.add(taskListView23);
        viewList.add(taskListView24);
        viewList.add(taskListView25);
        viewList.add(taskListView26);
        viewList.add(taskListView27);
        viewList.add(taskListView28);
        viewList.add(taskListView29);
        viewList.add(taskListView30);
        viewList.add(taskListView31);
        viewList.add(taskListView32);
        viewList.add(taskListView33);
        viewList.add(taskListView34);
        viewList.add(taskListView35);
        viewList.add(taskListView36);
        viewList.add(taskListView37);
        viewList.add(taskListView38);
        viewList.add(taskListView39);
        viewList.add(taskListView40);
        viewList.add(taskListView41);
        viewList.add(taskListView42);
    }

    private LocalDate getFirstDay() {
        int currentYear = LocalDate.now().getYear();
        int currentMonth = LocalDate.now().getMonthValue();
        return LocalDate.of(currentYear, currentMonth, 1);
    }

    private LocalDate getLastDay() {
        int currentYear = LocalDate.now().getYear();
        int currentMonth = LocalDate.now().getMonthValue();
        return LocalDate.of(currentYear, currentMonth+1, 1).minusDays(1);
    }

    private int getDateDiff(LocalDate currentDate) {
        LocalDate firstJan2020 = LocalDate.of(2020, 1, 1);
        long dateDiff = ChronoUnit.DAYS.between(currentDate, firstJan2020);
        return (int)(dateDiff%42+1)%42;
    }

    /**
     * Creates a {@code TaskListPanel} with the given {@code ObservableList}.
     */
    public CalendarPanel(ObservableList<Task> taskList) {
        super(FXML);
        initViewList();
        int initialPosition = getDateDiff(LocalDate.now());
        LocalDate firstDayOfMonths = getFirstDay();
        LocalDate lastDayOfMonths = getFirstDay();

        taskListView1.setItems(taskList);
        taskListView1.setCellFactory(listView -> new CalendarViewCell());
        taskListView2.setItems(taskList);
        taskListView2.setCellFactory(listView -> new CalendarViewCell());
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
