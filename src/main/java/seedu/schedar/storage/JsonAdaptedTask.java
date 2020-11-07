package seedu.schedar.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.schedar.commons.exceptions.IllegalValueException;
import seedu.schedar.model.tag.Tag;
import seedu.schedar.model.task.Deadline;
import seedu.schedar.model.task.Description;
import seedu.schedar.model.task.DoneStatus;
import seedu.schedar.model.task.Event;
import seedu.schedar.model.task.Priority;
import seedu.schedar.model.task.Task;
import seedu.schedar.model.task.TaskDate;
import seedu.schedar.model.task.TaskTime;
import seedu.schedar.model.task.Title;
import seedu.schedar.model.task.ToDo;

public class JsonAdaptedTask {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Task's %s field is missing!";

    private final String type;

    private final String title;
    private final String description;
    private final String priority;
    private final Integer doneStatus;
    private final List<JsonAdaptedTag> tagged = new ArrayList<>();

    private final String deadlineDate;
    private final String eventDate;
    private final String eventTime;

    /**
     * Constructs a {@code JsonAdaptedTodo} with the given task details.
     */
    @JsonCreator
    public JsonAdaptedTask(@JsonProperty("title") String title, @JsonProperty("description") String description,
                           @JsonProperty("priority") String priority, @JsonProperty("doneStatus") Integer doneStatus,
                           @JsonProperty("tagged") List<JsonAdaptedTag> tagged,
                           @JsonProperty("type") String type, @JsonProperty("deadlineDate") String deadlineDate,
                           @JsonProperty("eventDate") String eventDate, @JsonProperty("eventTime") String eventTime) {
        this.type = type;

        this.title = title;
        this.description = description;
        this.priority = priority;
        this.doneStatus = doneStatus;
        if (tagged != null) {
            this.tagged.addAll(tagged);
        }

        this.deadlineDate = deadlineDate;

        this.eventDate = eventDate;
        this.eventTime = eventTime;
    }

    /**
     * Converts a given {@code Task} into this class for Jackson use.
     */
    public JsonAdaptedTask(Task source) {
        title = source.getTitle().title;
        description = source.getDescription().value;
        priority = source.getPriority().level.toString();
        doneStatus = source.getStatus().status.getStatusCode();
        tagged.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));

        if (source instanceof Deadline) {
            type = Deadline.TASK_TYPE;
            deadlineDate = ((Deadline) source).getDeadlineDate().toString();
            eventDate = null;
            eventTime = null;
        } else if (source instanceof Event) {
            type = Event.TASK_TYPE;
            deadlineDate = null;
            eventDate = ((Event) source).getEventDate().toString();
            eventTime = ((Event) source).getEventTime().toString();
        } else {
            type = ToDo.TASK_TYPE;
            deadlineDate = null;
            eventDate = null;
            eventTime = null;
        }
    }

    /**
     * Converts this Jackson-friendly adapted todo object into the model's {@code ToDo} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted todo.
     */
    public Task toModelType() throws IllegalValueException {
        final List<Tag> taskTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tagged) {
            taskTags.add(tag.toModelType());
        }

        if (title == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Title.class.getSimpleName()));
        }
        if (!Title.isValidTitle(title)) {
            throw new IllegalValueException(Title.MESSAGE_CONSTRAINTS);
        }
        final Title modelTitle = new Title(title);

        if (description == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Description.class.getSimpleName()));
        }
        if (!Description.isValidDescription(description)) {
            throw new IllegalValueException(Description.MESSAGE_CONSTRAINTS);
        }
        final Description modelDesc = new Description(description);

        if (priority == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Priority.class.getSimpleName()));
        }
        if (!Priority.isValidPriority(priority)) {
            throw new IllegalValueException(Priority.MESSAGE_CONSTRAINTS);
        }
        final Priority modelPriority = new Priority(priority);

        if (doneStatus == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    DoneStatus.class.getSimpleName()));
        }
        if (!DoneStatus.isValidDoneStatus(doneStatus)) {
            throw new IllegalValueException(DoneStatus.MESSAGE_CONSTRAINTS);
        }
        final DoneStatus modelStatus = new DoneStatus(doneStatus);

        final Set<Tag> modelTags = new HashSet<>(taskTags);

        switch (type) {
        case Deadline.TASK_TYPE:
            if (deadlineDate == null) {
                throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                        TaskDate.class.getSimpleName()));
            }
            if (!TaskDate.isValidDate(deadlineDate)) {
                throw new IllegalValueException(TaskDate.MESSAGE_CONSTRAINTS);
            }
            final TaskDate modelDlDate = new TaskDate(deadlineDate);
            return new Deadline(modelTitle, modelDesc, modelPriority, modelDlDate, modelStatus, modelTags);

        case Event.TASK_TYPE:
            if (eventDate == null) {
                throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                        TaskDate.class.getSimpleName()));
            }
            if (!TaskDate.isValidDate(eventDate)) {
                throw new IllegalValueException(TaskDate.MESSAGE_CONSTRAINTS);
            }
            final TaskDate modelEventDate = new TaskDate(eventDate);

            if (eventTime == null) {
                throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                        TaskTime.class.getSimpleName()));
            }
            if (!TaskTime.isValidTime(eventTime)) {
                throw new IllegalValueException(TaskTime.MESSAGE_CONSTRAINTS);
            }
            final TaskTime modelEventTime = new TaskTime(eventTime);
            return new Event(modelTitle, modelDesc, modelPriority,
                    modelEventDate, modelEventTime, modelStatus, modelTags);

        case ToDo.TASK_TYPE:
            return new ToDo(modelTitle, modelDesc, modelPriority, modelStatus, modelTags);

        default:
            throw new IllegalValueException("Stored Task matches no known patterns.");
        }
    }
}
