package seedu.schedar.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.schedar.logic.commands.CommandTestUtil.VALID_DESCRIPTION_LECTURE;
import static seedu.schedar.logic.commands.CommandTestUtil.VALID_PRIORITY_PROJECT;
import static seedu.schedar.logic.commands.CommandTestUtil.VALID_TAG_PROJECT;
import static seedu.schedar.logic.commands.CommandTestUtil.VALID_TASKDATE_LECTURE;
import static seedu.schedar.logic.commands.CommandTestUtil.VALID_TASKTIME_LECTURE;
import static seedu.schedar.logic.commands.CommandTestUtil.VALID_TITLE_LECTURE;
import static seedu.schedar.testutil.Assert.assertThrows;
import static seedu.schedar.testutil.TypicalTasks.ATTEND;
import static seedu.schedar.testutil.TypicalTasks.BAKE;
import static seedu.schedar.testutil.TypicalTasks.FISH;

import org.junit.jupiter.api.Test;

import seedu.schedar.testutil.EventBuilder;

public class EventTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Event deadline = new EventBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> deadline.getTags().remove(0));
    }

    @Test
    public void isSameTask() {
        // same object -> returns true
        assertTrue(ATTEND.isSameTask(ATTEND));

        // null -> returns false
        assertFalse(ATTEND.isSameTask(null));

        // different description -> returns false
        Event editedAttend = new EventBuilder(ATTEND).withDescription(VALID_DESCRIPTION_LECTURE).build();
        assertFalse(ATTEND.isSameTask(editedAttend));

        // different title -> returns false
        editedAttend = new EventBuilder(ATTEND).withTitle(VALID_TITLE_LECTURE).build();
        assertFalse(ATTEND.isSameTask(editedAttend));

        // same title, same description, different attributes -> returns true
        editedAttend = new EventBuilder(ATTEND).withPriority(VALID_PRIORITY_PROJECT)
                .withEventDate(VALID_TASKDATE_LECTURE).withDoneStatus(1).withTags(VALID_TAG_PROJECT).build();
        assertTrue(ATTEND.isSameTask(editedAttend));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Event bakeCopy = new EventBuilder(ATTEND).build();
        assertTrue(ATTEND.equals(bakeCopy));

        //same object -> returns true
        assertTrue(ATTEND.equals(ATTEND));

        // null -> returns false
        assertFalse(ATTEND.equals(null));

        // different type -> returns false
        assertFalse(ATTEND.equals(BAKE));

        // different Events -> return false
        assertFalse(ATTEND.equals(FISH));

        // different title -> returns false
        Event editedAttend = new EventBuilder(ATTEND).withTitle(VALID_TITLE_LECTURE).build();
        assertFalse(ATTEND.equals(editedAttend));

        // different description -> returns false
        editedAttend = new EventBuilder(ATTEND).withDescription(VALID_DESCRIPTION_LECTURE).build();
        assertFalse(ATTEND.equals(editedAttend));

        // different priority -> returns false
        editedAttend = new EventBuilder(ATTEND).withPriority(VALID_PRIORITY_PROJECT).build();
        assertFalse(ATTEND.equals(editedAttend));

        // different eventDate -> returns false
        editedAttend = new EventBuilder(ATTEND).withEventDate(VALID_TASKDATE_LECTURE).build();
        assertFalse(ATTEND.equals(editedAttend));

        // different eventTime -> returns false
        editedAttend = new EventBuilder(ATTEND).withEventTime(VALID_TASKTIME_LECTURE).build();
        assertFalse(ATTEND.equals(editedAttend));

        // different tags -> returns false
        editedAttend = new EventBuilder(ATTEND).withTags(VALID_TAG_PROJECT).build();
        assertFalse(ATTEND.equals(editedAttend));
    }
}
