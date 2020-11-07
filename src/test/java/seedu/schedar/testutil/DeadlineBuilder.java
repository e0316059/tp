package seedu.schedar.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.schedar.model.tag.Tag;
import seedu.schedar.model.task.Deadline;
import seedu.schedar.model.task.Description;
import seedu.schedar.model.task.DoneStatus;
import seedu.schedar.model.task.Priority;
import seedu.schedar.model.task.TaskDate;
import seedu.schedar.model.task.Title;
import seedu.schedar.model.util.SampleDataUtil;

public class DeadlineBuilder {

    public static final String DEFAULT_TITLE = "Do tP tasks";
    public static final String DEFAULT_DESCRIPTION = "Complete tP tasks for week 10.";
    public static final String DEFAULT_PRIORITY = "High";
    public static final String DEFAULT_DEADLINE_DATE = "2020-12-31";

    private Title title;
    private Description description;
    private Priority priority;
    private DoneStatus status;
    private TaskDate deadlineDate;
    private Set<Tag> tags;

    /**
     * Creates a {@code DeadlineBuilder} with the default details.
     */
    public DeadlineBuilder() {
        title = new Title(DEFAULT_TITLE);
        description = new Description(DEFAULT_DESCRIPTION);
        priority = new Priority(DEFAULT_PRIORITY);
        status = new DoneStatus();
        deadlineDate = new TaskDate(DEFAULT_DEADLINE_DATE);
        tags = new HashSet<>();
    }

    /**
     * Initializes the DeadlineBuilder with the data of {@code todoToCopy}.
     */
    public DeadlineBuilder(Deadline dlToCopy) {
        title = dlToCopy.getTitle();
        description = dlToCopy.getDescription();
        priority = dlToCopy.getPriority();
        status = dlToCopy.getStatus();
        deadlineDate = dlToCopy.getDeadlineDate();
        tags = new HashSet<>(dlToCopy.getTags());
    }

    /**
     * Sets the {@code Title} of the {@code Deadline} that we are building.
     */
    public DeadlineBuilder withTitle(String title) {
        this.title = new Title(title);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Deadline} that we are building.
     */
    public DeadlineBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code Priority} of the {@code Deadline} that we are building.
     */
    public DeadlineBuilder withPriority(String priority) {
        this.priority = new Priority(priority);
        return this;
    }

    /**
     * Sets the {@code DoneStatus} of the {@code Deadline} that we are building.
     */
    public DeadlineBuilder withDoneStatus(int statusCode) {
        this.status = new DoneStatus(statusCode);
        return this;
    }

    /**
     * Sets the {@code deadlineDate} of the {@code Deadline} that we are building.
     */
    public DeadlineBuilder withDeadlineDate(String deadlineDate) {
        this.deadlineDate = new TaskDate(deadlineDate);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Deadline} that we are building.
     */
    public DeadlineBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    public Deadline build() {
        return new Deadline(title, description, priority, deadlineDate, status, tags);
    }
}
