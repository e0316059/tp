---
layout: page
title: Daniel Adipranoto's Project Portfolio Page
---

## Project: ScheDar

ScheDar is a desktop task manage system that helps user manage their daily tasks. The user interacts with it using a CLI, and it has a GUI created with JavaFX. It is written in Java, and has about 10 kLoC.

Given below are my contributions to the project.

* **Code contributed**: [RepoSense link](https://nus-cs2103-ay2021s1.github.io/tp-dashboard/#breakdown=true&search=danadi7)

* **New Feature**: Designed and added the different types of tasks, as well as basic commands to manipulate tasks.
  * What it does: Lays the groundwork for the entire task manager.
  * Highlights: Tasks are the basic building block for our task manager application. I created the 3 basic types of tasks: `ToDo`s, `Deadline`s and `Event`s, which included deciding the data types for the different variables, how they are implemented, and how they are parsed by the command parser.

* **Enhancements to existing feature**: Refactored AB3 to Schedar
  * Modified the AB3 application to the initial version of Schedar, which supports `Task`s instead of `Person`s.
  * Refactored close to 4kLOC over the course more than a week.
  * As there were 3 different classes of tasks inheriting from an abstract class `Task`, they had to be handled differently from how AB3 handled the `Person` class. This included ensuring that the different task types could still have their own attributes and behaviours, while still implementing common functions such as marking a task as completed.
  * Refactored test cases to support the new task manager, and the different types of tasks. 
  
* **Enhancements to existing feature**: Updated UI to support tasks
  * Modified the AB3 UI to be able to display the 3 different types of tasks.
  * (This has since been superseded with a new UI design, though some elements of this initial change were incorporated.)

* **Documentation**:
  * User Guide:
    * Added instruction for the features `todo`, `deadline`, `event`, `editTodo`, `editDeadline`, `editEvent`, `list` and `exit`.
  * Developer Guide:
    * Added implementation details for [Adding Tasks](https://ay2021s1-cs2103-t16-4.github.io/tp/DeveloperGuide.html#adding-tasks).
    * Updated UML diagram for `Model`, `Logic` and `Stroage` components.
    
* **Team Tasks**
  * Kept track of deadlines, and managed issues and milestones.
  * Managed how the individual and team tasks are divided among team members.
  * Managed JAR releases for all the different versions of the application.
  * Suggested and led the use of Trello for managing and keeping track of features and user stories. 

* **Community**:
  * PRs reviewed: [#40](https://github.com/AY2021S1-CS2103-T16-4/tp/pull/40), [#56](https://github.com/AY2021S1-CS2103-T16-4/tp/pull/56), [#64](https://github.com/AY2021S1-CS2103-T16-4/tp/pull/64), [#71](https://github.com/AY2021S1-CS2103-T16-4/tp/pull/71#partial-pull-merging)
  * Assisted team members in debugging issues related to their features: [#59](https://github.com/AY2021S1-CS2103-T16-4/tp/pull/59)
  * Contributed to forum discussions: [#244](https://github.com/nus-cs2103-AY2021S1/forum/issues/244) 
  * Reported bugs, and gave suggestions for another team during [PE Dry Run](https://github.com/danadi7/ped/issues). 