package seedu.schedar.model.task;

import static java.util.Objects.requireNonNull;
import static seedu.schedar.commons.util.AppUtil.checkArgument;

public class DoneStatus {

    public static final String MESSAGE_CONSTRAINTS = "DoneStatus should be an integer.";

    public final DoneStatusCode status;

    /**
     * Creates a new DoneStatus given a status code.
     */
    public DoneStatus(int statusCode) {
        requireNonNull(statusCode);
        checkArgument(isValidDoneStatus(statusCode), MESSAGE_CONSTRAINTS);
        this.status = DoneStatusCode.getDoneStatusByCode(statusCode);
    }

    /**
     * Creates a new DoneStatus that defaults to NOT_DONE.
     */
    public DoneStatus() {
        this.status = DoneStatusCode.NOT_DONE;
    }

    /**
     * Returns true if a given string is a valid status code.
     */
    public static boolean isValidDoneStatus(int test) {
        DoneStatusCode ds = DoneStatusCode.getDoneStatusByCode(test);
        return ds != null;
    }

    @Override
    public String toString() {
        return status.toString();
    }

    @Override
    public boolean equals(Object other) {
        return other == this
                || (other instanceof DoneStatus
                && status.equals(((DoneStatus) other).status));
    }

    @Override
    public int hashCode() {
        return status.hashCode();
    }

}
