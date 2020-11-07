package seedu.schedar.model.task;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.schedar.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class PriorityTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Priority(null));
    }

    @Test
    public void constructor_invalidPriorityLevel_throwsIllegalArgumentException() {
        String invalidPriorityLevel = "";
        assertThrows(IllegalArgumentException.class, () -> new Priority(invalidPriorityLevel));
    }

    @Test
    public void isValidPriority() {
        // null priority
        assertThrows(NullPointerException.class, () -> Priority.isValidPriority(null));

        // invalid priorities
        assertFalse(Priority.isValidPriority("Test"));
        assertFalse(Priority.isValidPriority("Higher"));
        assertFalse(Priority.isValidPriority("Lower"));
        assertFalse(Priority.isValidPriority("Random long sentence."));

        // valid priorities
        assertTrue(Priority.isValidPriority("HIGH"));
        assertTrue(Priority.isValidPriority("MEDIUM"));
        assertTrue(Priority.isValidPriority("LOW"));
        assertTrue(Priority.isValidPriority("High"));
        assertTrue(Priority.isValidPriority("Medium"));
        assertTrue(Priority.isValidPriority("Low"));
        assertTrue(Priority.isValidPriority("high"));
        assertTrue(Priority.isValidPriority("medium"));
        assertTrue(Priority.isValidPriority("low"));
        assertTrue(Priority.isValidPriority("hIgH"));
        assertTrue(Priority.isValidPriority("MeDiUm"));
        assertTrue(Priority.isValidPriority("lOw"));
    }
}
