package seedu.schedar.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.schedar.commons.util.AppUtil.checkArgument;

/**
 * Represents a Task's priority in the task manager.
 * Guarantees: immutable; is valid as declared in {@link #isValidPriority(String)}
 */
public class Priority {

    public static final String MESSAGE_CONSTRAINTS =
            "Priority level should be Low, Medium or High";
    public static final String VALIDATION_STRING_PRIORITY_NONE = "NO PRIORITY ASSIGNED";
    public static final String VALIDATION_STRING_PRIORITY_LOW = "LOW";
    public static final String VALIDATION_STRING_PRIORITY_MEDIUM = "MEDIUM";
    public static final String VALIDATION_STRING_PRIORITY_HIGH = "HIGH";

    public final PriorityLevel level;

    /**
     * Constructs a {@code Priority}.
     *
     * @param level A valid priority level.
     */
    public Priority(String level) {
        requireNonNull(level);
        checkArgument(isValidPriority(level), MESSAGE_CONSTRAINTS);
        switch(level.toUpperCase()) {
        case"NO PRIORITY ASSIGNED":
            this.level = PriorityLevel.NONE;
            break;
        case "LOW":
            this.level = PriorityLevel.LOW;
            break;
        case "MEDIUM":
            this.level = PriorityLevel.MEDIUM;
            break;
        case "HIGH":
            this.level = PriorityLevel.HIGH;
            break;
        default:
            throw new IllegalArgumentException(MESSAGE_CONSTRAINTS);
        }
    }

    /**
     * Returns true if a given string is a valid priority level.
     */
    public static boolean isValidPriority(String test) {
        return test.toUpperCase().equals(VALIDATION_STRING_PRIORITY_NONE)
                || test.toUpperCase().equals(VALIDATION_STRING_PRIORITY_LOW)
                || test.toUpperCase().equals(VALIDATION_STRING_PRIORITY_MEDIUM)
                || test.toUpperCase().equals(VALIDATION_STRING_PRIORITY_HIGH);
    }

    @Override
    public String toString() {
        return level.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Priority // instanceof handles nulls
                && level.equals(((Priority) other).level)); // state check
    }

    @Override
    public int hashCode() {
        return level.hashCode();
    }

}
