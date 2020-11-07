package seedu.schedar.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.schedar.model.ReadOnlyTaskManager;
import seedu.schedar.model.TaskManager;
import seedu.schedar.model.tag.Tag;
import seedu.schedar.model.task.Deadline;
import seedu.schedar.model.task.Description;
import seedu.schedar.model.task.Event;
import seedu.schedar.model.task.Priority;
import seedu.schedar.model.task.Task;
import seedu.schedar.model.task.TaskDate;
import seedu.schedar.model.task.TaskTime;
import seedu.schedar.model.task.Title;
import seedu.schedar.model.task.ToDo;

/**
 * Contains utility methods for populating {@code TaskManager} with sample data.
 */
public class SampleDataUtil {
    public static Task[] getSampleTasks() {
        return new Task[] {
            new ToDo(new Title("cs2103 tp"), new Description("Team Project"), new Priority("High"),
                getTagSet("cs2103", "projects")),
            new Deadline(new Title("cs2103 peer review"), new Description("Peer reviews for tp"),
                new Priority("Medium"), new TaskDate("2020-10-10"),
                getTagSet("cs2103")),
            new Event(new Title("cs2103 lecture"), new Description("Weekly lecture"), new Priority("Low"),
                new TaskDate("2020-10-09"), new TaskTime("12:00"),
                getTagSet("neighbours")),
        };
    }

    public static ReadOnlyTaskManager getSampleTaskManager() {
        TaskManager sampleTm = new TaskManager();
        for (Task sampleTask : getSampleTasks()) {
            sampleTm.addTask(sampleTask);
        }
        return sampleTm;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
                .map(Tag::new)
                .collect(Collectors.toSet());
    }

}
