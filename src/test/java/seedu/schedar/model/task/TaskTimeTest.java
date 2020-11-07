package seedu.schedar.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.schedar.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TaskTimeTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskTime(null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidTime = "";
        assertThrows(IllegalArgumentException.class, () -> new TaskTime(invalidTime));
    }

    @Test
    public void isValidTime() {
        // null time
        assertThrows(NullPointerException.class, () -> TaskTime.isValidTime(null));

        // blank time
        assertFalse(TaskTime.isValidTime(""));

        // invalid times
        assertFalse(TaskTime.isValidTime("12.00"));
        assertFalse(TaskTime.isValidTime("1pm"));
        assertFalse(TaskTime.isValidTime("12 noon"));
        assertFalse(TaskTime.isValidTime("Noon"));
        assertFalse(TaskTime.isValidTime("23:60"));
        assertFalse(TaskTime.isValidTime("1:1"));
        assertFalse(TaskTime.isValidTime("12:1"));
        assertFalse(TaskTime.isValidTime("1:01"));

        // valid times
        assertTrue(TaskTime.isValidTime("12:00"));
        assertTrue(TaskTime.isValidTime("00:00"));
        assertTrue(TaskTime.isValidTime("23:59"));
        assertTrue(TaskTime.isValidTime("01:01"));
        assertTrue(TaskTime.isValidTime("14:01"));
        assertTrue(TaskTime.isValidTime("01:14"));
    }
}
