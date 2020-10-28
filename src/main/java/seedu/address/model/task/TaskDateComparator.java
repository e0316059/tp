package seedu.address.model.task;

import java.util.Comparator;

public class TaskDateComparator implements Comparator<Task> {
    public TaskDate getDate(Task t) {
        if (t instanceof Deadline) {
            return ((Deadline) t).getDeadlineDate();
        }
        if (t instanceof Event) {
            return ((Event) t).getEventDate();
        } else {
            return null;
        }
    }

    @Override
    public int compare(Task task1, Task task2) {
        TaskDate date1 = getDate(task1);
        TaskDate date2 = getDate(task2);
        if (date1 != null && date2 != null) {
            return date1.compareTo(date2);
        }
        if (date1 == null) {
            return 1;
        } else {
            return -1;
        }
    }
}
