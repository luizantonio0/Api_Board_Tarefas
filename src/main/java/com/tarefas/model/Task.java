package com.tarefas.model;


import com.tarefas.changes_tasks.ChangesMadeByUser;
import com.tarefas.model.enums.task.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    private String id;
    private String name;
    private String description;
    private User creator;
    private User manager;
    private User completedBy;
    private String column;
    private TaskStatus status;
    private boolean completed;
    private boolean cancelled;
    //bloqueia a task
    private boolean blocked;
    //create a task
    private OffsetDateTime creationDate;
    private OffsetDateTime completionDate;
    private OffsetDateTime completedDate;
    //start the task
    private OffsetDateTime startDate;
    private List<ChangesMadeByUser> blockerUsers;
    private List<ChangesMadeByUser> deblockerUsers;
    private List<ChangesMadeByUser> ChangesMadeByUsers;

}
