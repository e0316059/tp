package seedu.schedar.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.schedar.logic.commands.CommandTestUtil.VALID_DESCRIPTION_PROJECT;
import static seedu.schedar.logic.commands.CommandTestUtil.VALID_PRIORITY_PROJECT;
import static seedu.schedar.logic.commands.CommandTestUtil.VALID_TAG_PROJECT;
import static seedu.schedar.logic.commands.CommandTestUtil.VALID_TITLE_PROJECT;
import static seedu.schedar.testutil.Assert.assertThrows;
import static seedu.schedar.testutil.TypicalTasks.ATTEND;
import static seedu.schedar.testutil.TypicalTasks.BAKE;
import static seedu.schedar.testutil.TypicalTasks.DISH;

import org.junit.jupiter.api.Test;

import seedu.schedar.testutil.ToDoBuilder;

public class ToDoTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        ToDo todo = new ToDoBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> todo.getTags().remove(0));
    }

    @Test
    public void isSameTask() {
        // same object -> returns true
        assertTrue(BAKE.isSameTask(BAKE));

        // null -> returns false
        assertFalse(BAKE.isSameTask(null));

        // different description -> returns false
        ToDo editedBake = new ToDoBuilder(BAKE).withDescription(VALID_DESCRIPTION_PROJECT).build();
        assertFalse(BAKE.isSameTask(editedBake));

        // different title -> returns false
        editedBake = new ToDoBuilder(BAKE).withTitle(VALID_TITLE_PROJECT).build();
        assertFalse(BAKE.isSameTask(editedBake));

        // same title, same description, different attributes -> returns true
        editedBake = new ToDoBuilder(BAKE).withPriority(VALID_PRIORITY_PROJECT)
                .withDoneStatus(1).withTags(VALID_TAG_PROJECT).build();
        assertTrue(BAKE.isSameTask(editedBake));
    }

    @Test
    public void equals() {
        // same values -> returns true
        ToDo bakeCopy = new ToDoBuilder(BAKE).build();
        assertTrue(BAKE.equals(bakeCopy));

        //same object -> returns true
        assertTrue(BAKE.equals(BAKE));

        // null -> returns false
        assertFalse(BAKE.equals(null));

        // different type -> returns false
        assertFalse(BAKE.equals(ATTEND));

        // different ToDos -> return false
        assertFalse(BAKE.equals(DISH));

        // different title -> returns false
        ToDo editedBake = new ToDoBuilder(BAKE).withTitle(VALID_TITLE_PROJECT).build();
        assertFalse(BAKE.equals(editedBake));

        // different description -> returns false
        editedBake = new ToDoBuilder(BAKE).withDescription(VALID_DESCRIPTION_PROJECT).build();
        assertFalse(BAKE.equals(editedBake));

        // different priority -> returns false
        editedBake = new ToDoBuilder(BAKE).withPriority(VALID_PRIORITY_PROJECT).build();
        assertFalse(BAKE.equals(editedBake));

        // different status -> returns false
        editedBake = new ToDoBuilder(BAKE).withDoneStatus(1).build();
        assertFalse(BAKE.equals(editedBake));

        // different tags -> returns false
        editedBake = new ToDoBuilder(BAKE).withTags(VALID_TAG_PROJECT).build();
        assertFalse(BAKE.equals(editedBake));
    }
}
