package seedu.schedar.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.schedar.logic.commands.CommandTestUtil.DESC_LECTURE;
import static seedu.schedar.logic.commands.CommandTestUtil.DESC_PROJECT;
import static seedu.schedar.logic.commands.CommandTestUtil.VALID_DESCRIPTION_LECTURE;
import static seedu.schedar.logic.commands.CommandTestUtil.VALID_PRIORITY_LECTURE;
import static seedu.schedar.logic.commands.CommandTestUtil.VALID_TAG_LECTURE;
import static seedu.schedar.logic.commands.CommandTestUtil.VALID_TASKDATE_LECTURE;
import static seedu.schedar.logic.commands.CommandTestUtil.VALID_TASKTIME_LECTURE;
import static seedu.schedar.logic.commands.CommandTestUtil.VALID_TITLE_LECTURE;

import org.junit.jupiter.api.Test;

import seedu.schedar.logic.commands.EditEventCommand.EditEventDescriptor;
import seedu.schedar.testutil.EditEventDescriptorBuilder;

public class EditEventDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        EditEventDescriptor descriptorWithSameValues = new EditEventDescriptor(DESC_PROJECT);
        assertTrue(DESC_PROJECT.equals(descriptorWithSameValues));

        // same object -> returns true
        assertTrue(DESC_PROJECT.equals(DESC_PROJECT));

        // null -> returns false
        assertFalse(DESC_PROJECT.equals(null));

        // different types -> returns false
        assertFalse(DESC_PROJECT.equals(5));

        // different values -> returns false
        assertFalse(DESC_PROJECT.equals(DESC_LECTURE));

        // different TITLE -> returns false
        EditEventDescriptor editedProject = new EditEventDescriptorBuilder(DESC_PROJECT)
                .withTitle(VALID_TITLE_LECTURE).build();
        assertFalse(DESC_PROJECT.equals(editedProject));

        // different DESCRIPTION -> returns false
        editedProject = new EditEventDescriptorBuilder(DESC_PROJECT).withDescription(VALID_DESCRIPTION_LECTURE).build();
        assertFalse(DESC_PROJECT.equals(editedProject));

        // different PRIORITY -> returns false
        editedProject = new EditEventDescriptorBuilder(DESC_PROJECT).withPriority(VALID_PRIORITY_LECTURE).build();
        assertFalse(DESC_PROJECT.equals(editedProject));

        // different schedar -> returns false
        editedProject = new EditEventDescriptorBuilder(DESC_PROJECT).withEventDate(VALID_TASKDATE_LECTURE).build();
        assertFalse(DESC_PROJECT.equals(editedProject));

        // different schedar -> returns false
        editedProject = new EditEventDescriptorBuilder(DESC_PROJECT).withEventTime(VALID_TASKTIME_LECTURE).build();
        assertFalse(DESC_PROJECT.equals(editedProject));

        // different tags -> returns false
        editedProject = new EditEventDescriptorBuilder(DESC_PROJECT).withTags(VALID_TAG_LECTURE).build();
        assertFalse(DESC_PROJECT.equals(editedProject));
    }
}
