package seedu.schedar.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.schedar.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.List;

import seedu.schedar.commons.core.Messages;
import seedu.schedar.commons.core.index.Index;
import seedu.schedar.logic.CommandHistory;
import seedu.schedar.logic.commands.exceptions.CommandException;
import seedu.schedar.model.Model;
import seedu.schedar.model.task.Deadline;
import seedu.schedar.model.task.DoneStatus;
import seedu.schedar.model.task.Event;
import seedu.schedar.model.task.Task;
import seedu.schedar.model.task.ToDo;

public class DoneCommand extends Command {
    public static final String COMMAND_WORD = "done";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the task identified by the index number shown in the list as done.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DONE_TASK_SUCCESS = "Mark task as done: %1$s";

    private final Index targetIndex;

    public DoneCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model, CommandHistory history) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToMarkDone = lastShownList.get(targetIndex.getZeroBased());
        Task taskMarkedDone;

        if (taskToMarkDone instanceof ToDo) {
            ToDo toDoToMarkDone = (ToDo) taskToMarkDone;
            taskMarkedDone = new ToDo(
                    toDoToMarkDone.getTitle(),
                    toDoToMarkDone.getDescription(),
                    toDoToMarkDone.getPriority(),
                    new DoneStatus(1),
                    toDoToMarkDone.getTags()
            );
        } else if (taskToMarkDone instanceof Deadline) {
            Deadline dlToMarkDone = (Deadline) taskToMarkDone;
            taskMarkedDone = new Deadline(
                    dlToMarkDone.getTitle(),
                    dlToMarkDone.getDescription(),
                    dlToMarkDone.getPriority(),
                    dlToMarkDone.getDeadlineDate(),
                    new DoneStatus(1),
                    dlToMarkDone.getTags()
            );
        } else {
            Event eventToMarkDone = (Event) taskToMarkDone;
            taskMarkedDone = new Event(
                    eventToMarkDone.getTitle(),
                    eventToMarkDone.getDescription(),
                    eventToMarkDone.getPriority(),
                    eventToMarkDone.getEventDate(),
                    eventToMarkDone.getEventTime(),
                    new DoneStatus(1),
                    eventToMarkDone.getTags()
            );
        }

        model.setTask(taskToMarkDone, taskMarkedDone);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        model.commitTaskManager();

        return new CommandResult(String.format(MESSAGE_DONE_TASK_SUCCESS, taskToMarkDone));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DoneCommand // instanceof handles nulls
                && targetIndex.equals(((DoneCommand) other).targetIndex)); // state check
    }
}
