package com.tarefas.model.task;


import com.tarefas.model.TaskColumn;
import com.tarefas.model.User;
import com.tarefas.model.enums.task.TaskStatus;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Task {
    private String id;
    private String name;
    // Só o manager pode alterar dados da task
    private String description;
    private User creator;
    private User manager;
    private User completedBy;
    private TaskStatus status;
    private boolean completed;
    private boolean cancelled;
    //TODO: deve registrar o tempo que demorou em cada coluna(insert AT, moved AT)
    private TaskColumn taskColumn;
    private OffsetDateTime enteredCurrentColumnAt;
    //bloqueia a task, quando há um novo bloqueio deve instanciar um novo objeto
    private BlockedTask blocked;
    //create a task
    private OffsetDateTime createdAt;
    private OffsetDateTime completionDate;
    private OffsetDateTime completedDate;
    //start the task
    private OffsetDateTime startDate;
}
