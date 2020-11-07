package seedu.schedar.testutil;

import java.util.HashSet;
import java.util.Set;

import seedu.schedar.model.tag.Tag;
import seedu.schedar.model.task.Description;
import seedu.schedar.model.task.DoneStatus;
import seedu.schedar.model.task.Event;
import seedu.schedar.model.task.Priority;
import seedu.schedar.model.task.TaskDate;
import seedu.schedar.model.task.TaskTime;
import seedu.schedar.model.task.Title;
import seedu.schedar.model.util.SampleDataUtil;

public class EventBuilder {

    public static final String DEFAULT_TITLE = "Do tP tasks";
    public static final String DEFAULT_DESCRIPTION = "Complete tP tasks for week 10.";
    public static final String DEFAULT_PRIORITY = "High";
    public static final String DEFAULT_EVENT_DATE = "2020-12-31";
    public static final String DEFAULT_EVENT_TIME = "12:00";

    private Title title;
    private Description description;
    private Priority priority;
    private DoneStatus status;
    private TaskDate eventDate;
    private TaskTime eventTime;
    private Set<Tag> tags;

    /**
     * Creates a {@code seedu.schedar.model.task.EventBuilder} with the default details.
     */
    public EventBuilder() {
        title = new Title(DEFAULT_TITLE);
        description = new Description(DEFAULT_DESCRIPTION);
        priority = new Priority(DEFAULT_PRIORITY);
        status = new DoneStatus();
        eventDate = new TaskDate(DEFAULT_EVENT_DATE);
        eventTime = new TaskTime(DEFAULT_EVENT_TIME);
        tags = new HashSet<>();
    }

    /**
     * Initializes the EventBuilder with the data of {@code todoToCopy}.
     */
    public EventBuilder(Event eventToCopy) {
        title = eventToCopy.getTitle();
        description = eventToCopy.getDescription();
        priority = eventToCopy.getPriority();
        status = eventToCopy.getStatus();
        eventDate = eventToCopy.getEventDate();
        eventTime = eventToCopy.getEventTime();
        tags = new HashSet<>(eventToCopy.getTags());
    }

    /**
     * Sets the {@code Title} of the {@code Event} that we are building.
     */
    public EventBuilder withTitle(String title) {
        this.title = new Title(title);
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code Event} that we are building.
     */
    public EventBuilder withDescription(String description) {
        this.description = new Description(description);
        return this;
    }

    /**
     * Sets the {@code Priority} of the {@code Event} that we are building.
     */
    public EventBuilder withPriority(String priority) {
        this.priority = new Priority(priority);
        return this;
    }

    /**
     * Sets the {@code DoneStatus} of the {@code Event} that we are building.
     */
    public EventBuilder withDoneStatus(int statusCode) {
        this.status = new DoneStatus(statusCode);
        return this;
    }

    /**
     * Sets the {@code eventDate} of the {@code Event} that we are building.
     */
    public EventBuilder withEventDate(String eventDate) {
        this.eventDate = new TaskDate(eventDate);
        return this;
    }

    /**
     * Sets the {@code eventTime} of the {@code Event} that we are building.
     */
    public EventBuilder withEventTime(String eventTime) {
        this.eventTime = new TaskTime(eventTime);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Event} that we are building.
     */
    public EventBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    public Event build() {
        return new Event(title, description, priority, eventDate, eventTime, status, tags);
    }
}
