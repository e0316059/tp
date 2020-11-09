package seedu.schedar.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.schedar.commons.util.AppUtil.checkArgument;

import java.time.DateTimeException;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Task's time in task manager.
 * Guarantees: immutable; is valid as declared in {@link #isValidTime(String)}
 */
public class TaskTime {

    public static final String MESSAGE_CONSTRAINTS =
            "Time should be a valid time between 00:00 and 23:59 in the format HH:MM";

    public static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_LOCAL_TIME;

    public final LocalTime time;

    /**
     * Constructs a {@code TaskTime}.
     *
     * @param timeString A valid time.
     */
    public TaskTime(String timeString) {
        requireNonNull(timeString);
        checkArgument(isValidTime(timeString), MESSAGE_CONSTRAINTS);
        this.time = LocalTime.parse(timeString);
    }

    /**
     * Returns true if a given string is a valid time.
     */
    public static boolean isValidTime(String test) {
        try {
            LocalTime.parse(test, FORMATTER);
        } catch (DateTimeException e) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return time.format(FORMATTER);
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof TaskTime
                && time.equals(((TaskTime) other).time));
    }

    @Override
    public int hashCode() {
        return time.hashCode();
    }
}
