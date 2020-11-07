package seedu.schedar.testutil;

import static seedu.schedar.logic.commands.CommandTestUtil.VALID_DESCRIPTION_LECTURE;
import static seedu.schedar.logic.commands.CommandTestUtil.VALID_DESCRIPTION_PROJECT;
import static seedu.schedar.logic.commands.CommandTestUtil.VALID_PRIORITY_LECTURE;
import static seedu.schedar.logic.commands.CommandTestUtil.VALID_PRIORITY_PROJECT;
import static seedu.schedar.logic.commands.CommandTestUtil.VALID_TAG_LECTURE;
import static seedu.schedar.logic.commands.CommandTestUtil.VALID_TAG_PROJECT;
import static seedu.schedar.logic.commands.CommandTestUtil.VALID_TASKDATE_LECTURE;
import static seedu.schedar.logic.commands.CommandTestUtil.VALID_TASKDATE_PROJECT;
import static seedu.schedar.logic.commands.CommandTestUtil.VALID_TASKTIME_LECTURE;
import static seedu.schedar.logic.commands.CommandTestUtil.VALID_TASKTIME_PROJECT;
import static seedu.schedar.logic.commands.CommandTestUtil.VALID_TITLE_LECTURE;
import static seedu.schedar.logic.commands.CommandTestUtil.VALID_TITLE_PROJECT;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.schedar.model.TaskManager;
import seedu.schedar.model.task.Deadline;
import seedu.schedar.model.task.Event;
import seedu.schedar.model.task.Task;
import seedu.schedar.model.task.ToDo;

/**
 * A utility class containing a list of {@code Task} objects to be used in tests.
 */
public class TypicalTasks {

    public static final Event ATTEND = new EventBuilder().withTitle("Attend CS2103 Lecture")
            .withDescription("Weekly Friday CS2103 Lecture").withPriority("Medium")
            .withEventDate("2020-10-23").withEventTime("12:00").withTags("lecture").build();
    public static final ToDo BAKE = new ToDoBuilder().withTitle("Bake cookies")
            .withDescription("Bake cookies for party").withPriority("Low")
            .withTags("party", "baking").build();
    public static final Deadline CRAFT = new DeadlineBuilder().withTitle("Craft model plane")
            .withDescription("Model plane for GET project").withPriority("High")
            .withDeadlineDate("2020-11-30").withTags("project").build();
    public static final ToDo DISH = new ToDoBuilder().withTitle("Wash dishes")
            .withDescription("Wash dishes after party").withPriority("Low").withTags("party").build();
    public static final Deadline ELECT = new DeadlineBuilder().withTitle("Elect new committee members")
            .withDescription("Interview and choose new committee for CCA").withPriority("High")
            .withDeadlineDate("2020-12-31").withDoneStatus(1).withTags("cca").build();
    public static final Event FISH = new EventBuilder().withTitle("Go fishing")
            .withDescription("Weekly fishing session with dad").withPriority("Low")
            .withEventDate("2020-11-01").withEventTime("17:00").withTags("fishing").build();
    public static final Event GAME = new EventBuilder().withTitle("Play CS GO")
            .withDescription("Play CS GO with bro").withPriority("Low")
            .withEventDate("2020-11-01").withEventTime("22:00").withTags("gaming").build();

    // Manually Added
    public static final Event HIKE = new EventBuilder().withTitle("Hiking with John")
            .withDescription("Explore Pulau Ubin on foot").withPriority("High")
            .withEventDate("2020-10-24").withEventTime("08:00").withTags("hiking").build();
    public static final Deadline IMPROVE = new DeadlineBuilder().withTitle("Improve CS2103 iP")
            .withDescription("Clean up iP for use in portfolio").withPriority("High")
            .withDeadlineDate("2020-10-25").withDoneStatus(1).build();

    // Manually added - Task's details found in {@code CommandTestUtil}
    public static final ToDo TODO_PROJECT = new ToDoBuilder().withTitle(VALID_TITLE_PROJECT)
            .withDescription(VALID_DESCRIPTION_PROJECT).withPriority(VALID_PRIORITY_PROJECT)
            .withTags(VALID_TAG_PROJECT).build();
    public static final Deadline DEADLINE_PROJECT = new DeadlineBuilder().withTitle(VALID_TITLE_PROJECT)
            .withDescription(VALID_DESCRIPTION_PROJECT).withPriority(VALID_PRIORITY_PROJECT)
            .withDeadlineDate(VALID_TASKDATE_PROJECT)
            .withTags(VALID_TAG_PROJECT).build();
    public static final Event EVENT_PROJECT = new EventBuilder().withTitle(VALID_TITLE_PROJECT)
            .withDescription(VALID_DESCRIPTION_PROJECT).withPriority(VALID_PRIORITY_PROJECT)
            .withEventDate(VALID_TASKDATE_PROJECT).withEventTime(VALID_TASKTIME_PROJECT)
            .withTags(VALID_TAG_PROJECT).build();

    public static final ToDo TODO_LECTURE = new ToDoBuilder().withTitle(VALID_TITLE_LECTURE)
            .withDescription(VALID_DESCRIPTION_LECTURE).withPriority(VALID_PRIORITY_LECTURE)
            .withTags(VALID_TAG_LECTURE).build();
    public static final Deadline DEADLINE_LECTURE = new DeadlineBuilder().withTitle(VALID_TITLE_LECTURE)
            .withDescription(VALID_DESCRIPTION_LECTURE).withPriority(VALID_PRIORITY_LECTURE)
            .withDeadlineDate(VALID_TASKDATE_LECTURE)
            .withTags(VALID_TAG_LECTURE).build();
    public static final Event EVENT_LECTURE = new EventBuilder().withTitle(VALID_TITLE_LECTURE)
            .withDescription(VALID_DESCRIPTION_LECTURE).withPriority(VALID_PRIORITY_LECTURE)
            .withEventDate(VALID_TASKDATE_LECTURE).withEventTime(VALID_TASKTIME_LECTURE)
            .withTags(VALID_TAG_LECTURE).build();

    private TypicalTasks() {} // prevents instantiation

    /**
     * Returns a {@code TaskManager} with all the typical tasks.
     */
    public static TaskManager getTypicalTaskManager() {
        TaskManager tm = new TaskManager();
        for (Task task : getTypicalTasks()) {
            tm.addTask(task);
        }
        return tm;
    }

    public static List<Task> getTypicalTasks() {
        return new ArrayList<>(Arrays.asList(ATTEND, BAKE, CRAFT, DISH, ELECT, FISH, GAME));
    }
}
