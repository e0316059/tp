package seedu.schedar.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.schedar.model.tag.Tag;
import seedu.schedar.model.task.Description;
import seedu.schedar.model.task.DoneStatus;
import seedu.schedar.model.task.Priority;
import seedu.schedar.model.task.Title;
import seedu.schedar.model.task.ToDo;
import seedu.schedar.model.util.SampleDataUtil;

/**
 * A utility class to help with building ToDo objects.
 */
public class ToDoBuilder {

    public static final String DEFAULT_TITLE = "Do tP tasks";
    public static final String DEFAULT_DESCRIPTION = "Complete tP tasks for week 10.";
    public static final String DEFAULT_PRIORITY = "High";

    private Title title;
    private Description description;
    private Priority priority;
    private DoneStatus status;
    private Set<Tag> tags;

    /**
     * Creates a {@code ToDoBuilder} with the default details.
     */
    public ToDoBuilder() {
        title = new Title(DEFAULT_TITLE);
        description = new Description(DEFAULT_DESCRIPTION);
        priority = new Priority(DEFAULT_PRIORITY);
        status = new DoneStatus();
        tags = new HashSet<>();
    }

    /**
     * Initializes the ToDoBuilder with the data of {@code todoToCopy}.
     */
    public ToDoBuilder(ToDo todoToCopy) {
        title = todoToCopy.getTitle();
        description = todoToCopy.getDescription();
        priority = todoToCopy.getPriority();
        status = todoToCopy.getStatus();
        tags = new HashSet<>(todoToCopy.getTags());
    }

    /**
     * Sets the {@code Title} of the {@code ToDo} that we are building.
     */
    public ToDoBuilder withTitle(String title) {
        this.title = new Title(title);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code ToDo} that we are building.
     */
    public ToDoBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code Priority} of the {@code ToDo} that we are building.
     */
    public ToDoBuilder withPriority(String priority) {
        this.priority = new Priority(priority);
        return this;
    }

    /**
     * Sets the {@code DoneStatus} of the {@code ToDo} that we are building.
     */
    public ToDoBuilder withDoneStatus(int statusCode) {
        this.status = new DoneStatus(statusCode);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code ToDo} that we are building.
     */
    public ToDoBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    public ToDo build() {
        return new ToDo(title, description, priority, status, tags);
    }
}
