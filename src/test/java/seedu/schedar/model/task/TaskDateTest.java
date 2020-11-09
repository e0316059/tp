package seedu.schedar.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.schedar.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class TaskDateTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new TaskDate(null));
    }

    @Test
    public void constructor_invalidDate_throwsIllegalArgumentException() {
        String invalidDate = "";
        assertThrows(IllegalArgumentException.class, () -> new TaskDate(invalidDate));
    }

    @Test
    public void isValidDate() {
        // null date
        assertThrows(NullPointerException.class, () -> TaskDate.isValidDate(null));

        // blank date
        assertFalse(TaskDate.isValidDate(""));

        // invalid dates
        assertFalse(TaskDate.isValidDate("2020/12/31"));
        assertFalse(TaskDate.isValidDate("2020.12.31"));
        assertFalse(TaskDate.isValidDate("20-12-31"));
        assertFalse(TaskDate.isValidDate("2020-31-12"));
        assertFalse(TaskDate.isValidDate("31-12-2020"));
        assertFalse(TaskDate.isValidDate("12-31-2020"));
        assertFalse(TaskDate.isValidDate("31 DEC 2020"));
        assertFalse(TaskDate.isValidDate("DEC 31 2020"));
        assertFalse(TaskDate.isValidDate("2020 DEC 31"));
        assertFalse(TaskDate.isValidDate("2020 31 DEC"));
        assertFalse(TaskDate.isValidDate("2020-DEC-31"));
        assertFalse(TaskDate.isValidDate("2020-1-31"));
        assertFalse(TaskDate.isValidDate("2020-12-1"));
        assertFalse(TaskDate.isValidDate("2020-02-30"));
        assertFalse(TaskDate.isValidDate("2020-02-31"));
        assertFalse(TaskDate.isValidDate("2021-02-29"));
        assertFalse(TaskDate.isValidDate("2100-02-29"));

        // valid dates
        assertTrue(TaskDate.isValidDate("2020-12-31"));
        assertTrue(TaskDate.isValidDate("1970-01-01"));
        assertTrue(TaskDate.isValidDate("2020-12-01"));
        assertTrue(TaskDate.isValidDate("2020-01-31"));
    }
}
