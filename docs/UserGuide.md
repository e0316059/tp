---
layout: page
title: User Guide
---

ScheDar is a **desktop app for managing tasks, optimized for use via a Command Line Interface** (CLI) while still having the benefits of a Graphical User Interface (GUI). If you can type fast, ScheDar can get your task management done faster than traditional GUI apps.

* Table of Contents
{:toc}

--------------------------------------------------------------------------------------------------------------------

## Quick start

1. Ensure you have Java `11` or above installed in your Computer.

1. Download the latest `ScheDar.jar` from [here](https://github.com/AY2021S1-CS2103-T16-4/tp/releases).

1. Copy the file to the folder you want to use as the _home folder_ for your ScheDar.

1. Double-click the file to start the app. The GUI similar to the below should appear in a few seconds. Note how the app contains some sample data.<br>
   ![Ui](images/Ui.png)

1. Type the command in the command box and press Enter to execute it. e.g. typing **`help`** and pressing Enter will open the help window.<br>
   Some example commands you can try:

   * **`list`** : Lists out the tasks currently on the task list.

   * **`event`**` t/project meeting date/2020-09-16 time/18:00 tag/important` : Adds an event named `project meeting` on 16 September 2020 at 6pm to ScheDar.

   * **`delete`**`3` : Deletes the 3rd task shown in the current list.

   * **`find`** `cs2103 project`: Searches the current task list for the specified keywords `cs2103 project`.

   * **`sort`** : Sorts the current list.

   * **`exit`** : Exits the app.

1. Refer to the [Features](#features) below for details of each command.

--------------------------------------------------------------------------------------------------------------------

## Features

<div markdown="block" class="alert alert-info">

**:information_source: Notes about the command format:**<br>

* Words in `UPPER_CASE` are the parameters to be supplied by the user.<br>
  e.g. in `add t/TITLE`, `TITLE` is a parameter which can be used as `todo t/homework`.

* Items in square brackets are optional.<br>
  e.g `t/TITLE [tag/TAG]` can be used as `t/homework tag/important` or as `t/project meeting`.

* Items with `…`​ after them can be used multiple times including zero times.<br>
  e.g. `[t/TAG]…​` can be used as ` ` (i.e. 0 times), `tag/important`, `tag/important tag/online` etc.

* Parameters can be in any order.<br>
  e.g. if the command specifies `t/TITLE d/DESCRIPTION`, `d/DESCRIPTION t/TITLE` is also acceptable.

</div>

### Viewing help : `help`

Lists out all the commands accepted by ScheDar.

Format: `help`


### Adding a todo : `todo`

Adds a new Todo to the ScheDar program.

Format: `todo t/TITLE [d/DESCRIPTION] [p/PRIORITY] [tag/TAG]…​`

* `TITLE` refers to the title of todo to be added.
* `DESCRIPTION` refers to the description of the todo to be added.
* `PRIORITY` refers to the priority level of the todo to be added.
* `PRIORITY` must be one of the following: `Low`, `Medium`, `High`
* Adds a new todo of the given title, description if any, priority if any, and tag if any, to the task list.
* The new todo will be appended to the end of the existing task list.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A task can have any number of tags (including 0)
</div>

Examples:
* `todo t/cook dinner tag/important`

### Adding a deadline : `deadline`

Adds a new Deadline to the ScheDar program.

Format: `deadline t/TITLE date/DATE [d/DESCRIPTION] [p/PRIORITY] [tag/TAG]…​`
* `TITLE` refers to the title of deadline to be added.
* `DATE` refers to the date of the deadline to be added, in a `YYYY-MM-DD` format.
* `DESCRIPTION` refers to the description of the deadline to be added.
* `PRIORITY` refers to the priority level of the deadline to be added.
* `PRIORITY` must be one of the following: `Low`, `Medium`, `High`
* Adds a new deadline of the given title on a given date, description if any, priority if any, and tag if any, to the task list.
* The new deadline will be appended to the end of the existing task list.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A task can have any number of tags (including 0)
</div>

Examples:
* `deadline t/tP code d/finish code for tP date/2020-10-30 tag/cs2103`

### Adding an event : `event`

Adds a new Event to the ScheDar program.

Format: `event t/TITLE date/DATE time/TIME [d/DESCRIPTION] [p/PRIORITY] [tag/TAG]…​`
* `TITLE` refers to the title of event to be added.
* `DATE` refers to the date of the event to be added, in a `YYYY-MM-DD` format.
* `TIME` refers to the time of the event to be added, in a `HH:MM` format.
* `DESCRIPTION` refers to the description of the event to be added.
* `PRIORITY` refers to the priority level of the event to be added.
* `PRIORITY` must be one of the following: `Low`, `Medium`, `High`
* Adds a new event of the given title on a given date and time, description if any, priority if any, and tag if any, to the task list.
* The new event will be appended to the end of the existing task list.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
A task can have any number of tags (including 0)
</div>

Examples:
* `event t/project meeting date/2020-10-28 time/18:00 p/high tag/cs2103`

### Listing all tasks : `list`

Lists out the tasks currently on the task list.

Format: `list`

### Listing dated items chronologically : `sort`

Lists out deadline-type and event-type tasks currently stored on the task list, in chronological order of date. The earliest deadline or event would be at the top of the list. 
Todo tasks would be move to the bottom of the list.

Format: `sort`

### Marking task as done : `done`

Marks an existing task on the task list as completed.

Format: `done INDEX`
        Marks as completed the task at the specified INDEX.
        The index refers to the index number shown on the task list when using the list command.
        The index must be a positive integer 1, 2, 3, …​

Example: `done 2`

### Deleting a task : `delete`

Deletes the specified task from the current task list.

Format: `delete INDEX`
        Deletes the task at the specified INDEX.
        The index refers to the index number shown on the task list when using the list command.
        The index must be a positive integer 1, 2, 3, …​

Example: `delete 1`

### Editing a task : `editTodo`/`editEvent`/`editDeadline`

Edits a task(todo/event/deadline) on the ScheDar program.

Format: `editTodo t/TITLE [d/DESCRIPTION] [p/PRIORITY] [tag/TAG]…​`/`editEvent t/TITLE date/DATE time/TIME [d/DESCRIPTION] [p/PRIORITY] [tag/TAG]…​`
        /`editDeadline t/TITLE date/DATE [d/DESCRIPTION] [p/PRIORITY] [tag/TAG]…​`
        Edits the task of the given type, title, description, priority, date and time, and tag, in the task list.
        The edited task will be placed in the same position as the original task in the task list.

<div markdown="span" class="alert alert-primary">:bulb: **Tip:**
The edit command used must correspond to the type of the task being edited.
</div>

Examples:
* `editEvent t/project discussion`
* `editDeadline date/2020-12-31 p/low`

### Retrieve last-deleted task : `retrieve`

Restores the most recently deleted task.

Format: `retrieve`	


### Searching for tasks by keyword : `find`

Searches current shown tasklist for the specified keyword(s).

Format: `find KEYWORD1 [KEYWORD2]`
        Searches the task list shown on screen for the presence of the keyword(s) in titles and descriptions of tasks.
        Matching tasks are shown on the screen to the user.

Example: `find cs2103 project`

### Exiting the program : `exit`

Exits the program.

Format: `exit`

### Saving the data

All ScheDar task data are saved on the hard disk automatically after any command that changes the data. There is no need to save manually.


--------------------------------------------------------------------------------------------------------------------

## FAQ

**Q**: How do I transfer my data to another Computer?<br>
**A**: Install the app in the other computer and overwrite the empty data file it creates with the file that contains the data of your previous ScheDar home folder.

--------------------------------------------------------------------------------------------------------------------

## Command summary

Action | Format, Examples
--------|------------------
**Add Todo** | `todo t/TITLE [d/DESCRIPTION] [p/PRIORITY] [tag/TAG]…​` <br> e.g., `todo t/bake cake d/bake birthday cake for prof p/high tag/important`
**Add Deadline** | `deadline t/TITLE date/DATE [d/DESCRIPTION] [p/PRIORITY] [tag/TAG]…​` <br> e.g., `deadline t/CS2103 quiz date/2020-10-31 p/high`
**Add Event** | `event t/TITLE date/DATE time/TIME [d/DESCRIPTION] [p/PRIORITY] [tag/TAG]…​` <br> e.g., `event t/project meeting date/2020-10-28 time/18:00 p/high tag/project`
**Delete** | `delete INDEX`<br> e.g., `delete 1`
**Done** | `done INDEX`<br> e.g., `done 1`
**Exit** | `exit`
**Find** | `find KEYWORD [MORE_KEYWORDS]`<br> e.g., `find project meeting`
**Help** | `help`
**List** | `list`
**Retrieve** | `retrieve`
**Sort** | `sort`

