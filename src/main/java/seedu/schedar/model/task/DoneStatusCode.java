package seedu.schedar.model.task;

import java.util.HashMap;
import java.util.Map;

public enum DoneStatusCode {
    NOT_DONE(0, "Not done"),
    DONE(1, "Done"),
    OVERDUE(2, "Overdue");

    private static final Map<Integer, DoneStatusCode> BY_STATUS_CODE = new HashMap<>();

    private final int statusCode;
    private final String label;
    static {
        for (DoneStatusCode ds : values()) {
            BY_STATUS_CODE.put(ds.statusCode, ds);
        }
    }

    private DoneStatusCode(int statusCode, String label) {
        this.statusCode = statusCode;
        this.label = label;
    }

    public static DoneStatusCode getDoneStatusByCode(int statusCode) {
        return BY_STATUS_CODE.get(statusCode);
    }

    public int getStatusCode() {
        return statusCode;
    }

    @Override
    public String toString() {
        return label;
    }
}
