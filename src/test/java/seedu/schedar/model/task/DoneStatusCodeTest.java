package seedu.schedar.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class DoneStatusCodeTest {

    @Test
    public void getDoneStatusByCode() {
        assertEquals(DoneStatusCode.NOT_DONE, DoneStatusCode.getDoneStatusByCode(0));
        assertEquals(DoneStatusCode.DONE, DoneStatusCode.getDoneStatusByCode(1));
        assertEquals(DoneStatusCode.OVERDUE, DoneStatusCode.getDoneStatusByCode(2));
    }

    @Test
    public void getStatusCode() {
        assertEquals(0, DoneStatusCode.NOT_DONE.getStatusCode());
        assertEquals(1, DoneStatusCode.DONE.getStatusCode());
        assertEquals(2, DoneStatusCode.OVERDUE.getStatusCode());
    }
}
