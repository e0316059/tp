package seedu.schedar.testutil;

import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import seedu.schedar.logic.commands.EditEventCommand.EditEventDescriptor;
import seedu.schedar.model.tag.Tag;
import seedu.schedar.model.task.Description;
import seedu.schedar.model.task.Event;
import seedu.schedar.model.task.Priority;
import seedu.schedar.model.task.TaskDate;
import seedu.schedar.model.task.TaskTime;
import seedu.schedar.model.task.Title;

/**
 * A utility class to help with building EditEventDescriptor objects.
 */
public class EditEventDescriptorBuilder {

    private EditEventDescriptor descriptor;

    public EditEventDescriptorBuilder() {
        descriptor = new EditEventDescriptor();
    }

    public EditEventDescriptorBuilder(EditEventDescriptor descriptor) {
        this.descriptor = new EditEventDescriptor(descriptor);
    }

    /**
     * Returns an {@code EditEventDescriptor} with fields containing {@code Event}'s details
     */
    public EditEventDescriptorBuilder(Event event) {
        descriptor = new EditEventDescriptor();
        descriptor.setTitle(event.getTitle());
        descriptor.setDescription(event.getDescription());
        descriptor.setPriority(event.getPriority());
        descriptor.setEventDate(event.getEventDate());
        descriptor.setEventTime(event.getEventTime());
        descriptor.setTagList(event.getTags());
    }

    /**
     * Sets the {@code Title} of the {@code EditEventDescriptor} that we are building.
     */
    public EditEventDescriptorBuilder withTitle(String title) {
        descriptor.setTitle(new Title(title));
        return this;
    }

    /**
     * Sets the {@code Description} of the {@code EditEventDescriptor} that we are building.
     */
    public EditEventDescriptorBuilder withDescription(String description) {
        descriptor.setDescription(new Description(description));
        return this;
    }

    /**
     * Sets the {@code Priority} of the {@code EditEventDescriptor} that we are building.
     */
    public EditEventDescriptorBuilder withPriority(String priority) {
        descriptor.setPriority(new Priority(priority));
        return this;
    }

    /**
     * Sets the {@code schedar} of the {@code EditEventDescriptor} that we are building.
     */
    public EditEventDescriptorBuilder withEventDate(String taskDate) {
        descriptor.setEventDate(new TaskDate(taskDate));
        return this;
    }

    /**
     * Sets the {@code schedar} of the {@code EditEventDescriptor} that we are building.
     */
    public EditEventDescriptorBuilder withEventTime(String taskTime) {
        descriptor.setEventTime(new TaskTime(taskTime));
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code EditEventDescriptor}
     * that we are building.
     */
    public EditEventDescriptorBuilder withTags(String... tags) {
        Set<Tag> tagSet = Stream.of(tags).map(Tag::new).collect(Collectors.toSet());
        descriptor.setTagList(tagSet);
        return this;
    }

    public EditEventDescriptor build() {
        return descriptor;
    }
}
