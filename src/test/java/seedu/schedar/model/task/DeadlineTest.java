package seedu.schedar.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.schedar.logic.commands.CommandTestUtil.VALID_DESCRIPTION_PROJECT;
import static seedu.schedar.logic.commands.CommandTestUtil.VALID_PRIORITY_LECTURE;
import static seedu.schedar.logic.commands.CommandTestUtil.VALID_TAG_LECTURE;
import static seedu.schedar.logic.commands.CommandTestUtil.VALID_TAG_PROJECT;
import static seedu.schedar.logic.commands.CommandTestUtil.VALID_TASKDATE_PROJECT;
import static seedu.schedar.logic.commands.CommandTestUtil.VALID_TITLE_PROJECT;
import static seedu.schedar.testutil.Assert.assertThrows;
import static seedu.schedar.testutil.TypicalTasks.ATTEND;
import static seedu.schedar.testutil.TypicalTasks.CRAFT;
import static seedu.schedar.testutil.TypicalTasks.ELECT;

import org.junit.jupiter.api.Test;

import seedu.schedar.testutil.DeadlineBuilder;

public class DeadlineTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Deadline deadline = new DeadlineBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> deadline.getTags().remove(0));
    }

    @Test
    public void isSameTask() {
        // same object -> returns true
        assertTrue(CRAFT.isSameTask(CRAFT));

        // null -> returns false
        assertFalse(CRAFT.isSameTask(null));

        // different description -> returns false
        Deadline editedCraft = new DeadlineBuilder(CRAFT).withDescription(VALID_DESCRIPTION_PROJECT).build();
        assertFalse(CRAFT.isSameTask(editedCraft));

        // different title -> returns false
        editedCraft = new DeadlineBuilder(CRAFT).withTitle(VALID_TITLE_PROJECT).build();
        assertFalse(CRAFT.isSameTask(editedCraft));

        // same title, same description, different attributes -> returns true
        editedCraft = new DeadlineBuilder(CRAFT).withPriority(VALID_PRIORITY_LECTURE)
                .withDeadlineDate(VALID_TASKDATE_PROJECT).withDoneStatus(1).withTags(VALID_TAG_PROJECT).build();
        assertTrue(CRAFT.isSameTask(editedCraft));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Deadline bakeCopy = new DeadlineBuilder(CRAFT).build();
        assertTrue(CRAFT.equals(bakeCopy));

        //same object -> returns true
        assertTrue(CRAFT.equals(CRAFT));

        // null -> returns false
        assertFalse(CRAFT.equals(null));

        // different type -> returns false
        assertFalse(CRAFT.equals(ATTEND));

        // different Deadlines -> return false
        assertFalse(CRAFT.equals(ELECT));

        // different title -> returns false
        Deadline editedCraft = new DeadlineBuilder(CRAFT).withTitle(VALID_TITLE_PROJECT).build();
        assertFalse(CRAFT.equals(editedCraft));

        // different description -> returns false
        editedCraft = new DeadlineBuilder(CRAFT).withDescription(VALID_DESCRIPTION_PROJECT).build();
        assertFalse(CRAFT.equals(editedCraft));

        // different priority -> returns false
        editedCraft = new DeadlineBuilder(CRAFT).withPriority(VALID_PRIORITY_LECTURE).build();
        assertFalse(CRAFT.equals(editedCraft));

        // different deadlineDate -> returns false
        editedCraft = new DeadlineBuilder(CRAFT).withDeadlineDate(VALID_TASKDATE_PROJECT).build();
        assertFalse(CRAFT.equals(editedCraft));

        // different status -> returns false
        editedCraft = new DeadlineBuilder(CRAFT).withDoneStatus(1).build();
        assertFalse(CRAFT.equals(editedCraft));

        // different tags -> returns false
        editedCraft = new DeadlineBuilder(CRAFT).withTags(VALID_TAG_LECTURE).build();
        assertFalse(CRAFT.equals(editedCraft));
    }
}
