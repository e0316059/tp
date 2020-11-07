package seedu.schedar.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class DoneStatusTest {

    @Test
    public void constructor_null_createNewDoneStatusWithNotDone() {
        assertTrue(new DoneStatus(0).equals(new DoneStatus()));
    }

    @Test
    public void isValidDoneStatus() {
        // invalid integers
        assertFalse(DoneStatus.isValidDoneStatus(4));
        assertFalse(DoneStatus.isValidDoneStatus(1000));
        assertFalse(DoneStatus.isValidDoneStatus(-1));

        // valid integers
        assertTrue(DoneStatus.isValidDoneStatus(0));
        assertTrue(DoneStatus.isValidDoneStatus(1));
        assertTrue(DoneStatus.isValidDoneStatus(2));
    }
}
